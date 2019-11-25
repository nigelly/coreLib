package com.pxs.corelibrary.corelib.http.rxcallback;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.jeremyliao.liveeventbus.LiveEventBus;
import com.pxs.corelibrary.corelib.OnSuccessAndFaultListener;
import com.pxs.corelibrary.corelib.ProjectInit;
import com.pxs.corelibrary.corelib.http.RxBaseBean;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;



public abstract class RxHttpCallback<Result> implements OnSuccessAndFaultListener {

    @Override
    public void onSuccess(Object result) {
        //首先解析BaseBean
        Gson gson = new Gson();
        RxBaseBean baseBean = gson.fromJson(result.toString(), RxBaseBean.class);
        if (baseBean.getCode() == 200) {

            Class<?> clz = analysisClassInfo(this);
            if (baseBean.getData().isJsonArray()) {
                List<Result> result1 = new ArrayList<>();
                if (baseBean.getData() instanceof JsonArray) {
                    for (int i = 0; i < ((JsonArray) baseBean.getData()).size(); i++) {
                        Result result2 = (Result) gson.fromJson(((JsonArray) baseBean.getData()).get(i), clz);
                        result1.add(result2);
                    }
                }
                //   onFromListSuccess(result1);
            } else {
                Result result1 = (Result) gson.fromJson(baseBean.getData(), clz);
                onFromSuccess(result1);
            }

        } else if (baseBean.getCode() == 400) {
            onError(baseBean.getCode(), baseBean.getMessage());
        } else if (baseBean.getCode() == 666) {
            onError(666, baseBean.getMessage());
            ProjectInit.isLogin = false;
        } else if (baseBean.getCode() == 500) {
            onError(500, baseBean.getMessage());
        } else {
            onError(-1, "未知错误");
        }
    }

    protected abstract void onError(int i, String msg);

    public abstract void onFromSuccess(Result result);

    //  public abstract void onFromListSuccess(List<Result> result);

    private Class<?> analysisClassInfo(Object object) {
        //拿到各种类的原始类型
        Type genType = object.getClass().getGenericSuperclass();
        //获取参数化类型
        Type[] arguments = ((ParameterizedType) genType).getActualTypeArguments();
        return (Class<?>) arguments[0];
    }

    @Override
    public void onFault(int code, String errorMsg) {
        onError(-2, errorMsg);
    }

}
