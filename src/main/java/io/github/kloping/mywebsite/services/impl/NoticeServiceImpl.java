package io.github.kloping.mywebsite.services.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {
    @Autowired
    NoticeMapper mapper;

    @Override
    public Page<Notice> gets(Integer pageNum) {
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state", 0);
        queryWrapper.select("id", "views", "title", "icon", "date", "time", "author_name", "author_id");
        queryWrapper.orderByDesc("time");
        Page<Notice> page = new Page<>(pageNum, 5);
        mapper.selectPage(page, queryWrapper);
        return page;
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
            Notice notice = new Notice().setId(null).setAuthorId(qid).setAuthorName(name)
                    .setState(1).setTime(System.currentTimeMillis()).setDate(sf_0.format(new Date()))
                    .setTitle(title).setHtml(body).setIcon(img).setType("md");
            save(notice);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            tips();
        }
    }

    private void tips() throws Exception {
        Notice notice = mapper.getUtmost();
        String json = JSON.toJSONString(notice);
        Document doc = Jsoup.connect(url + "/uploadTips").ignoreContentType(true).ignoreHttpErrors(true).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36 Edg/111.0.1661.41").requestBody(json).timeout(30000).post();
        System.out.println(doc);
    }


    @Override
    public Notice getOne(Integer id) {
        Notice notice = this.lambdaQuery().eq(Notice::getId, id).eq(Notice::getState, 0).one();
        if (notice != null) {
            notice.setViews(notice.getViews() + 1);
            updateById(notice);
        }
        return notice;
    }

    @Override
    public boolean modify(Integer id, String passwd, String body) {
        return false;
    }
}
