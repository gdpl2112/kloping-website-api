package io.github.kloping.mywebsite.broadcast;

import io.github.kloping.object.ObjectUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author github-kloping
 */
public abstract class Broadcast {
    public static final ExecutorService SERVICE = Executors.newFixedThreadPool(10);
    public static final Map<Class<? extends Broadcast>, Broadcast> CLASS_BROADCAST_MAP = new ConcurrentHashMap<>();
    public static final Map<String, Broadcast> ID_2_BROADCASTS = new ConcurrentHashMap<>();
    public static final LinkedHashSet<Receiver> RECEIVERS = new LinkedHashSet<>();
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

    public static void ifIsRunElseJump(Object instance, Method method, Object[] objects) {
        if (!method.isAccessible()) method.setAccessible(true);
        if (objects.length == method.getParameterCount()) {
            Class<?>[] cls = method.getParameterTypes();
            cls = ObjectUtils.baseToPack(cls);
            objects = ObjectUtils.baseToPack(objects);
            for (int i = 0; i < cls.length; i++) {
                if (cls[i] != objects[i].getClass())
                    return;
            }
            try {
                method.invoke(instance, objects);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean add(Receiver receiver) {
        return RECEIVERS.add(receiver);
    }

    public boolean remove(Receiver receiver) {
        return RECEIVERS.remove(receiver);
    }

    public final void broadcast(Object... objects) {
        ifIsRunElseJump(this, method, objects);
    }

    protected void setId(String id) {
        this.id = id;
    }
}
