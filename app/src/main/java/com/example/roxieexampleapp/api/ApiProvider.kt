package com.example.roxieexampleapp.api

import com.example.roxieexampleapp.model.Image
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response

interface ApiProvider {
    fun getImages(): Single<Array<Image>>

//    fun getImagesPerPage(page: Int): Single<Array<Image>>
}