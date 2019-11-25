package com.pxs.corelibrary.corelib.tool;

import android.util.Log;

import com.pxs.corelibrary.corelib.ProjectInit;

import static com.pxs.corelibrary.corelib.ConfigKeys.APP_IS_DEBUG;

public class Logger {

    public static void print(String msg) {
        if (ProjectInit.getConfiguration(APP_IS_DEBUG)) {
            Log.e("print", msg);
        }
    }

}
