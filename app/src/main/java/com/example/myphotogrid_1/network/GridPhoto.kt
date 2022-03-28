package com.example.myphotogrid_1.network

import com.squareup.moshi.Json
data class GridPhoto (
    val id: String,
    @Json(name = "url") val url:String
    )