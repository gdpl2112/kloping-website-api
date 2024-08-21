package io.github.kloping.mywebsite.services;

/**
 * @author github-kloping
 */
public interface ISearchSong {
    /**
     * search songs
     *
     * @param keyword
     * @param num
     * @return
     * @throws Exception
     */
    Songs searchSong(String keyword, int num);
}

