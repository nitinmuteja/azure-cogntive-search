package com.nitin.web.congitivesearch.models
import com.azure.search.documents.indexes.SimpleField
import com.azure.search.documents.indexes.SearchableField
import kotlinx.serialization.Serializable

@Serializable
data class Dataset(
    @SimpleField(isKey=true)
    @JvmField
    var id: Int,
    @SearchableField(analyzerName="en.microsoft")
    @JvmField
    var name: String,

    @JvmField
    var description: String,

    @JvmField
    var stewardName: String,

    @JvmField
    var itOwner: String,

    @JvmField var userAccessGroup: String,

    @JvmField var applicationAccessGroup: String,

    @JvmField var schema: List<Column>

    )