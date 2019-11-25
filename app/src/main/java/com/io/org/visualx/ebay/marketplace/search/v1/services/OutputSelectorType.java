// Generated by xsd compiler for android/java
// DO NOT CHANGE!
package com.io.org.visualx.ebay.marketplace.search.v1.services;

/**
 * 
 * Values that specify additional information to be returned in the search results.
 * 
 */
public enum OutputSelectorType {

    /**
     * 
   * Include information about the seller in the response.
   * 
     */
    SELLER_INFO("SellerInfo"),
  

    /**
     * 
   * Include information about the seller's eBay store in the response.
   * 
     */
    STORE_INFO("StoreInfo"),
  

    /**
     * 
   * Include a CategoryHistogram container with information about categories
   * that match your search.
   * 
     */
    CATEGORY_HISTOGRAM("CategoryHistogram"),
  

    /**
     * 
   * Include an AspectHistogram container with information about aspects from
   * the category that is most relevant to your search.
   * </span>
   * 
     */
    ASPECT_HISTOGRAM("AspectHistogram"),
  

    /**
     * 
   * Include a ConditionHistogram container with the number of
   * items found per condition (for example, how many items are new).
   * This value has no effect when Condition is specified as
   * an item filter. Supported for all eBay sites except US eBay Motors, India (IN), Malaysia
   * (MY) and Philippines (PH).
   * 
     */
    CONDITION_HISTOGRAM("ConditionHistogram"),
  

    /**
     * 
   * Include the GalleryInfoContainer, which contains URLs for three thumbnail
   * images of the item in different sizes: large, medium, and small.
   * 
     */
    GALLERY_INFO("GalleryInfo"),
  

    /**
     * 
   * Include a URL to a picture of the item that is 800x800 pixels in size.
   * 
     */
    PICTURE_URL_SUPER_SIZE("PictureURLSuperSize"),
  

    /**
     * 
   * Include a URL to a picture of the item that is 400x400 pixels in size.
   * 
     */
    PICTURE_URL_LARGE("PictureURLLarge"),
  

    /**
     * 
   * Include the unitPrice container with unit type and quantity information used for per-unit
   * pricing (on eBay EU sites only).
   * 
     */
    UNIT_PRICE_INFO("UnitPriceInfo");
  
  
    private final String value;
  
    OutputSelectorType(String v) {
        value = v;
    }
    
    public String value() {
        return value;
    }
    
    public static OutputSelectorType fromValue(String v) {
        if (v != null) {
            for (OutputSelectorType c: OutputSelectorType.values()) {
                if (c.value.equals(v)) {
                    return c;
                }
            }
        }
        throw new IllegalArgumentException(v);
    }
}