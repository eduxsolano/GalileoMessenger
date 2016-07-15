package com.example.eduardo.messenger.login;

import android.util.Log;

import com.example.eduardo.messenger.lib.EventBus;
import com.example.eduardo.messenger.lib.GreenRobotEventBus;
import com.example.eduardo.messenger.login.events.LoginEvent;

/**
 * Created by Eduardo on 15/7/16.
 */
public class LoginPresenterImplementation implements LoginPresenter {

    private EventBus eventBus;
    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImplementation(LoginView loginView) {
        this.loginView = loginView;
        this.loginInteractor =new LoginInteractorImplementation();
        this.eventBus = GreenRobotEventBus.getInstance();
    }


    @Override
    public void onCreate() {
       eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        loginView = null;
        eventBus.unregister(this);
    }

    @Override
    public void checkForAuthenticatedUser() {
        if (loginView != null){
            loginView.disabledInputs();
            loginView.showProgress();
        }
        loginInteractor.checkSession();
    }

    @Override
    public void validateLogin(String email, String password) {
        if (loginView != null){
            loginView.disabledInputs();
            loginView.showProgress();
        }
        loginInteractor.doSignIn(email, password);
    }

    @Override
    public void registerNewUser(String email, String password) {
        if (loginView != null){
            loginView.disabledInputs();
            loginView.showProgress();
        }
        loginInteractor.doSignUp(email, password);
    }

    @Override
    public void onEventMainThread(LoginEvent event) {
        switch (event.getEventType()){
            case LoginEvent.onSignInSuccess:
                onSignInSuccess();
                break;
            case LoginEvent.onSignInError:
                onSignInError(event.getErrorMessage());
                break;
            case LoginEvent.onSignUpSuccess:
                onSignUpSuccess();
                break;
            case LoginEvent.onSignUpError:
                onSignUpError(event.getErrorMessage());
                break;
            case LoginEvent.onFailedToRecoverSession:
                onFailedToRecoverSession();
                break;
        }
    }

    private void onFailedToRecoverSession(){
        if (loginView != null){
            loginView.hideProgress();
            loginView.enabledInputs();
        }
        Log.e("LoginPresenterImpl","onFailedToRecoverSession");
    }

    private void onSignInSuccess(){
        if (loginView != null){
            loginView.navigateToMainScreen();
        }
    }

    private void onSignUpSuccess(){
        if (loginView != null){
            loginView.newUserSuccess();
        }
    }
    private void onSignInError(String error){
        if (loginView != null){
            loginView.hideProgress();
            loginView.enabledInputs();
            loginView.loginError(error);
        }
    }
    private void onSignUpError(String error){
        if (loginView != null){
            loginView.hideProgress();
            loginView.enabledInputs();
            loginView.newUserError(error);
        }
    }

}
