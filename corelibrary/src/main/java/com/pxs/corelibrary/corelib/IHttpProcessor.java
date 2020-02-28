package com.pxs.corelibrary.corelib;

import com.pxs.corelibrary.corelib.http.rxcallback.DownHttpCallback;
import com.pxs.corelibrary.corelib.http.rxcallback.UploadProgressRequestBody;

import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.RequestBody;


public interface IHttpProcessor {

    void rxPost(String url, Map<String, Object> map, OnSuccessAndFaultListener listener);

    void rxPostRaw(String url, String body, OnSuccessAndFaultListener listener);

    void get(String url, Map<String, Object> map, OnSuccessAndFaultListener listener);

    void rxPost(String url, Map<String, Object> map, OnSuccessAndFaultListener listener, Interceptor interceptor);

    void rxPostRaw(String url, String body, OnSuccessAndFaultListener listener, Interceptor interceptor);

    void get(String url, Map<String, Object> map, OnSuccessAndFaultListener listener, Interceptor interceptor);

    void rxDownload(String url, DownHttpCallback downHttpCallback,String fileName);

    void rxUpload(String url, RequestBody requestBody, OnSuccessAndFaultListener listener);
}
