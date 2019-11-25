// Generated by xsd compiler for android/java
// DO NOT CHANGE!
package com.io.org.visualx.ebay.marketplace.search.v1.services;

import java.io.Serializable;
import com.leansoft.nano.annotation.*;


/**
 * 
 * This container supplies information for an item that has a Strike-Through
 * Pricing (STP) or Minimum Advertised Price (MAP) discount pricing treatment.
 * STP and MAP applies to only fixed price, BIN items. STP is available on the
 * US, UK, and DE sites while MAP is available only on the US site.
 * <br><br>
 * This feature is available to qualified sellers who participate in the
 * Discount Pricing program. A seller can provide a discount price treatment for
 * Non-MSKU and MSKU items in Clothing, Shoes and Accessories (CDA), Motors, and
 * Electronics (Home and Garden is expected to be supported in the future).
 * 
 */
public class DiscountPriceInfo implements Serializable {

    private static final long serialVersionUID = -1L;

	@Element    
	@Order(value=0)
	public Amount originalRetailPrice;	
	
	@Element    
	@Order(value=1)
	public MapExposureEnum minimumAdvertisedPriceExposure;	
	
	@Element    
	@Order(value=2)
	public PriceTreatmentEnum pricingTreatment;	
	
	@Element    
	@Order(value=3)
	public Boolean soldOnEbay;	
	
	@Element    
	@Order(value=4)
	public Boolean soldOffEbay;	
	
    
}