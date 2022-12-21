package com.ecommerceproductsearch.util;

public enum SearchFields {


    PRODUCT_ID("productid"),
    PRODUCT_TITLE("producttitle"),
    PRODUCT_DESC("productdescription"),
    PRODUCT_PRICE("actualprice");


    private String fieldName;

    SearchFields(String fieldName){
     this.fieldName = fieldName;
    }

    public String getFieldName(){
        return this.fieldName;
    }

}
