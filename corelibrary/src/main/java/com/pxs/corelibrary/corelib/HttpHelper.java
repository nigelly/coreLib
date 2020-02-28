package com.pxs.corelibrary.corelib;

import com.pxs.corelibrary.corelib.http.rxcallback.DownHttpCallback;
import com.pxs.corelibrary.corelib.http.rxcallback.UploadProgressRequestBody;

import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.RequestBody;


public class HttpHelper implements IHttpProcessor {

    private static final class HttpHelperHolder {
        private static final HttpHelper INSTANCE = new HttpHelper();
    }

    public static HttpHelper getInstance() {
        return HttpHelperHolder.INSTANCE;
    }

    public HttpHelper() {
    }

    private static IHttpProcessor mHttpProcessor;

    public static void init(IHttpProcessor iHttpProcessor) {
        mHttpProcessor = iHttpProcessor;
    }


    @Override
    public void rxPost(String url, Map<String, Object> map, OnSuccessAndFaultListener listener) {
        mHttpProcessor.rxPost(url, map, listener);
    }

    @Override
    public void rxPostRaw(String url, String body, OnSuccessAndFaultListener listener) {
        mHttpProcessor.rxPostRaw(url, body, listener);
    }

    @Override
    public void get(String url, Map<String, Object> body, OnSuccessAndFaultListener listener) {
        mHttpProcessor.get(url, body, listener);
    }

    @Override
    public void rxPost(String url, Map<String, Object> map, OnSuccessAndFaultListener listener, Interceptor interceptor) {
        mHttpProcessor.rxPost(url, map, listener, interceptor);
    }

    @Override
    public void rxPostRaw(String url, String body, OnSuccessAndFaultListener listener, Interceptor interceptor) {
        mHttpProcessor.rxPostRaw(url, body, listener, interceptor);
    }

    @Override
    public void get(String url, Map<String, Object> map, OnSuccessAndFaultListener listener, Interceptor interceptor) {
        mHttpProcessor.get(url, map, listener, interceptor);
    }

    @Override
    public void rxDownload(String url, DownHttpCallback downHttpCallback,String fileName) {
        mHttpProcessor.rxDownload(url, downHttpCallback,fileName);
    }

    @Override
    public void rxUpload(String url, RequestBody requestBody, OnSuccessAndFaultListener listener) {
        mHttpProcessor.rxUpload(url,requestBody,listener);
    }


}
