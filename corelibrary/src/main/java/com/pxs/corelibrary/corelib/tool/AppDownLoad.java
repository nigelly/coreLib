package com.pxs.corelibrary.corelib.tool;

import android.os.Environment;

import java.io.File;

public class AppDownLoad {


    public static final int NETWORK_ERROR = -1;

    public static final int DOWN_SUCCESS = 200;

    public static File getAvatarFile(String fileName) {
        // 使用 APP 内部储存空间
//        File appDir = new File(BaseApplication.getInstance()
//                .getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath(), "Avatar");

        // 这句是使用外部存储空间的
        File appDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "QR");

        if (!appDir.exists())
            appDir.mkdir();

        return new File(appDir, fileName);
    }

}
