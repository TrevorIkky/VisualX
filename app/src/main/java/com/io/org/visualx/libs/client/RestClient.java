package com.io.org.visualx.libs.client;

import com.io.org.visualx.libs.auth.AuthenticationProvider;
import com.io.org.visualx.libs.domain.ResponseStatus;
import com.io.org.visualx.libs.exception.HttpException;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpUriRequest;


public interface RestClient {

    public abstract int getConnectionTimeout();

    public abstract void setUrl(final String url);

    public abstract String getUrl();

    public abstract void setConnectionTimeout(final int connectionTimeout);

    public abstract int getSocketTimeout();

    public abstract void setSocketTimeout(final int socketTimeout);

    public abstract void setAuthentication(final AuthenticationProvider authProvider);

    public abstract String getResponse();

    public abstract ArrayList<NameValuePair> getHeaders();

    public abstract ArrayList<NameValuePair> getParams();

    public abstract void addHeader(final String name, final String value);

    public abstract void addParam(final String name, final String value);
    
    public void setRequestMethod(BaseRestClient.RequestMethod requestMethod);
    public BaseRestClient.RequestMethod getRequestMethod();

    public abstract void executeRequest(final HttpUriRequest request) throws IOException;

    public void execute() throws HttpException;

    public abstract ResponseStatus getResponseStatus();

}
