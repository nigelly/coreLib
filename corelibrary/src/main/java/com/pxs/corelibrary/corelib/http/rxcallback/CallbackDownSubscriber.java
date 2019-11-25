package com.pxs.corelibrary.corelib.http.rxcallback;

import com.pxs.corelibrary.corelib.OnSuccessAndFaultListener;
import com.pxs.corelibrary.corelib.tool.AppDownLoad;
import com.pxs.corelibrary.corelib.tool.Logger;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.pxs.corelibrary.corelib.tool.AppDownLoad.DOWN_SUCCESS;
import static com.pxs.corelibrary.corelib.tool.AppDownLoad.NETWORK_ERROR;

public class CallbackDownSubscriber implements Callback<ResponseBody> {

    private final OnSuccessAndFaultListener<Integer> listener;

    private final String fileName;

    public CallbackDownSubscriber(OnSuccessAndFaultListener listener,String fileName) {
        this.listener = listener;
        this.fileName = fileName;
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        ResponseBody responseBody = response.body();
        RandomAccessFile randomAccessFile = null;
        InputStream is = null;

        long total = 0;
        long responeLength = 0;

        byte[] buf = new byte[2048];
        int len = 0;
        responeLength = responseBody.contentLength();
        is = responseBody.byteStream();
        File file = AppDownLoad.getAvatarFile(fileName);//确定APK下载 路径
        try {
            randomAccessFile = new RandomAccessFile(file, "rwd");
            randomAccessFile.setLength(responeLength);
            int progress = 0;
            int lastProgress = 0;
            while ((len = is.read(buf)) != -1) {
                randomAccessFile.write(buf, 0, len);
                total += len;
                progress = (int) (total * 100 / randomAccessFile.length());
                if (progress > 0 && progress != lastProgress) {
                    //回调进度条
                    listener.onSuccess(progress);
                }
            }
            //完成下载
            listener.onSuccess(DOWN_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            //下载失败
            listener.onFault(NETWORK_ERROR,"网络错误.");
        } finally {
            try {
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        t.printStackTrace();
        listener.onFault(NETWORK_ERROR,"网络错误.");
    }
}
