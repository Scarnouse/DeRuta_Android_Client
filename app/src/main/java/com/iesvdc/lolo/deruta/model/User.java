package com.iesvdc.lolo.deruta.model;

import android.graphics.Bitmap;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class User {

    @SerializedName("id")
    private String id;

    @SerializedName("date_member")
    private String dateMember;

    @SerializedName("email")
    private String email;

    @SerializedName("first_name")
    private String firstName;

    private Bitmap userImage;

    /*private ArrayList<Route> routes;*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDateMember() {
        return dateMember;
    }

    public void setDateMember(String dateMember) {
        this.dateMember = dateMember;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Bitmap getUserImage() {
        return userImage;
    }

    public void setUserImage(Bitmap userImage) {
        this.userImage = userImage;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", dateMember='" + dateMember + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", userImage=" + userImage +
                '}';
    }
}
