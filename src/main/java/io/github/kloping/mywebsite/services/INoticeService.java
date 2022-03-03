package io.github.kloping.mywebsite.services;

import io.github.kloping.mywebsite.entitys.Notice;

import java.util.List;

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
    List<Notice> get(int pn);

    /**
     * save a notice
     *
     * @param json
     * @return
     * @throws Throwable
     */
    boolean save(String json) throws Throwable;

    /**
     * get one by id
     *
     * @param id
     * @return
     */
    Notice get0(Integer id);
}
