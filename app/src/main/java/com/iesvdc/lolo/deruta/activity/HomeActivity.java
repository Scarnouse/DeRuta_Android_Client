package com.iesvdc.lolo.deruta.activity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import butterknife.InjectView;

import com.iesvdc.lolo.deruta.R;
import com.iesvdc.lolo.deruta.manager.Manager;
import com.iesvdc.lolo.deruta.manager.UserManager;
import com.iesvdc.lolo.deruta.model.RequestError;
import com.iesvdc.lolo.deruta.model.User;

public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    @InjectView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @InjectView(R.id.homeDrawer) NavigationView drawer;
    @InjectView(R.id.drawerHeaderText) TextView drawerHeaderText;
    private ProgressDialog progressDialog;
    private View drawerHeader;
    private Fragment currentFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //createUI();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String target = bundle.getString("target");
        } else {
            //onHomeClick();
        }
    }

    private void createUI(){
        createToolbar(getString(R.string.app_name));
        final ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeAsUpIndicator(R.drawable.drawer_toogle);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        if(UserManager.getInstance(getApplicationContext()).getUser() == null){
            finish();
        }

        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        drawer.setNavigationItemSelectedListener(this);

        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

        fillDrawer();
    }

    private void fillDrawer(){
        requestUserImage(UserManager.getInstance(getApplicationContext()).getUser());

        drawerHeader = drawer.getHeaderView(0).findViewById(R.id.drawer_header);
        if(drawerHeader != null){
            drawerHeader.setOnClickListener(this);

            if(drawerHeaderText != null){
                drawerHeaderText.setText(UserManager.getInstance(getApplicationContext()).getUser().getFirstName());
            }
        }
    }

    private void requestUserImage(User user){
        UserManager.getInstance(getApplicationContext()).getImageProfile(user, new Manager.Listener<Bitmap>() {
            @Override
            public void onSuccess(Bitmap data) {

            }

            @Override
            public void onCancel(RequestError error) {

            }

            @Override
            public void onError(String error) {

            }
        });
        //TODO How to call an upload image from symfony backen in android?
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onClick(View v) {

    }
}
