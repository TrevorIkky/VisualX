package com.io.org.visualx.libs.auth;


import com.io.org.visualx.libs.client.BaseRestClient;

public interface AuthenticationProvider {

    public void authenticateRequest(BaseRestClient client);
}
