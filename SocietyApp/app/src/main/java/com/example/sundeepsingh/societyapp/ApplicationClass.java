package com.example.sundeepsingh.societyapp;

import android.app.Application;

import com.onesignal.OneSignal;

public class ApplicationClass extends Application {
    public void onCreate() {
        super.onCreate();
        // TODO: Add OneSignal initialization here

        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
    }

}
