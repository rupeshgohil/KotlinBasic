package aru.kotlinbasic.utils;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Button;


//import com.google.android.gms.location.FusedLocationProviderClient;
//import com.google.android.gms.location.LocationCallback;
//import com.google.android.gms.location.LocationRequest;
//import com.google.android.gms.location.LocationResult;
//import com.google.android.gms.location.LocationServices;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;



public class FusedLocationService implements LifecycleObserver {

//    private String filename = "SampleFile.txt";
//    private String filepath = "MyFileStorage";
//    File myExternalFile;
//
//    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 1000;
//    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 100;
//    public boolean isGPSEnabled = false;
//    Context mContext;
//    Location mLocation;
//    private FusedLocationProviderClient mFusedLocationClient;
//    private LocationCallback mLocationCallback;
//    private LocationRequest mLocationRequest;
//    private AlertDialog.Builder alertDialog;
//    private LocationManager locationManager;
//
//    public FusedLocationService(Lifecycle lifecycle, Context mContext) {
//        this.mContext = mContext;
//        // Starts lifecycle observation
//        lifecycle.addObserver(this);
//    }
//
//    public FusedLocationService(Context mContext) {
//        this.mContext = mContext;
//    }
//
//
//
//    @SuppressLint("RestrictedApi")
//    private void createLocationRequest() {
//        mLocationRequest = new LocationRequest();
//
//        // Sets the desired interval for active location updates. This interval is
//        // inexact. You may not receive updates at all if no location sources are available, or
//        // you may receive them slower than requested. You may also receive updates faster than
//        // requested if other applications are requesting location at a faster interval.
//        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
//
//        // Sets the fastest rate for active location updates. This interval is exact, and your
//        // application will never receive updates faster than this value.
//        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
//
//        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//    }
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
//    public void startLocationUpdates() {
//        setFusedLocationClient(false);
//    }
//
//    public void setFusedLocationClient(boolean isShowSetting) {
//        if (locationManager == null)
//            locationManager = (LocationManager) mContext
//                    .getSystemService(mContext.LOCATION_SERVICE);
//        // getting GPS status
//        isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//        if (ActivityCompat.checkSelfPermission(mContext, PermissionUtils.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, PermissionUtils.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            return;
//        }
//        if (isGPSEnabled) {
//            if (mLocationCallback == null)
//                if (isGPSEnabled) {
//                    mLocationCallback = new LocationCallback() {
//                        @Override
//                        public void onLocationResult(LocationResult locationResult) {
//                            for (Location location : locationResult.getLocations()) {
//                                if (location != null) {
//                                    mLocation = location;
//                                    updateLocation(location);
//                                    Helper.logDisplay("Lat :- " + mLocation.getLatitude() +
//                                            "\nLong :-" + mLocation.getLongitude());
//
//
//
//                                }
//                            }
//                        }
//                    };
//                    if (mFusedLocationClient == null)
//                        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(mContext);
//
//                    if (mLocationRequest == null)
//                        createLocationRequest();
//
//                    mFusedLocationClient.requestLocationUpdates(mLocationRequest,
//                            mLocationCallback,
//                            null /* Looper */);
//                }
//        } else if (isShowSetting)
//            showSettingsAlert();
//    }
//
//    public Location getLocation() {
//        return mLocation;
//    }
//
//    public void updateLocation(Location location) {
//    }
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
//    public void onPause() {
//        if (null != mFusedLocationClient)
//            mFusedLocationClient.removeLocationUpdates(mLocationCallback);
//    }
//
//    /**
//     * Function to show settings alert dialog
//     * On pressing Settings button will lauch Settings Options
//     */
//    public void showSettingsAlert() {
//        if (alertDialog != null)
//            alertDialog = null;
//        alertDialog = new AlertDialog.Builder(mContext);
//
//        // Setting Dialog Title
//        alertDialog.setTitle("GPS is settings");
//
//        // Setting Dialog Message
//        alertDialog.setMessage("GPS is not enabled or location mode not in high accuracy. Do you want to go to settings menu.?");
//
//        // On pressing Settings button
//        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                mContext.startActivity(intent);
//                dialog.cancel();
//
//                //Intent intent1 = new Intent("pay");
//                //LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent1);
//            }
//        });
//        // on pressing cancel button
//        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                cancelSettingScreen();
//                dialog.cancel();
//            }
//        });
//
//        AlertDialog alert = alertDialog.create();
//        alert.show();
//        Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
//        nbutton.setTextColor(ContextCompat.getColor(mContext, R.color.black));
//        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
//        pbutton.setTextColor(ContextCompat.getColor(mContext, R.color.black));
//    }
//
//    public void cancelSettingScreen() {
//
//    }
//
//    public  void savefile(String str)
//    {
//
//        try {
//            myExternalFile = new File(mContext.getExternalFilesDir(filepath), filename);
//            FileOutputStream fos = new FileOutputStream(myExternalFile);
//            fos.write(str.getBytes());
//            fos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}
