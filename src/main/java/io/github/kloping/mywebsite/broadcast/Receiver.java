package io.github.kloping.mywebsite.broadcast;

/**
 * @author github-kloping
 */
public interface Receiver {
    /**
     * on received call method
     *
     * @param o
     */
    default void onReceive(Object o) {
    }
}
