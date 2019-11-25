package com.pxs.corelibrary.corelib.http;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface RestService {

    @GET
    Call<String> get(@Url String url, @QueryMap Map<String, Object> params);

    @FormUrlEncoded
    @POST
    Call<String> post(@Url String url, @FieldMap Map<String, Object> params);

    @POST
    Call<String> postRaw(@Url String url, @Body RequestBody params);

    @Streaming
    @GET
    Call<ResponseBody> down(@Url String url);

    @POST
    Call<String> postFile(@Url String url,@Body RequestBody requestBody);

}
