package com.iesvdc.lolo.deruta.manager;

import android.content.Context;

import com.iesvdc.lolo.deruta.api.Api;
import com.iesvdc.lolo.deruta.model.RequestError;
import com.iesvdc.lolo.deruta.model.User;

public class UserManager extends Manager{

    //Singleton pattern
    private static UserManager instance;
    private Context context;

    User localUser;

    public UserManager(Context context){
        this.context = context;
    }

    public static UserManager getInstance(Context context){
        if(instance == null){
            instance = new UserManager(context);
        } else {
            instance.context = context;
        }

        return instance;
    }

    public boolean isLogged(){
        return localUser != null;
    }

    public User getUser() {
        return localUser;
    }

    public void login(String username, final String password, final Manager.Listener<User> listener){
        if(isLogged()){
            listener.onSuccess(localUser);
        } else {
            Api.getInstance(context).login(username, password, new Listener<User>() {
                @Override
                public void onSuccess(User data) {
                    localUser = data;
                    listener.onSuccess(localUser);
                }

                @Override
                public void onCancel(RequestError error) {
                    listener.onCancel(error);
                }

                @Override
                public void onError(String error) {
                    listener.onError(error);
                }
            });
        }
    }
}
