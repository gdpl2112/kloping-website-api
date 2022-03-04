package io.github.kloping.mywebsite.services.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.kloping.mywebsite.entitys.Notice;
import io.github.kloping.mywebsite.mapper.NoticeMapper;
import io.github.kloping.mywebsite.services.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Collections;
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
    public List<Notice> get(int pn) {
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state", 0);
        List<Notice> list = mapper.selectList(queryWrapper);
        List<Notice> list0 = new LinkedList<>();
        int i = pn * 5;
        while (true) {
            if (i >= list.size() || list0.size() >= 5) break;
            list0.add(list.get(i));
            i++;
        }
        Collections.reverse(list0);
        return list0;
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
            html = filterCode(html);
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

    private String filterCode(String html) {
        html = html.replaceAll("&lt;del&gt;", "<del>");
        html = html.replaceAll("&lt;/del&gt;", "</del>");
        return html;
    }

    @Override
    public Notice get0(Integer id) {
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        Notice notice = mapper.selectOne(queryWrapper);
        return notice;
    }
}
