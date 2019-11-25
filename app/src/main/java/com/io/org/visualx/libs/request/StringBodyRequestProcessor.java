package com.io.org.visualx.libs.request;


import com.io.org.visualx.libs.callback.HttpCallback;
import com.io.org.visualx.libs.client.BaseRestClient;
import com.io.org.visualx.libs.client.RestClient;
import com.io.org.visualx.libs.client.StringBodyRestClient;
import com.io.org.visualx.libs.logger.ALog;
import com.io.org.visualx.libs.parser.HttpResponseParser;

public abstract class StringBodyRequestProcessor<T> extends BaseRequestProcessor<T> {

    public static final String TAG = StringBodyRequestProcessor.class.getSimpleName();
    private final StringBodyRestClient client;

    public StringBodyRequestProcessor(
    		BaseRestClient.RequestMethod requestMethod,
            final HttpResponseParser<T> parser,
            final HttpCallback<T> callback) {
        super(parser, callback);
        client = new StringBodyRestClient();
        client.setRequestMethod(requestMethod);
    }

    public void setBody(final String body) {
        ALog.d(TAG, "String body" + body);
        client.setBody(body);
    }

    @Override
    public RestClient getRestClient() {
        return client;
    }
}
