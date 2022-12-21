# Elasticsearch Using Spring Boot Java Client

#### This example contains ecommerce use case of elastic search,where we search products using different queries. 

### Prerequisite:
- docker
- elasticsearch
- kibana

![img_8.png](img_8.png)

##### please follow below link to set up elasticsearch and kibana
https://www.elastic.co/guide/en/elasticsearch/reference/current/run-elasticsearch-locally.html
|Elasticsearch|->|Database| |————-|——–| |Index|->|Table| |Document|->|Row| |Field|->|Column|


### Data Ingestion Into Elasticsearch

There are many ways to ingest data into elastic search:

- Java Client Api , in this example you can use endpoint : /index
- Use Kibana to upload data 


##### USING Java Client Api:

    @PostMapping("/index")
    public ResponseEntity<String> createIndex(@RequestBody Product product) throws IOException {
    var response = elasticSearchService.createIndex(product);
    return new ResponseEntity<>(response, HttpStatus.OK);
    }


![img.png](img.png)


##### USING KIBANA 

![img_1.png](img_1.png)

![img_2.png](img_2.png)

![img_3.png](img_3.png)

![img_4.png](img_4.png)

![img_5.png](img_5.png)

![img_6.png](img_6.png)



### Query Data

    @GetMapping("/search")
    public @ResponseBody ResponseEntity<List<Product>> searchText(@RequestParam(name = "q")String q,@RequestParam(name = "by") SearchFields by) throws IOException {
    var response = elasticSearchService.searchQuery(q,by.getFieldName());
    return new ResponseEntity<List<Product>>(response, HttpStatus.OK);
    }

![img_7.png](img_7.png)

##### Using Kibana to query data

![img_9.png](img_9.png)

##### Kibana Queries
:cmart is index name

GET cmart/_doc/80000

POST /cmart/_search?typed_keys=true {"query":{"match":{"productTitle":{"query":"King"}}}}
