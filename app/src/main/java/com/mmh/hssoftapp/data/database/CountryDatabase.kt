package com.mmh.hssoftapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mmh.hssoftapp.data.entities.Country

@Database(entities = [Country::class], version = 1)

abstract class CountryDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao
}