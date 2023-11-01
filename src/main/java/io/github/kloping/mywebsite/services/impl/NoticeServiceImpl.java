package io.github.kloping.mywebsite.services.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.kloping.mywebsite.entitys.NoticePack;
import io.github.kloping.mywebsite.mapper.dao.Notice;
import io.github.kloping.mywebsite.mapper.dao.UserTemp;
import io.github.kloping.mywebsite.mapper.NoticeMapper;
import io.github.kloping.mywebsite.mapper.UserTempMapper;
import io.github.kloping.mywebsite.services.INoticeService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author github.kloping
 */
@Service
public class NoticeServiceImpl implements INoticeService {
    @Autowired
    NoticeMapper mapper;

    private static final int MAX = 5;

    public static List<Notice> notices = new LinkedList<>();
    public static List<Notice> notices2 = new LinkedList<>();

    @Override
    public NoticePack get(int pn) {
        List<Notice> list = new LinkedList<>();
        if (notices != null && !notices.isEmpty()) {
            list.addAll(notices);
        } else {
            QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("state", 0);
            queryWrapper.orderByDesc("time");
            list = mapper.selectList(queryWrapper);
            notices = list;
        }
        return exportPack(list, --pn);
    }

    @Override
    public NoticePack get1(Integer pn) {
        List<Notice> list = new LinkedList<>();
        if (notices2 != null && !notices2.isEmpty()) {
            list.addAll(notices2);
        } else {
            QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("state", 0);
            queryWrapper.orderByDesc("time");
            list = mapper.selectList(queryWrapper);
            notices2 = list;
        }
        return exportPack(list, --pn, true);
    }

    public NoticePack exportPack(List<Notice> list, int pn) {
        return exportPack(list, pn, false);
    }

    /**
     * 清空内容
     *
     * @param list
     * @param pn
     * @param k
     * @return
     */
    public NoticePack exportPack(List<Notice> list, int pn, boolean k) {
        List<Notice> list0 = new LinkedList<>();
        int i = pn * MAX;
        while (true) {
            if (i >= list.size() || list0.size() >= MAX) break;
            Notice notice = list.get(i);
            if (k) notice.setHtml("");
            list0.add(notice);
            i++;
        }
        NoticePack noticePack = new NoticePack();
        noticePack.setNotices(list0);
        noticePack.setPn(++pn);
        noticePack.setMax(list.size() / 5 + (list.size() % 5 > 0 ? 1 : 0));
        return noticePack;
    }

    final SimpleDateFormat sf_0 = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");

    @Value("${auth.url}")
    String url;

    @Autowired
    UserTempMapper userTempMapper;

    @Override
    public boolean save(String img, String title, String body, UserDetails userDetails) throws Throwable {
        try {
            UserTemp userTemp = userTempMapper.selectById(userDetails.getUsername());
            Long qid = userTemp.getQid();
            String name = userTemp.getNickname();
            Notice notice = new Notice()
                    .setId(null)
                    .setAuthorId(qid)
                    .setAuthorName(name)
                    .setState(1)
                    .setTime(System.currentTimeMillis())
                    .setDate(sf_0.format(new Date()))
                    .setTitle(title)
                    .setHtml(body)
                    .setIcon(img);
            mapper.insert(notice);
            tips();
            notices.clear();
            notices2.clear();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void tips() throws Exception {
        Notice notice = mapper.getUtmost();
        String json = JSON.toJSONString(notice);
        Document doc = Jsoup.connect(url + "/uploadTips")
                .ignoreContentType(true).ignoreHttpErrors(true)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36 Edg/111.0.1661.41")
                .requestBody(json).timeout(30000).post();
        System.out.println(doc);
    }

    @Override
    public Notice get0(Integer id) {
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        queryWrapper.eq("state", 0);
        Notice notice = mapper.selectOne(queryWrapper);
        notice.setViews(notice.getViews() + 1);
        for (Notice n0 : notices) {
            if (n0.getId().intValue() == notice.getId().intValue()) {
                n0.setViews(notice.getViews());
            }
        }
        for (Notice n0 : notices2) {
            if (n0.getId().intValue() == notice.getId().intValue()) {
                n0.setViews(notice.getViews());
            }
        }
        mapper.updateById(notice);
        return notice;
    }

    @Override
    public boolean modify(Integer id, String passwd, String body) {
//        if (this.passwd.equals(passwd)) {
//            Notice notice = mapper.selectById(id);
//            notice.setHtml(body);
//            return mapper.updateById(notice) > 0;
//        }
        return false;
    }
}
