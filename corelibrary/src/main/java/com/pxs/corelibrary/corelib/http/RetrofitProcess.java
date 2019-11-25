package com.pxs.corelibrary.corelib.http;

import com.pxs.corelibrary.corelib.IHttpProcessor;
import com.pxs.corelibrary.corelib.OnSuccessAndFaultListener;
import com.pxs.corelibrary.corelib.http.rxcallback.DownHttpCallback;
import com.pxs.corelibrary.corelib.http.rxcallback.UploadProgressRequestBody;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;


public class RetrofitProcess implements IHttpProcessor {

    @Override
    public void rxPost(String url, Map<String, Object> map, final OnSuccessAndFaultListener listener) {
        RestClient.create().url(url)
                .parmas((HashMap<String, Object>) map)
                .listener(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(Object result) {
                        listener.onSuccess(result);
                    }

                    @Override
                    public void onFault(int code, String errorMsg) {
                        listener.onFault(code, errorMsg);
                    }
                })
                .build().post();
    }

    @Override
    public void rxPostRaw(String url, String body, final OnSuccessAndFaultListener listener) {
        RestClient.create().url(url)
                .body(body)
                .listener(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(Object result) {
                        listener.onSuccess(result);
                    }

                    @Override
                    public void onFault(int code, String errorMsg) {
                        listener.onFault(code, errorMsg);
                    }
                })
                .build().postRaw();
    }

    @Override
    public void get(String url, Map<String, Object> map, final OnSuccessAndFaultListener listener) {
        RestClient.create().url(url)
                .parmas((HashMap<String, Object>) map)
                .listener(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(Object result) {
                        listener.onSuccess(result);
                    }

                    @Override
                    public void onFault(int code, String errorMsg) {
                        listener.onFault(code, errorMsg);
                    }
                })
                .build().get();
    }

    @Override
    public void rxPost(String url, Map<String, Object> map, final OnSuccessAndFaultListener listener, Interceptor interceptor) {
        RestClient.create().url(url)
                .parmas((HashMap<String, Object>) map)
                .interceptor(interceptor)
                .listener(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(Object result) {
                        listener.onSuccess(result);
                    }

                    @Override
                    public void onFault(int code, String errorMsg) {
                        listener.onFault(code, errorMsg);
                    }
                })
                .build().post();
    }

    @Override
    public void rxPostRaw(String url, String body, final OnSuccessAndFaultListener listener, Interceptor interceptor) {
        RestClient.create().url(url)
                .body(body)
                .interceptor(interceptor)
                .listener(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(Object result) {
                        listener.onSuccess(result);
                    }

                    @Override
                    public void onFault(int code, String errorMsg) {
                        listener.onFault(code, errorMsg);
                    }
                })
                .build().postRaw();
    }

    @Override
    public void get(String url, Map<String, Object> map, final OnSuccessAndFaultListener listener, Interceptor interceptor) {
        RestClient.create().url(url)
                .parmas((HashMap<String, Object>) map)
                .interceptor(interceptor)
                .listener(new OnSuccessAndFaultListener() {
                    @Override
                    public void onSuccess(Object result) {
                        listener.onSuccess(result);
                    }

                    @Override
                    public void onFault(int code, String errorMsg) {
                        listener.onFault(code, errorMsg);
                    }
                })
                .build().get();
    }

    @Override
    public void rxDownload(String url, DownHttpCallback httpCallback,String fileName) {
        RestClient.create().url(url)
                .fileName(fileName)
                .listener(httpCallback).build().down();
    }

    @Override
    public void rxUpload(String url, UploadProgressRequestBody requestBody, final OnSuccessAndFaultListener listener) {
        RestClient.create().url(url)
                .body(requestBody)
                .listener(listener)
                .build().postFile();
    }


}
