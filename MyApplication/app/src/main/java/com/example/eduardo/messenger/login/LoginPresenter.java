package com.example.eduardo.messenger.login;

import com.example.eduardo.messenger.login.events.LoginEvent;

/**
 * Created by Eduardo on 15/7/16.
 */
public interface LoginPresenter {
    void onCreate();
    void onDestroy();
    void checkForAuthenticatedUser();
    void validateLogin(String email, String password);
    void registerNewUser(String email, String password);
    void onEventMainThread(LoginEvent event);
}
