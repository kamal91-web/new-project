package com.foddez.service;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.foddez.service.Util.SharedPrefManager;

public class MainActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    Dialog popupDialig;
    Button closeImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        popupDialig=new Dialog(this);
        progressDialog = new ProgressDialog(this);

        if(!isConnected(getApplicationContext())){
            showPopup(getApplicationContext());
        }else {
            Thread thread=new Thread(){
                @Override
                public void run() {
                    try {
                        sleep(3000);
                        if (SharedPrefManager.getInstance(getApplicationContext()).isLogedIn()) {
                            startActivity(new Intent(getApplicationContext(), Home.class));
                            finish();
                            return;
                        } else {
                            startActivity(new Intent(getApplicationContext(), EnterMobile.class));
                            finish();
                            return;
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
        }
    }

    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();
        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if ((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting()))
                return true;
            else return false;
        } else return false;
    }
    public void showPopup(final Context b){
        popupDialig.setContentView(R.layout.popup_window_no_internet_connection);
        closeImage=popupDialig.findViewById(R.id.close_connection_btn);

        closeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        popupDialig.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupDialig.show();
        Window window=popupDialig.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        window.setGravity(Gravity.CENTER);
    }
}
