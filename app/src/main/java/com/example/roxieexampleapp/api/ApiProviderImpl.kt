package com.example.roxieexampleapp.api

import javax.inject.Inject

class ApiProviderImpl @Inject constructor(private val api: ApiEndpoints): ApiProvider {

    override fun getImages() = api.getImages(10)

//    override fun getImagesPerPage(page: Int) = api.getImagesPerPage(page, 10)
}