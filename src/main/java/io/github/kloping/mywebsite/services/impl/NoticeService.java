package io.github.kloping.mywebsite.services.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.kloping.mywebsite.domain.po.Notice;
import io.github.kloping.mywebsite.domain.po.UserTemp;
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

/**
 * @author github.kloping
 */
@Service
public class NoticeService implements INoticeService {
    @Autowired
    NoticeMapper mapper;

    @Override
    public Notice[] gets() {
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state", 0);
        queryWrapper.select("id", "views", "title", "icon", "date", "time", "author_name", "author_id");
        Notice[] notices = mapper.selectList(queryWrapper).toArray(new Notice[0]);
        return notices;
    }


    final SimpleDateFormat sf_0 = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");

    @Value("${bot.url}")
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
    public Notice getOne(Integer id) {
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        queryWrapper.eq("state", 0);
        Notice notice = mapper.selectOne(queryWrapper);
        if (notice != null) {
            notice.setViews(notice.getViews() + 1);
            mapper.updateById(notice);
            return notice;
        } else return null;
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
