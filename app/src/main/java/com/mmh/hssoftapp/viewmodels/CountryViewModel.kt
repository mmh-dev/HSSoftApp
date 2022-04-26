package com.mmh.hssoftapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.mmh.hssoftapp.data.entities.Country
import com.mmh.hssoftapp.data.repositories.CountryRepository
import com.mmh.hssoftapp.utils.DispatcherProvider
import com.mmh.hssoftapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val repository: CountryRepository, private val dispatchers: DispatcherProvider,
) : ViewModel() {

    sealed class CountryEvent {
        class Success<T>(val resultData: T) : CountryEvent()
        class Failure<T>(val resultData: T, val errorText: String) : CountryEvent()
        object Empty : CountryEvent()
    }

    private val _countryList = MutableStateFlow<CountryEvent>(CountryEvent.Empty)
    val countryList: StateFlow<CountryEvent> = _countryList

    private val _countryData = MutableStateFlow<CountryEvent>(CountryEvent.Empty)
    val countryData: StateFlow<CountryEvent> = _countryData

    fun getCountries() {
        val paramObject = JSONObject()
        paramObject.put("query", "{countries{code,name}}")
        viewModelScope.launch(dispatchers.io) {
            when (val countryListResponse = repository.getOnlineData(paramObject.toString())) {
                is Resource.Success -> {
                    val mainObj = JSONObject(countryListResponse.data.toString())
                    val dataObj = mainObj.getJSONObject("data")
                    val countriesObj = dataObj.getJSONArray("countries")
                    for (i in 0 until countriesObj.length()) {
                        val countryObj = countriesObj.getJSONObject(i)
                        val country = Country(code = countryObj.getString("code").toString(), name = countryObj.getString("name").toString())
                        repository.insertCountry(country)
                        val list = repository.getAllCountries()
                        _countryList.value = CountryEvent.Success(list)
                    }
                }
                is Resource.Error -> {
                    val list = repository.getAllCountries()
                    _countryList.value = CountryEvent.Failure(list, countryListResponse.message.toString())
                }
            }
        }
    }

    fun getCountryData(code: String) {
        val paramObject = JSONObject()
        paramObject.put("query", "{country(code: \"$code\") {code,name,phone,capital,currency}}")
        viewModelScope.launch(dispatchers.io) {
            when (val countryListResponse = repository.getOnlineData(paramObject.toString())) {
                is Resource.Success -> {
                    val mainObj = JSONObject(countryListResponse.data.toString())
                    val dataObj = mainObj.getJSONObject("data")
                    val countryObject = dataObj.getJSONObject("country")
                    val country: Country = Gson().fromJson(countryObject.toString(), Country::class.java)
                    repository.updateCountry(country)
                    val savedCountry = repository.getCountryData(code)
                    _countryData.value = CountryEvent.Success(savedCountry)
                }
                is Resource.Error -> {
                    val savedCountry = repository.getCountryData(code)
                    _countryData.value = CountryEvent.Failure(savedCountry, countryListResponse.message.toString())
                }
            }
        }
    }
}