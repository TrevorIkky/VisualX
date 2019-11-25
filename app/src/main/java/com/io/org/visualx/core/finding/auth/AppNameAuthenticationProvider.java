package com.io.org.visualx.core.finding.auth;


import com.io.org.visualx.core.finding.FindingConstants;
import com.io.org.visualx.libs.auth.AuthenticationProvider;
import com.io.org.visualx.libs.client.BaseRestClient;

public class AppNameAuthenticationProvider implements AuthenticationProvider {
	
	private final String appName;
	
	public AppNameAuthenticationProvider(String appName) {
		this.appName = appName;
	}

	@Override
	public void authenticateRequest(BaseRestClient client) {
		client.addHeader(FindingConstants.X_EBAY_SOA_SECURITY_APPNAME_HEADER, appName);
	}

}
