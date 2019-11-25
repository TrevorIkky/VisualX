package com.io.org.visualx.libs.request;


import com.io.org.visualx.libs.callback.HttpCallback;
import com.io.org.visualx.libs.client.BaseRestClient;
import com.io.org.visualx.libs.client.ParametersRestClient;
import com.io.org.visualx.libs.client.RestClient;
import com.io.org.visualx.libs.parser.HttpResponseParser;

public abstract class ParameterRequestProcessor<T> extends BaseRequestProcessor<T> {

    public static final String TAG = ParameterRequestProcessor.class.getSimpleName();
    protected ParametersRestClient client;

    public ParameterRequestProcessor(
            final BaseRestClient.RequestMethod requestMethod,
            final HttpResponseParser<T> parser,
            final HttpCallback<T> callback) {
        super(parser, callback);
        client = new ParametersRestClient();
        client.setRequestMethod(requestMethod);
    }

    @Override
    public RestClient getRestClient() {
        return client;
    }

    public void addParam(final String key, final String value) {
        client.addParam(key, value);
    }
}

