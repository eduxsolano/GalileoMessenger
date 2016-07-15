package com.example.eduardo.messenger.login;

/**
 * Created by Eduardo on 15/7/16.
 */
public interface LoginView {
    void enabledInputs();
    void disabledInputs();
    void showProgress();
    void hideProgress();

    void handleSignIn();
    void handleSignUp();

    void navigateToMainScreen();
    void loginError(String error);

    void newUserSuccess();
    void newUserError(String error);
}
