package com.example.eduardo.messenger.login;

/**
 * Created by Eduardo on 15/7/16.
 */
public class LoginInteractorImplementation implements LoginInteractor {
    private LoginRepository loginRepository;

    public LoginInteractorImplementation() {
        loginRepository = new LoginRepositoryImplementation();
    }

    @Override
    public void checkSession() {
        loginRepository.checkAlreadyAuthenticated();
    }

    @Override
    public void doSignUp(String email, String password) {
        loginRepository.signUp(email,password);
    }

    @Override
    public void doSignIn(String email, String password) {
        loginRepository.signIn(email, password);
    }
}
