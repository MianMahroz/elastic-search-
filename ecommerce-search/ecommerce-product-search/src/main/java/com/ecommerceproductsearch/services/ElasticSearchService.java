package com.ecommerceproductsearch.services;

import com.ecommerceproductsearch.dto.Product;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface ElasticSearchService {

    public String createIndex(Product product) throws IOException;

    public String searchIndexById(String id) throws IOException;

    public List<Product> searchQuery(String searchText,String searchBy) throws IOException;



}
