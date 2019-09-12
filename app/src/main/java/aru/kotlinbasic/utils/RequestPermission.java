package aru.kotlinbasic.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class RequestPermission {

    public static final String PERMISSION_CAMERA = Manifest.permission.CAMERA;
    // Permission Common Request Code
    public static final int REQUEST_CODE_PERMISSION = 143;
    public static final String PERMISSION_CALL = Manifest.permission.CALL_PHONE;
    public static final String PERMISSION_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    public static final String PERMISSION_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    public static final String PERMISSION_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    public static final String PERMISSION_READ_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    public static final String PERMISSION_WRITE_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private final String TAG = "Request Permission";
    private final Activity m_Activity;
    private PermissionCallBack mPermissionCallBack;

    public RequestPermission(Activity activity, boolean isFragment) {
        m_Activity = activity;
    }


    public boolean checkPermission(String permissionName) {
        return ContextCompat.checkSelfPermission(m_Activity, permissionName) == PackageManager.PERMISSION_GRANTED;
    }


    public void permissionRequestShow(PermissionCallBack callBack, String... permissionName) {
        mPermissionCallBack = callBack;
        ActivityCompat.requestPermissions(m_Activity, permissionName, REQUEST_CODE_PERMISSION);
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Helper.logDisplay(requestCode + " <---> " + REQUEST_CODE_PERMISSION);
        if (requestCode == REQUEST_CODE_PERMISSION) {
            boolean grantAllPermission = true;
            List<Boolean> permissionResultList = new ArrayList<>();
            boolean deniedAllPermission = true;

            for (int grantResult : grantResults) {
                if (grantResult == PackageManager.PERMISSION_GRANTED) {
                    permissionResultList.add(true);
                    deniedAllPermission = false;
                } else {
                    permissionResultList.add(false);
                    grantAllPermission = false;
                }
            }
            mPermissionCallBack.callBack(grantAllPermission, deniedAllPermission, permissionResultList);
            mPermissionCallBack = null;
        }
    }

    /**
     * This used when you nedd the callback function.
     */
    public interface PermissionCallBack {
        /**
         * Permission callback function.
         *
         * @param grantAllPermission   :- all permission are granted or not.
         * @param deniedAllPermission  :- all permission are denied or not.
         * @param permissionResultList :- its all result in boolean value in squall where inserted.
         */
        void callBack(boolean grantAllPermission, boolean deniedAllPermission, List<Boolean> permissionResultList);
    }

}
