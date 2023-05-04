package io.github.kloping.mywebsite.broadcast;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author github-kloping
 */
public abstract class Broadcast<T extends Receiver> {
    public static final ExecutorService SERVICE = Executors.newFixedThreadPool(10);
    public static final Map<Class<? extends Broadcast>, Broadcast> CLASS_BROADCAST_MAP = new ConcurrentHashMap<>();
    public static final Map<String, Broadcast> ID_2_BROADCASTS = new ConcurrentHashMap<>();
    protected String id;
    private Method method;

    public Broadcast(String id) {
        this.id = id;
        CLASS_BROADCAST_MAP.put(this.getClass(), this);
        ID_2_BROADCASTS.put(id, this);
        for (Method declaredMethod : this.getClass().getDeclaredMethods()) {
            if ("broadcast".equals(declaredMethod.getName())) {
                if (declaredMethod.getParameterCount() >= 1) {
                    if (declaredMethod.getParameterTypes()[0] != Object[].class) {
                        method = declaredMethod;
                        break;
                    }
                }
            }
        }
    }

    protected void submit(Runnable runnable) {
        SERVICE.submit(runnable);
    }

    public abstract boolean add(T receiver);

    public abstract boolean remove(T receiver);
}
