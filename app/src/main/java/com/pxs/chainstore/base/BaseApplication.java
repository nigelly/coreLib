package com.pxs.chainstore.base;

import android.app.Application;

import com.pxs.corelibrary.corelib.HttpHelper;
import com.pxs.corelibrary.corelib.ProjectInit;
import com.pxs.corelibrary.corelib.http.RetrofitProcess;

public class BaseApplication extends Application {

    //https://gss3.bdstatic.com/
    @Override
    public void onCreate() {
        super.onCreate();
        ProjectInit.init(this)
                .withApiHostSing("https://gss3.bdstatic.com/")
                .withAppIsDebug(true)
                .withApplicationContext(this)
                .build();
        HttpHelper.init(new RetrofitProcess());
    }
}
