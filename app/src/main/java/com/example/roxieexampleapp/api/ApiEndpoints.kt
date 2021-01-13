package com.example.roxieexampleapp.api

import com.example.roxieexampleapp.model.Image
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndpoints {

    @GET("list")
    fun getImages(@Query("limit") limit: Int): Single<Array<Image>>

    @GET("list")
    suspend fun getImagesPerPage(@Query("page") page: Int, @Query("limit") limit: Int): Response<Array<Image>>
}