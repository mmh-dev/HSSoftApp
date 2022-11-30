package com.mmh.hssoftapp.ui.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mmh.hssoftapp.R
import com.mmh.hssoftapp.data.entities.Country
import com.mmh.hssoftapp.databinding.FragmentListBinding
import com.mmh.hssoftapp.ui.adapters.CountryAdapter
import com.mmh.hssoftapp.utils.showToast
import com.mmh.hssoftapp.viewmodels.CountryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private val TAG = ListFragment::class.simpleName

@AndroidEntryPoint
class ListFragment : Fragment() {

    private val binding: FragmentListBinding by lazy {
        FragmentListBinding.inflate(layoutInflater)
    }

    private val countryAdapter = CountryAdapter(onItemClick = { code: String -> adapterOnClick(code) })
    private var countries = mutableListOf<Country>()
    private val viewModel: CountryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.countriesView.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = countryAdapter
        }

        viewModel.getCountries()
        lifecycleScope.launch {
            viewModel.countryList.collect { event ->
                when (event) {
                    is CountryViewModel.CountryEvent.Success<*> -> {
                        countries = event.resultData as MutableList<Country>
                        countryAdapter.submitList(countries)
                    }
                    is CountryViewModel.CountryEvent.Failure<*> -> {
                        Log.d(TAG, "failure: " + event.errorText)
                        showToast(getString(R.string.no_internet))
                        countries = event.resultData as MutableList<Country>
                        countryAdapter.submitList(countries)
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun adapterOnClick(code: String) {
        val bundle = Bundle()
        bundle.putString("code", code)
        val fragment = DetailsFragment()
        fragment.arguments = bundle
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            parentFragmentManager.beginTransaction().replace(R.id.countries_view, fragment).addToBackStack(null).commit()
        } else {
            parentFragmentManager.beginTransaction().replace(R.id.details_view, fragment).commit()
        }
    }
}