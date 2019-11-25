package com.pxs.corelibrary.corelib.http.rxcallback;

public interface ProgressListener {
    void onProgress(long writtenLength, long totalLength, boolean isFinish);
}
