package com.tubili.dokubank;

import com.google.android.gms.maps.model.LatLng;
import com.tubili.dokubank.Model.Demand;
import com.tubili.dokubank.Model.User;

public class Common {
    public static User currentUser;
    public static Demand selectedDemand;
    public static LatLng latLng;

    //public static Request currentRequest;
    public final static String UPDATE = "Güncelle";
    public final static String DELETE = "Sil";
    public final static String USER_PHONE = "KullaniciNo";
    public final static String USER_PASSWORD = "KullanıcıParola";
    public final static String USER_USERNAME = "KullanıcıAdı";
    public final static String USER_NAME = "Ad";
    public final static String CLIENT = "client";
    public final static String SERVER = "server";
}
