// Generated by xsd compiler for android/java
// DO NOT CHANGE!
package com.io.org.visualx.ebay.marketplace.search.v1.services;

import java.io.Serializable;
import com.leansoft.nano.annotation.*;


/**
 * 
 * <b>This call is deprecated.</b>
 * 
 */
@com.leansoft.nano.annotation.RootElement(name = "findItemsByImageResponse", namespace = "http://www.ebay.com/marketplace/search/v1/services")
public class FindItemsByImageResponse extends BaseFindingServiceResponse implements Serializable {

    private static final long serialVersionUID = -1L;

	@Element    
	@Order(value=0)
	public CategoryHistogramContainer categoryHistogramContainer;	
	
	@Element    
	@Order(value=1)
	public AspectHistogramContainer aspectHistogramContainer;	
	
	@Element    
	@Order(value=2)
	public ConditionHistogramContainer conditionHistogramContainer;	
	
    
}