package com.pxs.corelibrary.corelib.http;

import com.pxs.corelibrary.corelib.OnSuccessAndFaultListener;
import com.pxs.corelibrary.corelib.http.rxcallback.CallbackDownSubscriber;
import com.pxs.corelibrary.corelib.http.rxcallback.CallbackSubscriber;
import com.pxs.corelibrary.corelib.tool.Logger;

import java.io.File;
import java.util.HashMap;

import okhttp3.Interceptor;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;

public class RestClient {
    private final HashMap<String, Object> PARAMS;
    private final String URL;
    private final RequestBody BODY;
    private final File File;

    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String FILENAME;
    private final OnSuccessAndFaultListener listener;
    private final Interceptor interceptor;


    public RestClient(HashMap<String, Object> params, String url, RequestBody body
            , java.io.File file, String download_dir, String extension, String filename
            , OnSuccessAndFaultListener listener, Interceptor interceptor) {
        PARAMS = params;
        URL = url;
        BODY = body;
        File = file;
        DOWNLOAD_DIR = download_dir;
        EXTENSION = extension;
        FILENAME = filename;
        this.interceptor = interceptor;
        this.listener = listener;
    }

    public static RestClientBuilder create() {
        return new RestClientBuilder();
    }


    private CallbackSubscriber getRxCallbackSubscriber() {
        return new CallbackSubscriber(listener);
    }

    private CallbackDownSubscriber getRxCallbackDownSubscriber() {
        return new CallbackDownSubscriber(listener, FILENAME);
    }

    //开始真实的网络请求
    private void request(HttpMethod httpMethod) {
        RestService restService = null;
        if (interceptor == null) {
            restService = RestCreator.getRestService();
        } else {
            restService = RestCreator.getRestService(interceptor);
        }
        Call<String> call = null;
        Call<ResponseBody> downCall = null;
        switch (httpMethod) {
            case GET:
                call = restService.get(URL, PARAMS);
                break;
            case POST:
                call = restService.post(URL, PARAMS);
                break;
            case PUT:
                break;
            case DELETE:
                break;
            case UPLOAD:
                call = restService.postFile(URL, BODY);
                break;
            case PUT_PAW:
                break;
            case DOWNLOAD:
                downCall = restService.down(URL);
                break;
            case POST_RAW:
                call = restService.postRaw(URL, BODY);
                break;
        }
        if (call != null) {
            call.enqueue(getRxCallbackSubscriber());
        }
        if (downCall != null) {
            downCall.enqueue(getRxCallbackDownSubscriber());
        }
    }

    //各种请求 给用户使用
    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        request(HttpMethod.POST);
    }

    public final void postRaw() {
        request(HttpMethod.POST_RAW);
    }

    public final void down() {
        request(HttpMethod.DOWNLOAD);
    }

    public final void postFile() {
        request(HttpMethod.UPLOAD);
    }


}
