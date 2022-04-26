package com.mmh.hssoftapp.data.repositories

import android.content.Context
import com.mmh.hssoftapp.R
import com.mmh.hssoftapp.data.apis.CountryApi
import com.mmh.hssoftapp.data.database.CountryDatabase
import com.mmh.hssoftapp.data.entities.Country
import com.mmh.hssoftapp.utils.Resource
import javax.inject.Inject

class CountryRepository @Inject constructor(
    private val context: Context,
    private val api: CountryApi,
    private val database: CountryDatabase
) {
    suspend fun getOnlineData(body: String): Resource<String> {
        return try {
            val response = api.queryData(body)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: context.getString(R.string.error_occurred))
        }
    }

    suspend fun insertCountry(country: Country) {
        database.countryDao().insertCountry(country)
    }

    suspend fun updateCountry(country: Country) {
        database.countryDao().updateCountry(country)
    }

    suspend fun getAllCountries(): MutableList<Country> {
        return database.countryDao().getAllCountries()
    }

    suspend fun getCountryData(code: String): Country {
        return database.countryDao().getCountryData(code)
    }

}