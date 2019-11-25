package com.pxs.corelibrary.corelib.http.rxcallback;

import com.pxs.corelibrary.corelib.OnSuccessAndFaultListener;
import com.pxs.corelibrary.corelib.tool.Toaster;

public abstract class DownHttpCallback implements OnSuccessAndFaultListener<Integer> {

    @Override
    public void onSuccess(Integer result) {
        if (result == 200) {
            downComplete();
            return;
        }
        onProgress(result);
    }

    protected abstract void onProgress(Integer result);

    protected abstract void downComplete();

    @Override
    public void onFault(int code, String errorMsg) {
        Toaster.show(errorMsg);
    }
}
