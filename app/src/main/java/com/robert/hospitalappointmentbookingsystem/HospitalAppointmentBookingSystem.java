package com.robert.hospitalappointmentbookingsystem;

import android.app.Application;

import com.singhajit.sherlock.core.Sherlock;
import com.singhajit.sherlock.core.investigation.AppInfo;
import com.singhajit.sherlock.core.investigation.AppInfoProvider;


public class HospitalAppointmentBookingSystem  extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Sherlock.init(this); //Initializing Sherlock


//        Sherlock.setAppInfoProvider(new AppInfoProvider() {
//            @Override
//            public AppInfo getAppInfo() {
//                return new AppInfo.Builder()
//                        .with("Version", "2.21") //You can get the actual version using "AppInfoUtil.getAppVersion(context)"
//                        .with("BuildNumber", "221B")
//                        .build();
//            }
//        });

    }
}
