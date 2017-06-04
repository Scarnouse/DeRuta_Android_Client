package com.iesvdc.lolo.deruta.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.iesvdc.lolo.deruta.R;
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
                Log.i(getClass().getName(), "onLogin(): Login success " + data.getEmail());
                if(data.getId() == null){
                    showDialog(getString(R.string.error), getString(R.string.user_or_pass_wrong));
                } else {
                    gotoDashboard();
                }
            }

            @Override
            public void onCancel(RequestError error) {
                Log.e(getClass().getName(), "onLogin(): Login cancel " + error.getMessage());

                showDialog(getString(R.string.error), error.getMessage());
            }

            @Override
            public void onError(String error) {
                Log.e(getClass().getName(), "onLogin(): Login failed " + error);

                showDialog(getString(R.string.error), getString(R.string.login_fail));
            }
        });
    }

    private void gotoDashboard(){
        Log.i(TAG, "gotoDashboard()");
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void showDialog(String title, String content){
        AlertDialog.Builder builder;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle(title)
                .setMessage(content)
                .setCancelable(true)
                .show();
    }
}
