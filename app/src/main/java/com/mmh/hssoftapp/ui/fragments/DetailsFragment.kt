package com.mmh.hssoftapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.mmh.hssoftapp.R
import com.mmh.hssoftapp.data.entities.Country
import com.mmh.hssoftapp.databinding.FragmentDetailsBinding
import com.mmh.hssoftapp.utils.showToast
import com.mmh.hssoftapp.viewmodels.CountryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

private val TAG = DetailsFragment::class.simpleName

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val binding: FragmentDetailsBinding by lazy {
        FragmentDetailsBinding.inflate(layoutInflater)
    }

    private val viewModel: CountryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val code = this.arguments?.getString("code")
        viewModel.getCountryData(code.toString())
        lifecycleScope.launchWhenStarted {
            viewModel.countryData.collect { event ->
                when (event) {
                    is CountryViewModel.CountryEvent.Success<*> -> {
                        val country = event.resultData as Country
                        binding.apply {
                            codeInfo.text = country.code
                            nameInfo.text = country.name
                            phoneInfo.text = country.phone
                            capitalInfo.text = country.capital
                            currencyInfo.text = country.currency
                        }
                    }
                    is CountryViewModel.CountryEvent.Failure<*> -> {
                        val country = event.resultData as Country
                        binding.name.text = country.toString()
                        Log.d(TAG, "failure: " + event.errorText)
                        showToast(getString(R.string.no_internet))
                    }
                    else -> Unit
                }
            }
        }
    }
}