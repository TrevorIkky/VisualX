package com.io.org.visualx.libs.domain;


import com.io.org.visualx.libs.RequestProcessor;

public class RequestWrapper {

    public static final String TAG = RequestWrapper.class.getSimpleName();

    private final RequestProcessor request;
    private final RequestOptions options;

    public RequestWrapper(RequestProcessor request, RequestOptions options) {
        super();
        this.request = request;
        if (options != null) {
            this.options = options;
        } else {
            this.options = new RequestOptions();
        }
    }

    public RequestProcessor getRequest() {
        return request;
    }

    public RequestOptions getOptions() {
        return options;
    }

}
