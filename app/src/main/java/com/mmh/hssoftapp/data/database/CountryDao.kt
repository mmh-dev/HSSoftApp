package com.mmh.hssoftapp.data.database

import androidx.room.*
import com.mmh.hssoftapp.data.entities.Country


@Dao
interface CountryDao {

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCountry(country: Country)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateCountry(country: Country)

    @Query("SELECT * FROM country_database")
    suspend fun getAllCountries(): MutableList<Country>

    @Query("SELECT * FROM country_database WHERE code ==:code")
    suspend fun getCountryData(code: String): Country
}