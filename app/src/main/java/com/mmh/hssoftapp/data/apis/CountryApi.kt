package com.mmh.hssoftapp.data.apis

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface CountryApi {

    @Headers("Content-Type: application/json")
    @POST("/")
    suspend fun queryData(@Body body: String): Response<String>
}