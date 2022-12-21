package com.ecommerceproductsearch.controller;

import com.ecommerceproductsearch.dto.Product;
import com.ecommerceproductsearch.services.ElasticSearchService;
import com.ecommerceproductsearch.util.SearchFields;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class SearchController {

    private final ElasticSearchService elasticSearchService;

    public SearchController(ElasticSearchService elasticSearchService){
      this.elasticSearchService = elasticSearchService;
    }

    @PostMapping("/index")
    public ResponseEntity<String> createIndex(@RequestBody Product product) throws IOException {
        var response = elasticSearchService.createIndex(product);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/indexById/{id}")
    public ResponseEntity<String> indexById(@PathVariable(name="id")String id) throws IOException {
        var response = elasticSearchService.searchIndexById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search")
    public @ResponseBody ResponseEntity<List<Product>> searchText(@RequestParam(name = "q")String q,@RequestParam(name = "by") SearchFields by) throws IOException {
        var response = elasticSearchService.searchQuery(q,by.getFieldName());
        return new ResponseEntity<List<Product>>(response, HttpStatus.OK);
    }

}
