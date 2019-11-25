package com.io.org.visualx.libs.handler;


import com.io.org.visualx.libs.callback.HttpCallback;
import com.io.org.visualx.libs.domain.ResponseStatus;

public interface ResponseHandler<T> {

    public HttpCallback<T> getCallback();

    public void handleSuccess(final T responseData);

    public void handleError(final ResponseStatus status);
}
