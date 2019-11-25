package com.pxs.corelibrary.corelib.tool;

import android.content.Context;
import android.widget.Toast;

import com.pxs.corelibrary.corelib.ConfigKeys;
import com.pxs.corelibrary.corelib.ProjectInit;

public class Toaster {

    public static void show(String msg) {
        Toast.makeText((Context) ProjectInit.getConfiguration(ConfigKeys.APPLICATION_CONTEXT), msg, Toast.LENGTH_SHORT).show();
    }

}
