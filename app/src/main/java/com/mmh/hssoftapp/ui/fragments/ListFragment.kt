package com.mmh.hssoftapp.ui.fragments

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.mmh.hssoftapp.R
import com.mmh.hssoftapp.databinding.FragmentListBinding
import com.mmh.hssoftapp.viewmodels.CountryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.json.JSONObject

@AndroidEntryPoint
class ListFragment : Fragment() {

    private val binding: FragmentListBinding by lazy {
        FragmentListBinding.inflate(layoutInflater)
    }

    private lateinit var adapter: ArrayAdapter<String>
    private val list = mutableListOf<String>()
    private val viewModel: CountryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_activated_1, list)
        binding.listView.adapter = adapter

        viewModel.getCountryList()
        lifecycleScope.launchWhenStarted {
            viewModel.countryList.collect { event ->
                when (event) {
                    is CountryViewModel.CountryEvent.Success -> {
                        val obj = JSONObject(event.resultText)
                        val data = obj.getJSONObject("data")
                        val countries = data.getJSONArray("countries")
                        var country: JSONObject
                        for (i in 0 until countries.length()) {
                            country = countries.getJSONObject(i)
                            list.add(country.getString("name").toString())
                        }
                        adapter.notifyDataSetChanged()
                    }
                    is CountryViewModel.CountryEvent.Failure -> {

                    }
                    else -> Unit
                }
            }
        }
//        getCountries()
        binding.listView.setOnItemClickListener { adapterView, view, position, id ->
            val bundle = Bundle()
            bundle.putString("pos", position.toString())
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                parentFragmentManager.beginTransaction().replace(R.id.countries_view, fragment).addToBackStack(null).commit()
            } else {
                parentFragmentManager.beginTransaction().replace(R.id.details_view, fragment).commit()
            }
        }
    }

//    private fun getCountries() {
//        val retrofit = GraphQLInstance.COUNTRY_API
//        val paramObject = JSONObject()
//        paramObject.put("query", "{countries{name}}")
//        GlobalScope.launch {
//            try {
//                val response = retrofit.queryData(paramObject.toString())
//                if (response.isSuccessful) {
//
//                    val obj = JSONObject(response.body().toString())
//                    val data = obj.getJSONObject("data")
//                    val countries = data.getJSONArray("countries")
//                    var country: JSONObject
//                    for (i in 0 until countries.length()) {
//                        country = countries.getJSONObject(i)
//                        list.add(country.getString("name").toString())
//                    }
//                    adapter.notifyDataSetChanged()
//                }
//
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//    }


}