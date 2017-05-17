package com.iesvdc.lolo.deruta.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.iesvdc.lolo.deruta.R;
import com.iesvdc.lolo.deruta.component.Dialog;
import com.iesvdc.lolo.deruta.manager.Manager;
import com.iesvdc.lolo.deruta.manager.UserManager;
import com.iesvdc.lolo.deruta.model.RequestError;
import com.iesvdc.lolo.deruta.model.User;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "LoginActivity";

    @InjectView(R.id.input_email) EditText _emailText;
    @InjectView(R.id.input_password) EditText _passwordText;
    @InjectView(R.id.btn_login) Button _loginButton;

    String userName, userPass;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        _loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                onLogin();
                break;
        }
    }

    private void onLogin(){
        Log.i(TAG, "onLogin()");

        userName = _emailText.getText().toString();
        userPass = _passwordText.getText().toString();

        UserManager.getInstance(this).login(userName, userPass, new Manager.Listener<User>() {
            @Override
            public void onSuccess(User data) {
                Log.e(getClass().getName(), "onLogin(): Login success " + data.getEmail());
                gotoDashboard();
            }

            @Override
            public void onCancel(RequestError error) {
                Log.e(getClass().getName(), "onLogin(): Login cancel " + error.getMessage());

                showDialog(getString(R.string.login), error.getMessage());
            }

            @Override
            public void onError(String error) {
                Log.e(getClass().getName(), "onLogin(): Login failed " + error);

                showDialog(getString(R.string.login), getString(R.string.login_fail));
            }
        });
    }

    private void gotoDashboard(){
        //TODO goto next activity
    }

    private void showDialog(String title, String content){
        Dialog dialog = new Dialog(this);
        dialog.show(title, content, getString(android.R.string.ok), null);
    }
}