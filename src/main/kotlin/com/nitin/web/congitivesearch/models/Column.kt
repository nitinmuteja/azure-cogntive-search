package com.nitin.web.congitivesearch.models
import kotlinx.serialization.Serializable

@Serializable
data class Column(
    @JvmField val columnName: String,

    @JvmField val dataType: String,

    @JvmField val description: String,

)