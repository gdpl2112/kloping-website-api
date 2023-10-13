package io.github.kloping.mywebsite.services;

/**
 * @author github.kloping
 */
public interface ChatBotService {
    String ai(String msg, String id);

    String clear(String id);
}
