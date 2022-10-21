package io.github.kloping.mywebsite.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.kloping.mywebsite.entitys.database.*;
import io.github.kloping.mywebsite.services.game.GameService;
import io.github.kloping.mywebsite.utils.ImageDrawer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * 当前商品 <br/>
 * /game/all <br/>
 * 获取当前信息 <br/>
 * /game/info?qid <br/>
 * 购买当前上架的商品 <br/>
 * /game/buy?name <br/>
 * 查询当前的资产 <br/>
 * /game/put?qid <br/>
 * 存当前的资产 <br/>
 * /game/get?qid <br/>
 * 查询当前储存 <br/>
 * /game/bag?qid <br/>
 * 查询当前房价 <br/>
 * /game/house <br/>
 * 购买房子 <br/>
 * /game/buyHouse <br/>
 *
 * @author github.kloping
 */
@RestController
@RequestMapping("/game")
public class GameController {

    @RequestMapping("buy")
    private Object buy(@RequestParam("qid") Long qid, @Nullable @RequestParam("n") Integer n, @RequestParam("name") String name) {
        if (n == null || n <= 0) n = 1;
        GamePersonInfo gpi = gameService.getInfo(qid);
        QueryWrapper<GameGoods> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        wrapper.eq("up", 1);
        GameGoods goods = gameService.gameGoodsMapper.selectOne(wrapper);
        if (goods == null) return "未发现相关商品";
        if (goods.getNum() < n) {
            return "供货不足";
        } else {
            if (gpi.getMoney() >= goods.getPrice1() * n) {
                int r = 0;
                for (GameItem item : gameService.gameItemMapper.selectByQid(qid)) {
                    r += item.getNum();
                }
                int max = gameService.cap;
                for (GameHouse gameHouse : gameService.gameHouseMapper.selectByQid(qid)) {
                    max += gameHouse.getCap();
                }
                if (r + n > max) return "空间不足";
                GameItem item = new GameItem();
                item.setGoodsId(goods.getId()).setPrice0(goods.getPrice1()).setOwnerId(qid).setNum(n);
                gameService.gameItemMapper.insert(item);
                gpi.setMoney(gpi.getMoney() - goods.getPrice1() * n);
                gameService.gamePersonInfoMapper.updateById(gpi);
                return "完成";
            } else {
                return "余额不足";
            }
        }
    }

    @RequestMapping("sel")
    private Object sel(@RequestParam("qid") Long qid, @Nullable @RequestParam("n") Integer n, @RequestParam("name") String name) {
        if (n == null || n <= 0) n = 1;
        GamePersonInfo gpi = gameService.getInfo(qid);
        QueryWrapper<GameGoods> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        wrapper.eq("up", 1);
        GameGoods goods = gameService.gameGoodsMapper.selectOne(wrapper);
        if (goods == null) {
            return "未发现相关商品";
        } else {
            GameItem item = gameService.gameItemMapper.selectOne(qid, goods.getId());
            if (item.getNum() >= n) {
                if (item.getNum().intValue() == n) {
                    gameService.gameItemMapper.deleteById(item.getId());
                } else {
                    item.setNum(item.getNum() - n);
                    gameService.gameItemMapper.updateById(item);
                }
                int m0 = goods.getPrice1();
                gpi.setMoney(gpi.getMoney() + m0 * item.getNum());
                gameService.gamePersonInfoMapper.updateById(gpi);
                goods.setNum(goods.getNum() + n);
                gameService.gameGoodsMapper.updateById(goods);
                return "成功";
            } else {
                return "储量不足";
            }
        }
    }

    @RequestMapping("bag")
    private Object bag(@RequestParam("qid") Long qid, HttpServletResponse response) throws IOException {
        java.util.List<String> list = new LinkedList<>();
        List<GameItem> items = gameService.gameItemMapper.selectByQid(qid);
        int r = 0;
        for (GameItem item : items) {
            r += item.getNum();
        }
        int max = gameService.cap;
        for (GameHouse gameHouse : gameService.gameHouseMapper.selectByQid(qid)) {
            max += gameHouse.getCap();
        }
        list.add(String.format("容量:%s/%s", r, max));
        list.add(String.format("名称:数量(购买时价格)"));
        for (GameItem item : items) {
            GameGoods g = gameService.gameGoodsMapper.selectById(item.getGoodsId());
            list.add(String.format("%s:%s(%s)", g.getName(), item.getNum(), item.getPrice0()));
        }
        String name = ImageDrawer.createImage(list.toArray(new String[0]));
        response.sendRedirect("/" + name);
        return "OK";
    }

    @RequestMapping("put")
    private Object put(@RequestParam("qid") Long qid, @RequestParam("n") Long n) {
        GamePersonInfo gpi = gameService.getInfo(qid);
        if (gpi.getMoney() >= n) {
            GameBank gb = gameService.getBank(qid);
            gb.setMoney(gb.getMoney() + n);
            gpi.setMoney(gpi.getMoney() - n);
            gameService.gameBankMapper.updateById(gb);
            gameService.gamePersonInfoMapper.updateById(gpi);
            return "完成";
        } else {
            return "余额不足";
        }
    }

    @RequestMapping("get")
    private Object get(@RequestParam("qid") Long qid, @RequestParam("n") Long n) {
        GameBank gb = gameService.getBank(qid);
        if (gb.getMoney() >= n) {
            GamePersonInfo gpi = gameService.getInfo(qid);
            gb.setMoney(gb.getMoney() - n);
            gpi.setMoney(gpi.getMoney() + n);
            gameService.gameBankMapper.updateById(gb);
            gameService.gamePersonInfoMapper.updateById(gpi);
            return "完成";
        } else {
            return "存额不足";
        }
    }

    @RequestMapping("info")
    public Object info(@RequestParam("qid") Long qid) {
        GamePersonInfo gpi = gameService.getInfo(qid);
        GameBank gb = gameService.getBank(qid);
        JSONObject jo = new JSONObject();
        jo.put("id", qid);
        jo.put("money", gpi.getMoney());
        jo.put("bank", gb.getMoney());
        return jo;
    }

    @Autowired
    GameService gameService;

    private static final SimpleDateFormat SF_0 = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");

    @RequestMapping("all")
    public Object all(@RequestParam("type") @Nullable String type, HttpServletResponse response) throws Exception {
        QueryWrapper<GameGoods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("up", 1);
        if (type == null || type.isEmpty()) {
            return gameService.gameGoodsMapper.selectList(queryWrapper);
        } else {
            String name = ImageDrawer.drawer
                    (String.format("TIPS: %s", gameService.event.getDesc()),
                            gameService.gameGoodsMapper.selectList(queryWrapper),
                            String.format("周次:%s 下次刷新:%s 银行利率:%s%%", gameService.week, SF_0.format(gameService.time), gameService.rate));
            response.sendRedirect("/" + name);
            return "ok";
        }
    }
}
