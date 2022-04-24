package com.mmh.hssoftapp.data.repositories

import com.mmh.hssoftapp.data.apis.CountryApi
import com.mmh.hssoftapp.utils.Resource
import javax.inject.Inject

class CountryRepository @Inject constructor(
    private val api: CountryApi
) {
    suspend fun getData(body: String): Resource<String>{
        return try {
            val response = api.queryData(body)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }
}