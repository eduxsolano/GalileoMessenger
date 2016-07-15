package com.example.eduardo.messenger.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.eduardo.messenger.ContactListActivity;
import com.example.eduardo.messenger.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView {

    @Bind(R.id.txtEmail)
    EditText txtEmail;
    @Bind(R.id.wrapperEmail)
    TextInputLayout wrapperEmail;
    @Bind(R.id.txtPassword)
    EditText txtPassword;
    @Bind(R.id.wrapperPassword)
    TextInputLayout wrapperPassword;
    @Bind(R.id.btnSignUp)
    Button btnSignUp;
    @Bind(R.id.btnSignIn)
    Button btnSignIn;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.mainContainer)
    RelativeLayout mainContainer;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        loginPresenter = new LoginPresenterImplementation(this);
        loginPresenter.onCreate();
        loginPresenter.checkForAuthenticatedUser();
    }

    @Override
    protected void onDestroy() {
        loginPresenter.onDestroy();
        super.onDestroy();
    }

    @OnClick({R.id.btnSignUp, R.id.btnSignIn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSignUp:
                handleSignUp();
                break;
            case R.id.btnSignIn:
                handleSignIn();

                break;
        }
    }

    @Override
    public void enabledInputs() {
        setInputs(true);
    }

    @Override
    public void disabledInputs() {
        setInputs(false);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void handleSignIn() {
        loginPresenter.validateLogin(txtEmail.getText().toString(),
                txtPassword.getText().toString());
    }

    @Override
    public void handleSignUp() {
        loginPresenter.registerNewUser(txtEmail.getText().toString(),
                txtPassword.getText().toString());
    }

    @Override
    public void navigateToMainScreen() {
        startActivity(new Intent(this, ContactListActivity.class));
    }

    @Override
    public void loginError(String error) {
        txtPassword.getText().clear();
        String msgError = String.format(getString(R.string.login_error_message_signin), error);
        txtPassword.setError(msgError);
    }

    @Override
    public void newUserSuccess() {
        Snackbar.make(mainContainer,R.string.login_notice_message_signup,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void newUserError(String error) {
        txtPassword.getText().clear();
        String msgError = String.format(getString(R.string.login_error_message_signup), error);
        txtPassword.setError(msgError);
    }

    private void setInputs(Boolean enabled) {
        txtEmail.setEnabled(enabled);
        txtPassword.setEnabled(enabled);
        btnSignIn.setEnabled(enabled);
        btnSignUp.setEnabled(enabled);
    }
}
