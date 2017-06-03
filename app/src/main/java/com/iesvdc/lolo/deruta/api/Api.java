package com.iesvdc.lolo.deruta.api;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.iesvdc.lolo.deruta.manager.Manager;
import com.iesvdc.lolo.deruta.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class Api {
    private static Api instance;
    private Context context;
    private final String HOST =  "http://10.0.2.2:8000/app_dev.php/api/v1";

    private Api(Context context){
        this.context = context;
    }

    public static Api getInstance(Context context){
        if(instance == null){
            instance = new Api(context);
        }
        return instance;
    }

    public void login(String email, String password, final Manager.Listener<User> listener){
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            String URL = HOST + "/user/login";
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("email", email);
            jsonBody.put("password", password);
            final String mRequestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST,
                    URL,
                    new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("LOG_VOLLEY_RESPONSE", response);

                    Gson gson = new Gson();
                    User user = gson.fromJson(response, User.class);
                    Log.i("USER", user.toString());

                    listener.onSuccess(user);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("LOG_VOLLEY_ERROR", error.toString());

                    listener.onError(error.toString());
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Codificación no permitida al intentar obtener los bytes de %s usando %s", mRequestBody, "utf-8");
                        return null;
                    }
                }
            };

            requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getImageProfile(final User user, final Manager.Listener<Bitmap> listener){

    }
}
