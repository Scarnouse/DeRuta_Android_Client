package com.iesvdc.lolo.deruta.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import butterknife.InjectView;

import com.iesvdc.lolo.deruta.R;
import com.iesvdc.lolo.deruta.component.Dialog;

public class BaseActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    @InjectView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void showProgress(String message){
        if(getApplicationContext() != null && progressDialog == null){
            progressDialog = new ProgressDialog(this);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage(message);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
    }

    public void showProgress(){
        showProgress(getString(R.string.wait));
    }

    protected void createToolbar(String title){
        if(toolbar != null){
            toolbar.setTitle(title);
            setSupportActionBar(toolbar);
            if(getSupportActionBar() != null){
                getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
    }

    public Toolbar getToolbar(){
        return toolbar;
    }

    public void hideProgress(){
        if(progressDialog != null){
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    public void showDialog(String title, String content){
        if(getApplicationContext() != null){
            Dialog dialog = new Dialog(this);
            dialog.show(title, content, getString(R.string.ok), null);
        }
    }

    public void showToast(String content){
        if(getApplicationContext() != null){
            Toast.makeText(getApplicationContext(), content, Toast.LENGTH_SHORT).show();
        }
    }

    public void showSnackbar(View rootView, String message){
        if(getApplicationContext() != null){
            showSnackbar(rootView, message, null, null);
        }
    }

    public void showSnackbar(View rootView, String title, String button, View.OnClickListener listener){
        if(listener != null){
            Snackbar.make(rootView, title, Snackbar.LENGTH_SHORT)
                    .setAction(button, listener)
                    .show();
        } else {
            Snackbar.make(rootView, title, Snackbar.LENGTH_SHORT)
                    .show();
        }
    }
}
