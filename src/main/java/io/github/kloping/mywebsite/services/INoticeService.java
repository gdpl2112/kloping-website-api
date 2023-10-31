package io.github.kloping.mywebsite.services;

import io.github.kloping.mywebsite.entitys.NoticePack;
import io.github.kloping.mywebsite.mapper.dao.Notice;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author github.kloping
 */
public interface INoticeService {
    /**
     * get pn page notices
     *
     * @param pn
     * @return
     */
    NoticePack get(int pn);

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
    Notice get0(Integer id);

    /**
     * simple
     *
     * @param pn
     * @return
     */
    NoticePack get1(Integer pn);

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
