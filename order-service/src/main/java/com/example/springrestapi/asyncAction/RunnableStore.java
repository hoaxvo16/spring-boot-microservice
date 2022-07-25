package com.example.springrestapi.asyncAction;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public final class RunnableStore {
    private static Map<String, Runnable> actions = new HashMap<>();

    @Transactional
    public static void runAction(String key) {
        actions.get(key).run();
        actions.remove(key);
    }

    public static void addAction(String key, Runnable action) {
        actions.put(key, action);
    }

    public static void removeAction(String key) {
        actions.remove(key);
    }

    public static void clearAction() {
        actions.clear();
    }

    public static void log() {
        for (String key : actions.keySet()) {
            System.out.println(key);
        }
    }

}
