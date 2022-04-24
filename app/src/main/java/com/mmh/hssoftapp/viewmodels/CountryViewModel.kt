package com.mmh.hssoftapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmh.hssoftapp.data.repositories.CountryRepository
import com.mmh.hssoftapp.utils.DispatcherProvider
import com.mmh.hssoftapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val repository: CountryRepository, private val dispatchers: DispatcherProvider
) : ViewModel() {

    sealed class CountryEvent {
        class Success(val resultList: MutableList<String>) : CountryEvent()
        class Failure(val errorText: String) : CountryEvent()
        object Empty : CountryEvent()
    }

    private val _countryList = MutableStateFlow<CountryEvent>(CountryEvent.Empty)
    val countryList: StateFlow<CountryEvent> = _countryList

    private val _countryData = MutableStateFlow<CountryEvent>(CountryEvent.Empty)
    val countryData: StateFlow<CountryEvent> = _countryData

    fun getCountryList() {
        val paramObject = JSONObject()
        paramObject.put("query", "{countries{name}}")
        viewModelScope.launch (dispatchers.io) {
            when (val countryListResponse = repository.getData(paramObject.toString())) {
                is Resource.Error -> _countryList.value = CountryEvent.Failure(countryListResponse.message!!)
                is Resource.Success -> {
                    val countryListStr = countryListResponse.data!!
                    _countryList.value = CountryEvent.Success(countryListStr)
                }
            }
        }
    }

    fun getCountryData(code: String) {
        val paramObject = JSONObject()
        paramObject.put(
            "query",
            "{country(code: \"$code\") {code,name,native,capital,phone,currency,languages {name},states {name}}}")
        viewModelScope.launch (dispatchers.io) {
            when (val countryDataResponse = repository.getData(paramObject.toString())) {
                is Resource.Error -> _countryData.value = CountryEvent.Failure(countryDataResponse.message!!)
                is Resource.Success -> {
                    val countryDataStr = countryDataResponse.data!!
                    _countryData.value = CountryEvent.Success(countryDataStr)
                }
            }
        }
    }
}