package com.pxs.corelibrary.corelib;

import android.content.Context;

import java.util.HashMap;

import static com.pxs.corelibrary.corelib.ConfigKeys.API_HOST_SING;
import static com.pxs.corelibrary.corelib.ConfigKeys.API_HOST_SING_TEST;
import static com.pxs.corelibrary.corelib.ConfigKeys.API_HOST_TEST;
import static com.pxs.corelibrary.corelib.ConfigKeys.API_HOST_TEST_V2;
import static com.pxs.corelibrary.corelib.ConfigKeys.APPLICATION_CONTEXT;
import static com.pxs.corelibrary.corelib.ConfigKeys.APP_IS_DEBUG;
import static com.pxs.corelibrary.corelib.ConfigKeys.CONFIG_READY;
import static com.pxs.corelibrary.corelib.ConfigKeys.TOKEN;


public class Configurator {

    private static final HashMap<Object, Object> CONFIGS = new HashMap<>();

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public Configurator() {
        //初始化配置信息
        CONFIGS.put(CONFIG_READY, false);
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    final HashMap<Object, Object> getConfigs() {
        return CONFIGS;
    }

    //配置全局上下文
    public final Configurator withApplicationContext(Context context) {
        CONFIGS.put(APPLICATION_CONTEXT, context);
        return this;
    }

    //配置正式区主机地址
    public final Configurator withApiHostSing(String url) {
        CONFIGS.put(API_HOST_SING, url);
        return this;
    }

    //配置正式区测试环境主机地址
    public final Configurator withApiHostSingTest(String url) {
        CONFIGS.put(API_HOST_SING_TEST, url);
        return this;
    }

    //配置测试区主机地址
    public final Configurator withApiHostTest(String url) {
        CONFIGS.put(API_HOST_TEST, url);
        return this;
    }

    //配置测试区开发环境主机地址
    public final Configurator withApiHostTestV2(String url) {
        CONFIGS.put(API_HOST_TEST_V2, url);
        return this;
    }

    //配置APP是否是debug调试模式
    public final Configurator withAppIsDebug(boolean isDebug) {
        CONFIGS.put(APP_IS_DEBUG, isDebug);
        return this;
    }

    //配置完成
    public final void build() {
        CONFIGS.put(CONFIG_READY, true);
    }

    //检测是否配置完毕
    private void checkConfiguration() {
        final boolean isReady = (boolean) CONFIGS.get(CONFIG_READY);
        if (!isReady) {
            new RuntimeException("CONFIGS IS NO READY");
        }
    }

    //配置Token
    public final Configurator withToken(String token) {
        CONFIGS.put(TOKEN, token);
        return this;
    }

    public final <T> T getConfigValue(Object key) {
        checkConfiguration();
        final Object value = CONFIGS.get(key);
        if (key.equals(TOKEN)) {
            return (T) (value == null ? "" : value);
        }
        if (value == null) {
            new NullPointerException("value is null");
        }
        return (T) value;
    }

}
