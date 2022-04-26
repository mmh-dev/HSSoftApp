package com.mmh.hssoftapp.data.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity (tableName = "country_database")
data class Country(
    @PrimaryKey
    @NonNull
    var code: String = "",
    var name: String? = null,
    var native: String? = null,
    var phone: String? = null,
    var capital: String? = null,
    var currency: String? = null
)