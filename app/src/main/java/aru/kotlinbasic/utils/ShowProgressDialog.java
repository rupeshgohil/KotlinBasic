package aru.kotlinbasic.utils;

import android.app.Activity;
import android.app.ProgressDialog;

import aru.kotlinbasic.R;


public class ShowProgressDialog {
    static ProgressDialog progressDialog;

    public static  void ShowProgressDialog(Activity con){
         progressDialog = new ProgressDialog(con, R.style.ProgressDialog);

        if (!con.isFinishing()) {
            progressDialog.setCancelable(false);
            progressDialog.show();
            progressDialog.setContentView(R.layout.layout_progress_indicator);
        }
    }
    public static  void DismissDialog(){
        progressDialog.dismiss();
    }
}
