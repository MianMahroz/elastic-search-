package com.ecommerceproductsearch.services;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.CreateRequest;
import co.elastic.clients.elasticsearch.core.GetRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.search.TotalHitsRelation;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import com.ecommerceproductsearch.dto.Product;
import com.ecommerceproductsearch.util.SearchFields;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ElasticSearchServiceImpl implements ElasticSearchService {


    private final String PRODUCT_INDEX = "cmart";


    private final ElasticsearchClient elasticRestClient;

    public ElasticSearchServiceImpl(ElasticsearchClient elasticRestClient) {
        this.elasticRestClient = elasticRestClient;
    }

    @Override
    public String createIndex(Product product) throws IOException {

        IndexResponse response = elasticRestClient.index(i -> i
                .index(PRODUCT_INDEX)
                .id(product.id())
                .document(product)
        );
        log.info("Indexed with version " + response.version());
        return response.result().jsonValue();
    }

    @Override
    public String searchIndexById(String id) throws IOException {
        var request = GetRequest.of(s -> s.index(PRODUCT_INDEX).id(id));
        var res = elasticRestClient.get(request, Product.class);
        return res.found() ? res.source().toString() : "Not Found!";
    }


    @Override
    public List<Product> searchQuery(String searchText,String searchBy) throws IOException {

        var req = SearchRequest.of(s -> s
                .index(PRODUCT_INDEX)
                .query(q -> q
                        .match(m -> m
                                .field(searchBy)
                                .query(searchText))));

        var response =  elasticRestClient.search(req, Product.class);

        var total = response.hits().total();
        boolean isExactResult = total.relation() == TotalHitsRelation.Eq;

        if (isExactResult) {
            log.info("There are " + total.value() + " results");
        } else {
            log.info("There are more than " + total.value() + " results");
        }



        return response
                .hits()
                .hits()
                .stream()
                .map(s->s.source())
                .collect(Collectors.toList());
    }
}
