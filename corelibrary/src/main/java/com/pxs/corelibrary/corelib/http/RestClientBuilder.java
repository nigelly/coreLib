package com.pxs.corelibrary.corelib.http;


import com.pxs.corelibrary.corelib.OnSuccessAndFaultListener;

import java.io.File;
import java.util.HashMap;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RestClientBuilder {
    private HashMap<String, Object> mParmas;
    private String mUrl;
    private RequestBody mBody;
    private File mFile;


    private String mDownloadDir;
    private String mExtension;
    private String mFileName;
    private OnSuccessAndFaultListener listener;

    private Interceptor interceptor;


    public RestClientBuilder() {
    }

    public final RestClientBuilder listener(OnSuccessAndFaultListener listener) {
        this.listener = listener;
        return this;
    }

    public final RestClientBuilder extension(String mExtension) {
        this.mExtension = mExtension;
        return this;
    }

    public final RestClientBuilder fileName(String mFileName) {
        this.mFileName = mFileName;
        return this;
    }

    public final RestClientBuilder downloadDir(String mDownloadDir) {
        this.mDownloadDir = mDownloadDir;
        return this;
    }

    public final RestClientBuilder url(String mUrl) {
        this.mUrl = mUrl;
        return this;
    }

    public final RestClientBuilder parmas(HashMap<String, Object> mParmas) {
        this.mParmas = mParmas;
        return this;
    }

    public final RestClientBuilder body(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json"), raw);
        return this;
    }

    public final RestClientBuilder body(RequestBody body) {
        this.mBody = body;
        return this;
    }


    public final RestClientBuilder file(File mFile) {
        this.mFile = mFile;
        return this;
    }

    public final RestClientBuilder interceptor(Interceptor interceptor) {
        this.interceptor = interceptor;
        return this;
    }

    public final RestClient build() {
        return new RestClient(mParmas, mUrl, mBody, mFile, mDownloadDir, mExtension, mFileName, listener, interceptor);
    }
}
