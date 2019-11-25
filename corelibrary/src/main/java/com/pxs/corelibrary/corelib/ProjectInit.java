package com.pxs.corelibrary.corelib;

import android.content.Context;

import com.bumptech.glide.request.RequestOptions;

public class ProjectInit {

    public static Configurator init(Context context) {
        Configurator.getInstance().withApplicationContext(context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return Configurator.getInstance().getConfigValue(key);
    }


    public static RequestOptions configRequestOptions(){
        RequestOptions requestOptions = new RequestOptions();
       // requestOptions.transform(new GlideRoundTransform(5));
      //  requestOptions.transform(new RoundedCorners(5));
        return requestOptions;
    }

    public static boolean isDeBug = true;

    public static boolean isLogin = false;


}
