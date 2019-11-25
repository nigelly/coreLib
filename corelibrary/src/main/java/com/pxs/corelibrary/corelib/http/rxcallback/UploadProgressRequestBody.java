package com.pxs.corelibrary.corelib.http.rxcallback;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

public class UploadProgressRequestBody extends RequestBody {

    private RequestBody requestBody;
    private ProgressListener progressListener;
    private BufferedSink bufferedSink;

    public UploadProgressRequestBody(File file, Map<String, String> params, ProgressListener progressListener) {
        this.progressListener = progressListener;

        MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
        RequestBody fileBody = MultipartBody.create(MEDIA_TYPE_PNG, file);
        MultipartBody.Builder multiBuilder = new MultipartBody.Builder();

        multiBuilder.setType(MultipartBody.FORM);
        multiBuilder.addFormDataPart("file", file.getName(), fileBody);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            multiBuilder.addPart(MultipartBody.Part.createFormData(entry.getKey(), entry.getValue()));
        }

        requestBody = multiBuilder.build();
    }

    @Override
    public MediaType contentType() {
        return requestBody.contentType();
    }

    @Override
    public long contentLength() throws IOException {
        return requestBody.contentLength();
    }

    @Override
    public void writeTo(@NonNull BufferedSink sink) {
        if (bufferedSink == null) {
            bufferedSink = Okio.buffer(sink(sink));
        }
        try {
            requestBody.writeTo(bufferedSink);
            bufferedSink.flush();
        }catch (Exception e){
            progressListener.onProgress(-100,-100,true);
        }
    }

    private Sink sink(@NonNull BufferedSink sink) {
        return new ForwardingSink(sink) {
            long bytesWritten = 0L;
            long contentLength = 0L;

            @Override
            public void write(@NonNull Buffer source, long byteCount) throws IOException {
                super.write(source, byteCount);
                if (contentLength == 0L) {
                    contentLength = contentLength();
                }

                bytesWritten += byteCount;
                progressListener.onProgress(bytesWritten, contentLength, bytesWritten == contentLength);
            }
        };
    }
}
