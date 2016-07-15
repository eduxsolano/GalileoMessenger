package com.example.eduardo.messenger.login;

/**
 * Created by Eduardo on 15/7/16.
 */
public interface LoginInteractor {
    void checkSession();
    void doSignUp(String email, String password);
    void doSignIn(String email, String password);

}
