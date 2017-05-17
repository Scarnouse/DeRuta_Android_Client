package com.iesvdc.lolo.deruta.manager;

import com.iesvdc.lolo.deruta.model.RequestError;

public class Manager {

    public interface Listener<T>{
        void onSuccess(T data);
        void onCancel(RequestError error);
        void onError(String error);
    }
}
