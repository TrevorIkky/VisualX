package com.io.org.visualx.core.finding;


import com.io.org.visualx.core.finding.request.BaseFindingRequestProcessor;
import com.io.org.visualx.ebay.marketplace.search.v1.services.FindItemsByKeywordsRequest;
import com.io.org.visualx.ebay.marketplace.search.v1.services.FindItemsByKeywordsResponse;
import com.io.org.visualx.libs.RequestProcessor;
import com.io.org.visualx.libs.callback.HttpCallback;

public class FindingService {
	
	public static final String TAG = FindingService.class.getSimpleName();
	
	public static RequestProcessor getFindItemsByKeywordsHttpRequest(
			FindItemsByKeywordsRequest requestObject,
			HttpCallback<FindItemsByKeywordsResponse> callback,
			FindingConfig config) {
		return new BaseFindingRequestProcessor<FindItemsByKeywordsResponse>(
				requestObject, 
				"findItemsByKeywords", 
				FindItemsByKeywordsResponse.class, 
				callback, 
				config);
	}

}
