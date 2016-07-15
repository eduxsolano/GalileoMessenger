package com.example.eduardo.messenger.lib;

/**
 * Created by Eduardo on 15/7/16.
 */
public interface EventBus {
    void register(Object suscriber);
    void unregister(Object suscriber);
    void post(Object event);
}
