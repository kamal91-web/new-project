package com.foddez.service.Util;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.foddez.service.R;

public class SuccessDialog {
    private Activity activity;
    private AlertDialog dialog;
    private TextView txtSuccessMessage;
    public SuccessDialog(Activity myActivity){
        activity=myActivity;
    }

    public void startSuccessDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(activity);

        LayoutInflater inflater=activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.success_popup,null));
        builder.setCancelable(true);
        dialog=builder.create();

        dialog.show();
    }
    public void closeSuccessDilog(){
        dialog.dismiss();
    }
}
