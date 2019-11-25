package com.io.org.visualx.libs.request;


import com.io.org.visualx.libs.callback.HttpCallback;
import com.io.org.visualx.libs.client.BaseRestClient;
import com.io.org.visualx.libs.client.RestClient;
import com.io.org.visualx.libs.client.StringBodyRestClient;
import com.io.org.visualx.libs.exception.MarshallException;
import com.io.org.visualx.libs.logger.ALog;
import com.io.org.visualx.libs.parser.NanoXmlResponseParser;
import com.leansoft.nano.IWriter;
import com.leansoft.nano.NanoFactory;

public abstract class NanoXmlRequestProcessor<T> extends BaseRequestProcessor<T>  {
	
    private final StringBodyRestClient client;

	private IWriter xmlWriter = NanoFactory.getXMLWriter();
	private Object requestObject;
	
	public NanoXmlRequestProcessor(
			Object requestObject, 
			Class<T> responseType,
			HttpCallback<T> callback) {
		super(new NanoXmlResponseParser<T>(responseType), callback);
		
		this.requestObject = requestObject;
		
        client = new StringBodyRestClient();
        client.setRequestMethod(BaseRestClient.RequestMethod.POST);
	}
	
	@Override
	protected void prepareRequest() {
      String requestXml = marshallRequestObject();
      ALog.d(TAG, "String body" + requestXml);
      client.setBody(requestXml);
	}
    
    private String marshallRequestObject() {
		String requestXml;
		try {
			requestXml = xmlWriter.write(requestObject);
		} catch (Exception e) {
			throw new MarshallException("fail to marshall request object", e);
		}
		return requestXml;
    }

    @Override
    public RestClient getRestClient() {
        return client;
    }
    
    public void addHeader(final String key, final String value) {
    	client.addHeader(key, value);
    }
}
