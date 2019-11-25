package com.pxs.corelibrary.corelib.http.rxcallback;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

public class UploadRequestBody extends RequestBody {


    @Override
    public MediaType contentType() {
        return null;
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {

    }
}
