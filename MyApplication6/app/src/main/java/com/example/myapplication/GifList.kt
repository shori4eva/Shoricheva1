package com.example.myapplication

import com.google.gson.annotations.SerializedName

class GifList {
    @SerializedName("result")
    var gif: List<Gif>? = null
}