package com.io.org.visualx.libs.client;

import com.io.org.visualx.libs.exception.HttpException;
import com.io.org.visualx.libs.logger.ALog;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.protocol.HTTP;

import java.io.IOException;
import java.io.UnsupportedEncodingException;


/**
 * @author bulldog
 */
public class ParametersRestClient extends BaseRestClient {

    private static final String TAG = ParametersRestClient.class.getSimpleName();

    public ParametersRestClient() {
        super();
    }

    @Override
    public void execute() throws HttpException {
        try {
            switch (getRequestMethod()) {
                case GET: {
                    final HttpGet request = new HttpGet(getUrl() + generateParametersString(getParams()));
                    executeRequest(request);
                    break;
                }
                case POST: {
                    final HttpPost request = new HttpPost(getUrl());
                    if (!getParams().isEmpty()) {
                        request.setEntity(new UrlEncodedFormEntity(getParams(), HTTP.UTF_8));
                    }
                    executeRequest(request);
                    break;
                }
                case PUT: {
                    final HttpPut request = new HttpPut(getUrl());
                    if (!getParams().isEmpty()) {
                        request.setEntity(new UrlEncodedFormEntity(getParams(), HTTP.UTF_8));
                    }
                    executeRequest(request);
                    break;
                }
                case DELETE: {
                    final HttpDelete request = new HttpDelete(getUrl() + generateParametersString(getParams()));
                    executeRequest(request);
                    break;
                }
            }
        } catch (final UnsupportedEncodingException e) {
            ALog.w(TAG, "", e);
            throw new HttpException(e);
        } catch (final IOException e) {
            ALog.w(TAG, "", e);
            throw new HttpException(e);
        }
    }


}

