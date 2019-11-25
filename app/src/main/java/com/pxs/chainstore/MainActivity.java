package com.pxs.chainstore;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.jeremyliao.liveeventbus.LiveEventBus;
import com.pxs.chainstore.base.BaseActivity;
import com.pxs.chainstore.base.interceptor.HeadInterceptor;
import com.pxs.corelibrary.corelib.HttpHelper;
import com.pxs.corelibrary.corelib.OnSuccessAndFaultListener;
import com.pxs.corelibrary.corelib.ProjectInit;
import com.pxs.corelibrary.corelib.http.rxcallback.DownHttpCallback;
import com.pxs.corelibrary.corelib.http.rxcallback.ListHttpCallback;
import com.pxs.corelibrary.corelib.http.rxcallback.ProgressListener;
import com.pxs.corelibrary.corelib.http.rxcallback.RxHttpCallback;
import com.pxs.corelibrary.corelib.http.rxcallback.UploadProgressRequestBody;
import com.pxs.corelibrary.corelib.tool.Logger;
import com.pxs.corelibrary.corelib.tool.Toaster;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Interceptor;
import okhttp3.Response;

public class MainActivity extends BaseActivity {

    @BindView(R.id.rootContainer)
    FrameLayout rootContainer;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {


    }

    public void getUserInfo() {

        HttpHelper.getInstance().rxUpload("", new UploadProgressRequestBody(new File("11"), new HashMap<>(), new ProgressListener() {
            @Override
            public void onProgress(long writtenLength, long totalLength, boolean isFinish) {

            }
        }), new ListHttpCallback() {
            @Override
            protected void onError(int i, String msg) {

            }

            @Override
            public void onFromListSuccess(List result) {

            }
        });
    }
}
