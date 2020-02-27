package com.pxs.corelibrary.corelib.http;


import com.pxs.corelibrary.corelib.ConfigKeys;
import com.pxs.corelibrary.corelib.ProjectInit;
import com.pxs.corelibrary.corelib.tool.Logger;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class RestCreator {
    private static String simpleName = "";

    private static class RetrofitHolder {

        private static String BASE_URL = ProjectInit.getConfiguration(ConfigKeys.API_HOST_SING);

        private static Retrofit RETROFIT_CLIENT = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(OkHttpHolder.OK_HTTP_CLIENT())
                .build();

        private static Retrofit RETROFIT_CUSTOM_INTERCEPTOR(Interceptor interceptor) {
            return new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(OkHttpHolder.OK_HTTP_CLIENT_INTERCEPTOR(interceptor))
                    .build();
        }
    }

    //单独配置okhttp
    private static class OkHttpHolder {

        private static final int TIME_OUT = 60;

        private static OkHttpClient client = null;

        private static OkHttpClient clientInterceptor = null;


        private static OkHttpClient OK_HTTP_CLIENT() {
            if (client == null) {
                return client = new OkHttpClient.Builder()
                        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                        .build();
            }
            return client;
        }

        private static OkHttpClient OK_HTTP_CLIENT_INTERCEPTOR(Interceptor interceptor) {
            if (interceptor != null) {//第一次进来 记录下 拦截器的类名
                if (interceptor.getClass().getSimpleName().equals(simpleName)) {
                    //如果是一样的 那就直接返回原来的
                    if (clientInterceptor == null) {//如果为空 那么重新创建一个
                        return clientInterceptor = new OkHttpClient.Builder()
                                .addInterceptor(interceptor)
                                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                                .build();
                    }
                    Logger.print("use old interceptor");
                    return clientInterceptor;
                } else {
                    Logger.print("use new interceptor");
                    simpleName = interceptor.getClass().getSimpleName();
                    return clientInterceptor = new OkHttpClient.Builder()
                            .addInterceptor(interceptor)
                            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                            .build();
                }
            } else {//如果拦截器为空 那么直接创建一个新的客户端
                simpleName = "";
                return clientInterceptor = new OkHttpClient.Builder()
                        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                        .build();
            }
        }

    }

    //提供一个接口让调用者得到retrofit对象
    private static class RestServiceHolder {

        private static RestService REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);

        private static RestService getRestServiceInterceptor(Interceptor interceptor) {
            return RetrofitHolder.RETROFIT_CUSTOM_INTERCEPTOR(interceptor).create(RestService.class);
        }
    }

    //获取对象
    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }

    //定义一个自定义拦截器
    public static RestService getRestService(Interceptor interceptor) {
        return RestServiceHolder.getRestServiceInterceptor(interceptor);
    }


}
