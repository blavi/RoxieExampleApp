package com.example.roxieexampleapp.repository

import com.example.roxieexampleapp.api.ApiProviderImpl
import javax.inject.Inject

class Repository @Inject constructor(private val apiHelperImpl: ApiProviderImpl) {

    fun getImages() = apiHelperImpl.getImages()
}