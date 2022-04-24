package com.mmh.hssoftapp.data.entities

import androidx.room.Entity

@Entity
data class Country(
    val code: String? = null,
    val name: String? = null,
    val native: String? = null,
    val capital: String? = null,
    val phone: String? = null,
    val currency: String? = null,
    val languages: List<String>? = null,
    val states: List<String>? = null
)