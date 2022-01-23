package com.nitin.web.congitivesearch.controllers

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import com.azure.search.documents.indexes.SearchIndexClient
import com.azure.search.documents.indexes.SearchIndexClientBuilder
import com.azure.search.documents.indexes.models.FieldBuilderOptions
import com.azure.search.documents.indexes.models.SearchIndex
import com.azure.core.credential.AzureKeyCredential
import com.nitin.web.congitivesearch.models.Dataset
@Controller
@RequestMapping("/api/index")
class IndexingController{


    @PostMapping("/")
    fun index(): ResponseEntity<String> {
        val client: SearchIndexClient = SearchIndexClientBuilder()
            .endpoint("")
            .credential(AzureKeyCredential(""))
            .buildClient()
        
        // Use the SearchIndexClient to create SearchFields from your own model that has fields or methods annotated
        // with @SimpleField or @SearchableField.
       val indexFields = SearchIndexClient.buildSearchFields(Dataset::class.java,  FieldBuilderOptions());
        val indexName = "dataset";
        val newIndex =  SearchIndex(indexName, indexFields);

        // Create index.
        client.createIndex(newIndex);
        return ResponseEntity.ok("")
    }


}