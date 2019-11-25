// Generated by xsd compiler for android/java
// DO NOT CHANGE!
package com.io.org.visualx.ebay.marketplace.search.v1.services;

import java.io.Serializable;
import com.leansoft.nano.annotation.*;
import java.util.List;


/**
 * 
 * Container holding the item's shipping details.
 * 
 */
public class ShippingInfo implements Serializable {

    private static final long serialVersionUID = -1L;

	@Element    
	@Order(value=0)
	public Amount shippingServiceCost;	
	
	@Element    
	@Order(value=1)
	public String shippingType;	
	
	@Element    
	@Order(value=2)
	public List<String> shipToLocations;	
	
	@Element    
	@Order(value=3)
	public Boolean expeditedShipping;	
	
	@Element    
	@Order(value=4)
	public Boolean oneDayShippingAvailable;	
	
	@Element    
	@Order(value=5)
	public Integer handlingTime;	
	
	@Element    
	@Order(value=6)
	public Boolean intermediatedShipping;	
	
	@Element    
	@Order(value=7)
	public String delimiter;	
	
	@AnyElement
	@Order(value=8)
	public List<Object> any;	
	
    
}