package com.fetch_rewards.fetchrewardsassessment.applications;


import android.app.Application;
import android.content.BroadcastReceiver;

import com.fetch_rewards.fetchrewardsassessment.utils.ConnectivityReceiver;

public class MyApplication extends Application {
    private static MyApplication mInstance;
 
    @Override
    public void onCreate() {
        super.onCreate();
 
        mInstance = this;
    }
 
    public static synchronized MyApplication getInstance() {
        return mInstance;
    }
 
    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}