package com.foddez.service.Util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    private static SharedPrefManager mInstance;
    private static Context mCtx;
    private static final String SHARED_PREF_NAME="mysharedpref123";
    private static final String KEY_USER_MOBILE="usermobile";
    private static final String KEY_AREA_ID="areaid";
    private static final String KEY_AREA_NAME="areanm";
    private static final String KEY_CITY_NAME="citynm";
    private static final String KEY_ADDRESS_TYPE="addresstype";
    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public boolean userLogin(String umobileno,String areaid,String areanm,String citynm,String addresstype){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(KEY_USER_MOBILE,umobileno);
        editor.putString(KEY_AREA_ID,areaid);
        editor.putString(KEY_AREA_NAME,areanm);
        editor.putString(KEY_CITY_NAME,citynm);
        editor.putString(KEY_ADDRESS_TYPE,addresstype);
        editor.apply();
        return true;
    }
    public boolean isLogedIn(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_USER_MOBILE,null)!=null){
            return true;
        }
        return false;
    }
    public boolean logOut(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }
    public String getUserMobile(){
        SharedPreferences spm=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return spm.getString(KEY_USER_MOBILE,null);
    }
    public String getAreaId(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_AREA_ID,null);
    }
    public String getAreaName(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_AREA_NAME,null);
    }
    public String getCityName(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_CITY_NAME,null);
    }
    public String getAddressType(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_ADDRESS_TYPE,null);
    }
}
