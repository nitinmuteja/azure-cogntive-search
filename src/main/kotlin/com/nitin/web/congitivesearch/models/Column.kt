package com.nitin.web.congitivesearch.models
import kotlinx.serialization.Serializable
import com.azure.search.documents.indexes.SimpleField
import com.azure.search.documents.indexes.SearchableField

data class Column(
    
    @SearchableField(analyzerName="en.microsoft",synonymMapNames=["default"])
    @JvmField 
    val columnName: String,
    
    @SimpleField
    @JvmField 
    val dataType: String,

    @SearchableField
    @JvmField 
    val description: String,

)