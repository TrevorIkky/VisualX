// Generated by xsd compiler for android/java
// DO NOT CHANGE!
package com.io.org.visualx.ebay.marketplace.search.v1.services;

import java.io.Serializable;
import com.leansoft.nano.annotation.*;
import java.util.List;


/**
 * 
 * Returns items whose listings are completed and are no longer available for sale based on keyword and/or category and doesn't allow searching
 * within item descriptions.
 * <br><br>
 * You can expect response times for this call to be longer than other types of Finding Service
 * requests. The call must search through historical databases rather than performing a quick
 * lookup on currently listed items. There is a 5,000 limit on the number of findCompletedItems
 * calls an application can make in a single day (even if the application has completed an
 * app check).
 * <br><br>
 * Be aware that it is possible to use this call in such a way as to violate the terms and
 * conditions of your API License Agreement. Ensure that you do not store the results retrieved
 * from this call or use the results for market research purposes.
 * 
 */
@com.leansoft.nano.annotation.RootElement(name = "findCompletedItemsRequest", namespace = "http://www.ebay.com/marketplace/search/v1/services")
public class FindCompletedItemsRequest extends BaseFindingServiceRequest implements Serializable {

    private static final long serialVersionUID = -1L;

	@Element    
	@Order(value=0)
	public String keywords;	
	
	@Element    
	@Order(value=1)
	public List<String> categoryId;	
	
	@Element    
	@Order(value=2)
	public List<ItemFilter> itemFilter;	
	
	@Element    
	@Order(value=3)
	public List<AspectFilter> aspectFilter;	
	
	@Element    
	@Order(value=4)
	public List<OutputSelectorType> outputSelector;	
	
	@Element    
	@Order(value=5)
	public List<DomainFilter> domainFilter;	
	
	@Element    
	@Order(value=6)
	public ProductId productId;	
	
    
}