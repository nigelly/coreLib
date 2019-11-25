package com.pxs.corelibrary.corelib;

/**
 * Created by Administrator on 2018/12/11.
 */

public interface OnSuccessAndFaultListener<T> {

    void onSuccess(T result);

    void onFault(int code, String errorMsg);
}
