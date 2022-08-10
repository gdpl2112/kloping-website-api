package io.github.kloping.mywebsite.services.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.kloping.mywebsite.entitys.Notice;
import io.github.kloping.mywebsite.entitys.NoticePack;
import io.github.kloping.mywebsite.mapper.NoticeMapper;
import io.github.kloping.mywebsite.services.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Override
    public NoticePack get(int pn) {
        pn--;
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state", 0);
        queryWrapper.orderByDesc("time");
        List<Notice> list = mapper.selectList(queryWrapper);
        List<Notice> list0 = new LinkedList<>();
        int i = pn * 5;
        while (true) {
            if (i >= list.size() || list0.size() >= 5) break;
            list0.add(list.get(i));
            i++;
        }
        NoticePack noticePack = new NoticePack();
        noticePack.setNotices(list0);
        noticePack.setPn(++pn);
        noticePack.setMax(list.size() / 5 + (list.size() % 5 > 0 ? 1 : 0));
        return noticePack;
    }

    @Override
    public NoticePack get1(Integer pn) {
        List<Notice> list = mapper.ln();
        List<Notice> list0 = new LinkedList<>();
        int i = pn * 5;
        while (true) {
            if (i >= list.size() || list0.size() >= 5) break;
            list0.add(list.get(i));
            i++;
        }
        NoticePack noticePack = new NoticePack();
        noticePack.setNotices(list0);
        noticePack.setPn(++pn);
        noticePack.setMax(list.size() / 5 + (list.size() % 5 > 0 ? 1 : 0));
        return noticePack;
    }

    @Value("${upload.passwd:123456}")
    String passwd;
    final SimpleDateFormat sf_0 = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");

    @Override
    public boolean save(String json) throws Throwable {
        JSONObject j = JSON.parseObject(json);
        if (j.getString("passwd").equals(passwd)) {
            String author = j.getString("name");
            String title = j.getString("title");
            Long id = Long.valueOf(j.get("id").toString());
            String html = j.get("code").toString();
            String icon = j.get("icon").toString();
            Notice notice = new Notice()
                    .setId(null)
                    .setAuthorId(id).setAuthorName(author)
                    .setState(0).setTime(System.currentTimeMillis())
                    .setDate(sf_0.format(new Date())).setTitle(title)
                    .setHtml(html).setIcon(icon);
            mapper.insert(notice);
            return true;
        }
        return false;
    }

    @Override
    public Notice get0(Integer id) {
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        Notice notice = mapper.selectOne(queryWrapper);
        notice.setViews(notice.getViews() + 1);
        mapper.updateById(notice);
        return notice;
    }

    @Override
    public boolean modify(Integer id, String passwd, String body) {
        if (this.passwd.equals(passwd)) {
            Notice notice = mapper.selectById(id);
            notice.setHtml(body);
            return mapper.updateById(notice) > 0;
        }
        return false;
    }
}
