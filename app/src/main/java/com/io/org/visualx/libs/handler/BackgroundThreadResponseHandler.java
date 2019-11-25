package com.io.org.visualx.libs.handler;


import com.io.org.visualx.libs.callback.HttpCallback;
import com.io.org.visualx.libs.domain.ResponseStatus;

public abstract class BackgroundThreadResponseHandler<T> implements ResponseHandler<T> {

    public static final String TAG = BackgroundThreadResponseHandler.class.getSimpleName();
    protected final HttpCallback<T> callback;

    public BackgroundThreadResponseHandler(HttpCallback<T> callback) {
        this.callback = callback;
    }

    @Override
    public HttpCallback<T> getCallback() {
        return callback;
    }

    @Override
    public void handleSuccess(final T responseData) {
        if (callback != null) {
            callback.onSuccess(responseData);
        }
    }

    @Override
    public void handleError(final ResponseStatus status) {
        if (callback != null) {
            callback.onHttpError(status);
        }
    }
}
