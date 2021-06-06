package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("{m}?json=true")
    fun us(@Path("m") m : Int) : Call<GifList>
}