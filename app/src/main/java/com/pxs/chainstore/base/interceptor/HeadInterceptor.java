package com.pxs.chainstore.base.interceptor;

import com.pxs.corelibrary.corelib.ConfigKeys;
import com.pxs.corelibrary.corelib.ProjectInit;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeadInterceptor {


  public static class TokenInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request.Builder builder = request.newBuilder()
                    .addHeader("token", "f916a9f5a59f1401ecf6426619b4184a");
            Request request1 = builder.build();
            return chain.proceed(request1);
        }
    }

    public static class TestTokenInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request.Builder builder = request.newBuilder()
                    .addHeader("token", "bbbbb");
            Request request1 = builder.build();
            return chain.proceed(request1);
        }
    }

}
