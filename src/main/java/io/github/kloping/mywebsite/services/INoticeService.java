package io.github.kloping.mywebsite.services;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.kloping.mywebsite.domain.po.Notice;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author github.kloping
 */
public interface INoticeService extends IService<Notice> {
    /**
     * get pn page notices
     *
     * @param pn
     * @param pageNum
     * @return
     */
    Page<Notice> gets(Integer pageNum);

    /**
     * save a notice
     *
     * @param img
     * @param title
     * @param body
     * @param userDetails
     * @return
     * @throws Throwable
     */
    boolean save(String img, String title, String body, UserDetails userDetails) throws Throwable;

    /**
     * get one by id
     *
     * @param id
     * @return
     */
    Notice getOne(Integer id);

    /**
     * 修改内容
     *
     * @param id
     * @param passwd
     * @param body
     * @return
     */
    boolean modify(Integer id, String passwd, String body);
}
