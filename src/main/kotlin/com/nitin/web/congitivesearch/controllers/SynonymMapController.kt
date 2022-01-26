package com.nitin.web.congitivesearch.controllers

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import com.azure.search.documents.indexes.SearchIndexClient
import com.azure.search.documents.indexes.SearchIndexClientBuilder
import com.azure.search.documents.indexes.models.FieldBuilderOptions
import com.azure.search.documents.indexes.models.SearchIndex
import com.azure.search.documents.indexes.models.SynonymMap
import com.azure.search.documents.indexes.models.BM25SimilarityAlgorithm
import com.azure.core.credential.AzureKeyCredential
import com.nitin.web.congitivesearch.models.Dataset

@Controller
@RequestMapping("/api/synonymMap")
class SynonymMapController(@Value("\${cognitivesearch.url}") private val endpoint: String,@Value("\${cognitivesearch.apikey}") private val  apiKey: String){


    @PostMapping
    fun synonymMap(): ResponseEntity<String> {
        val client: SearchIndexClient = SearchIndexClientBuilder()
            .endpoint(endpoint)
            .credential(AzureKeyCredential(apiKey))
            .buildClient()

         val synonymMap =  SynonymMap("default",
            "user, customer\nlogin,signin\nnik=>nicolas");
            client.createSynonymMap(synonymMap);


        return ResponseEntity.ok("ok")
    }

}