package com.example.eduardo.messenger.login;

/**
 * Created by Eduardo on 15/7/16.
 */
public interface LoginRepository {
    void signUp(String email, String password);
    void signIn(String email, String password);
    void checkAlreadyAuthenticated();
}
