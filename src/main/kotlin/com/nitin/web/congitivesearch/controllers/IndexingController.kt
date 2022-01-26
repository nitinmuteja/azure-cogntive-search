package com.nitin.web.congitivesearch.controllers

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestMapping
import com.azure.search.documents.indexes.SearchIndexClient
import com.azure.search.documents.indexes.SearchIndexClientBuilder
import com.azure.search.documents.indexes.models.FieldBuilderOptions
import com.azure.search.documents.indexes.models.SearchIndex
import com.azure.search.documents.indexes.models.SynonymMap
import com.azure.search.documents.indexes.models.BM25SimilarityAlgorithm
import com.azure.search.documents.indexes.models.ScoringProfile
import com.azure.search.documents.indexes.models.TextWeights
import com.azure.core.credential.AzureKeyCredential
import com.nitin.web.congitivesearch.models.Dataset

@Controller
@RequestMapping("/api/index")
class IndexingController(@Value("\${cognitivesearch.url}") private val endpoint: String,@Value("\${cognitivesearch.apikey}") private val  apiKey: String){

        private  val client: SearchIndexClient = SearchIndexClientBuilder()
            .endpoint(endpoint)
            .credential(AzureKeyCredential(apiKey))
            .buildClient()

        private val indexName = "dataset"

    @PostMapping
    fun index(): ResponseEntity<String> {
       
        // Use the SearchIndexClient to create SearchFields from your own model that has fields or methods annotated
        // with @SimpleField or @SearchableField.
       val indexFields = SearchIndexClient.buildSearchFields(Dataset::class.java,  FieldBuilderOptions())
        val newIndex =  SearchIndex(indexName, indexFields)
        newIndex.similarity = BM25SimilarityAlgorithm().setB(1.0)
        newIndex.scoringProfiles = listOf(scoringProfile("defaultProfile"))
        newIndex.defaultScoringProfile = "defaultProfile"
        client.createIndex(newIndex);
        return ResponseEntity.ok("ok")
    }

    private fun scoringProfile(profileName: String): ScoringProfile{

       val profile= ScoringProfile(profileName)
       profile.textWeights= TextWeights(mapOf(Pair("name",8.0),Pair("stewardName",5.0)))
        return profile
    }

    @DeleteMapping
    fun deleteIndex(): ResponseEntity<String> {
        client.deleteIndex(indexName)
        return ResponseEntity.ok("deleted")
    }


}