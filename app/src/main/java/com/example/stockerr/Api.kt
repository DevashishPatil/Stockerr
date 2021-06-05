package com.example.stockerr


import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface Api {

    @GET("bonus")
    fun getBonus(
    ): Call<ResponseBody?>?

    //--------------------------------------------------------------------------------------------------
}
