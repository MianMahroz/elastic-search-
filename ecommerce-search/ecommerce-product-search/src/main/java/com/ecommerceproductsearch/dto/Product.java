package com.ecommerceproductsearch.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public record Product(@JsonProperty("productid") String id,
                      @JsonProperty("producttitle") String name,
                      @JsonProperty("productdescription") String desc,
                      @JsonProperty("actualprice") double price){}

