package com.nitin.web.congitivesearch.models
import com.azure.search.documents.indexes.SimpleField
import com.azure.search.documents.indexes.SearchableField
import kotlinx.serialization.Serializable


data class Dataset(

    @SimpleField(isKey=true)
    @JvmField
    var id: String,

    @SearchableField(isSortable=true,analyzerName="en.microsoft",synonymMapNames=["default"])
    @JvmField
    var name: String,

    @JvmField
    @SearchableField
    var description: String,

    @JvmField
    @SimpleField(isFilterable=true,isFacetable=true)
    var datasetType: String,

    @JvmField
    @SimpleField(isFilterable=true,isFacetable=true)
    var location: String,

    @JvmField
    @SearchableField(isFilterable=true,isFacetable=true,synonymMapNames=["default"])
    var stewardName: String,

    @JvmField
    @SearchableField(isFilterable=true,isFacetable=true)
    var itOwner: String,
    
    @SearchableField(analyzerName="keyword")
    @JvmField var userAccessGroup: String,

    @SearchableField(analyzerName="keyword")
    @JvmField 
    var applicationAccessGroup: String,
    
    @SearchableField
    @JvmField 
    var schema: List<Column>

    )