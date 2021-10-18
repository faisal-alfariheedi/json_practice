package com.example.jsonpractice


import retrofit2.Call
import retrofit2.http.*

interface APIInterface {

    @Headers("Content-Type: application/json")
    @GET("contacts/")
    fun getdat(): Call<List<celep.dat>>


    @Headers("Content-Type: application/json")
    @POST("contacts/")
    fun adddat(@Body data: celep.dat): Call<celep.dat>

    @Headers("Content-Type: application/json")
    @PUT("contacts/{id}")
    fun updatedat(@Path("id")id:Int, @Body data: celep.dat): Call<celep.dat>

    @Headers("Content-Type: application/json")
    @DELETE("contacts/{id}")
    fun deldat(@Path("id")id:Int): Call<celep.dat>
}