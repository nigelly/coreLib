package com.pxs.corelibrary.corelib.http.rxcallback;

import com.pxs.corelibrary.corelib.OnSuccessAndFaultListener;
import com.pxs.corelibrary.corelib.tool.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallbackSubscriber implements Callback<String> {

    private final OnSuccessAndFaultListener listener;

    public CallbackSubscriber(OnSuccessAndFaultListener listener) {
        this.listener = listener;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (listener != null) {
                    try {
                        String body = response.body();
                        Logger.print("数据: "+body);
                        listener.onSuccess(body);
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                }
            }
        } else {
            if (listener != null) {
                listener.onFault(response.code(), response.message());
                Logger.print(response.code() + " msg " + response.message());
            }
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {

        t.printStackTrace();
        if (listener != null)
            listener.onFault(-3, "网络错误.");
    }
}
