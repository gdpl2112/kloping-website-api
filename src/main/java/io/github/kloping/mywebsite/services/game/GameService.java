package io.github.kloping.mywebsite.services.game;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.kloping.common.Public;
import io.github.kloping.mywebsite.entitys.database.*;
import io.github.kloping.mywebsite.mapper.*;
import io.github.kloping.number.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

import static io.github.kloping.mywebsite.controller.ApiToolController.RANDOM;

/**
 * @author github.kloping
 */
@org.springframework.stereotype.Service
public class GameService {
    /**
     * 玩家基础容量
     */
    public static final String BASE_CAPACITY = "BASE_CAPACITY";
    /**
     * 银行利率
     */
    public static final String BASE_RATE = "BASE_RATE";
    /**
     * 下次更新时间
     */
    public static final String NEXT_TIME = "NEXT_TIME";
    /**
     * next CD
     */
    public static final String NEXT_CD = "NEXT_CD";
    /**
     * 第几周标记
     */
    public static final String WEEK_ID = "WEEK_ID";
    /**
     * 初始资金
     */
    public static final String INIT_MONEY = "INIT_MONEY";
    /**
     * 补给资金
     */
    public static final String SUPPLY_MONEY = "SUPPLY_MONEY";
    /**
     * 上次id
     */
    public static final String UP_EVENT = "UP_EVENT";


    public Integer cd;
    public Long time;
    public Integer cap;
    public Float rate;
    public Integer week;
    public Integer im;
    public Integer sm;

    @Autowired
    public GameBankMapper gameBankMapper;

    @Autowired
    public GameEventMapper gameEventMapper;

    @Autowired
    public GameGoodsMapper gameGoodsMapper;

    @Autowired
    public GameHouseMapper gameHouseMapper;

    @Autowired
    public GameInfoMapper gameInfoMapper;

    @Autowired
    public GameItemMapper gameItemMapper;

    @Autowired
    public GamePersonInfoMapper gamePersonInfoMapper;

    public GameService() {
        Public.EXECUTOR_SERVICE.submit(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("init");
                im = getGameInfoValue(INIT_MONEY, 2000);
                sm = getGameInfoValue(INIT_MONEY, 100);
                cd = getGameInfoValue(NEXT_CD, 6);
                cap = getGameInfoValue(BASE_CAPACITY, 50);
                rate = getGameInfoValue(BASE_RATE, 0.01f);
                week = getGameInfoValue(WEEK_ID, 1);
                time = getGameInfoValue(NEXT_TIME, System.currentTimeMillis());
                getGameInfoValue(UP_EVENT, 0);
                if (time <= System.currentTimeMillis()) {
                    shake();
                } else {
                    event = gameEventMapper.selectById(getGameInfoValue(UP_EVENT, -1));
                    Public.EXECUTOR_SERVICE.submit(RUNNABLE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public final Runnable RUNNABLE = new Runnable() {
        @Override
        public void run() {
            long t0 = time - System.currentTimeMillis();
            try {
                Thread.sleep(t0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            shake();
            Public.EXECUTOR_SERVICE.submit(RUNNABLE);
        }
    };

    private void shake() {
        GameInfo info = new GameInfo();
        info.setName(WEEK_ID).setValue(String.valueOf(week + 1));
        gameInfoMapper.updateById(info);
        int b1 = (int) (rate * 100);
        for (GameBank gameBank : gameBankMapper.selectAll()) {
            Long b0 = NumberUtils.percentTo(1, gameBank.getMoney());
            b0 = NumberUtils.percentTo(b1, b0);
            if (b0 >= 0) {
                gameBank.setMoney(gameBank.getMoney() + b0);
                gameBankMapper.updateById(gameBank);
            }
        }
        time = time + ((long) cd * 1000L * 60L * 60L);
        info.setName(NEXT_TIME).setValue(time.toString());
        gameInfoMapper.updateById(info);
        List<GameEvent> events = gameEventMapper.selectAll();
        if (events.size() > 0) {
            while (true) {
                event = events.get(RANDOM.nextInt(events.size()));
                GameGoods goods = gameGoodsMapper.selectById(event.getGoodsId());
                if (event.getMinOffset() < 0) {
                    if (NumberUtils.toPercent(goods.getPrice1(), goods.getPrice0()) <= 60) {
                        continue;
                    }
                } else if (event.getMinOffset() > 0) {
                    if (NumberUtils.toPercent(goods.getPrice1(), goods.getPrice0()) >= 140) {
                        continue;
                    }
                }
                break;
            }
            GameInfo gameInfo = new GameInfo();
            gameInfo.setName(UP_EVENT);
            gameInfo.setValue(event.getId().toString());
            gameInfoMapper.updateById(gameInfo);
            Integer off;
            if (event.getMinOffset() > 0) {
                off = RANDOM.nextInt(event.getMaxOffset() - event.getMinOffset()) + event.getMinOffset();
            } else {
                off = RANDOM.nextInt((-event.getMaxOffset()) - (-event.getMinOffset())) + (-event.getMinOffset());
                off = -off;
            }
            gameGoodsMapper.updateAll();
            List<GameGoods> list = gameGoodsMapper.selectAll();
            Collections.shuffle(list);
            int n = 7;
            for (int i = 0; i < n; i++) {
                GameGoods gameGoods = list.get(i);
                if (gameGoods.getId().intValue() == event.getGoodsId().intValue()) {
                    n++;
                } else {
                    gameGoods.setUp(true);
                    int r;
                    if (gameGoods.getPrice0() < 100) {
                        r = RANDOM.nextInt(3) - 1;
                        gameGoods.setPrice1(gameGoods.getPrice1() + r);
                    } else {
                        r = RANDOM.nextInt(2) + 1;
                        r = NumberUtils.percentTo(r, gameGoods.getPrice0()).intValue();
                        if (RANDOM.nextBoolean())
                            r = -r;
                        gameGoods.setPrice1(gameGoods.getPrice1() + r);
                    }
                    gameGoods.setNum(40);
                    gameGoodsMapper.updateById(gameGoods);
                }
            }
            GameGoods eg = gameGoodsMapper.selectById(event.getGoodsId());
            eg.setPrice1(eg.getPrice1() + off);
            eg.setUp(true);
            eg.setNum(40);
            gameGoodsMapper.updateById(eg);
        }
    }

    public GameEvent event;

    private <T> T getGameInfoValue(String name, T defaultValue) {
        QueryWrapper<GameInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        GameInfo gameInfo = gameInfoMapper.selectOne(queryWrapper);
        if (gameInfo == null) {
            gameInfo = new GameInfo();
            gameInfo.setValue(defaultValue.toString());
            gameInfo.setName(name);
            gameInfoMapper.insert(gameInfo);
            return defaultValue;
        } else {
            Class c = defaultValue.getClass();
            if (c == Integer.class) {
                return (T) gameInfo.valueAsInteger();
            } else if (c == Long.class) {
                return (T) gameInfo.valueAsLong();
            } else if (c == Float.class) {
                return (T) gameInfo.valueAsFloat();
            } else return null;
        }
    }

    public GamePersonInfo getInfo(Long qid) {
        GamePersonInfo gpi = gamePersonInfoMapper.selectById(qid);
        if (gpi == null) {
            gpi = new GamePersonInfo();
            gpi.setMoney(im.longValue()).setQid(qid).setSupply(false);
            gamePersonInfoMapper.insert(gpi);
        }
        return gpi;
    }

    public GameBank getBank(Long qid) {
        GameBank gpi = gameBankMapper.selectById(qid);
        if (gpi == null) {
            gpi = new GameBank();
            gpi.setMoney(0L).setPid(qid);
            gameBankMapper.insert(gpi);
        }
        return gpi;
    }
}
