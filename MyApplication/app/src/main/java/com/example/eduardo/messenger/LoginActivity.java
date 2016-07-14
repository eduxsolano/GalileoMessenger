package com.example.eduardo.messenger;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity
{

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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btnSignUp, R.id.btnSignIn})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btnSignUp:
                Toast.makeText(this,"Hice click en sign up",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnSignIn:
                Toast.makeText(this,"Hice click en sign in",Toast.LENGTH_SHORT).show();

                break;
        }
    }
}
