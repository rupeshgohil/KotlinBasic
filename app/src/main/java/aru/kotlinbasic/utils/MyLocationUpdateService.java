package aru.kotlinbasic.utils;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;



import java.util.Timer;
import java.util.TimerTask;

@SuppressLint("Registered")
public class MyLocationUpdateService extends Service  {


    FusedLocationService mFusedLocationService;


    int counter = 0;
   /* private Handler handler;
    private Runnable r;*/
    Handler h = new Handler();
    int delay = 600000; //
    //int delay = 60000; //
    Runnable runnable;

    public MyLocationUpdateService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        startTimer();
        return START_STICKY;
    }




    @Nullable
    @Override
    public IBinder onBind(Intent intent) {


        return null;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();

        Intent broadcastIntent = new Intent("com.tutorialspoint.CUSTOM_INTENT");
        sendBroadcast(broadcastIntent);
        stoptimertask();
    }

    private Timer timer;
    private TimerTask timerTask;
    long oldTime = 0;

    public void startTimer() {
        //set a new Timer
        timer = new Timer();

        //initialize the TimerTask's job
        initializeTimerTask();
        timer.schedule(timerTask, 1500, 1500); //
    }

    /**
     * it sets the timer to print the counter every x seconds
     */
    public void initializeTimerTask() {
        timerTask = new TimerTask() {
            public void run() {

                if (counter % 60 == 0) {
                    counter=0;
                    Log.i("Gps Counter", "in timer Range ++++  " + (counter++));
                    Intent broadcastIntent = new Intent("com.tutorialspoint.CUSTOM_INTENT");
                    sendBroadcast(broadcastIntent);

                }

            }
        };
    }

    /**
     * not needed
     */
    public void stoptimertask() {
        //stop the timer, if it's not already null
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }





}
