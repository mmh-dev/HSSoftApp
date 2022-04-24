package com.mmh.hssoftapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mmh.hssoftapp.GraphQLInstance
import com.mmh.hssoftapp.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val binding: FragmentDetailsBinding = FragmentDetailsBinding.inflate(layoutInflater)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.name.text = this.arguments?.getString("pos")
    }

//    private fun getCountryData(code: String) {
//        val retrofit = GraphQLInstance.COUNTRY_API
//        val paramObject = JSONObject()
//        paramObject.put(
//            "query",
//            "{country(code: \"$code\") {code,name,native,capital,phone,currency,languages {name},states {name}}}"
//        )
//        GlobalScope.launch {
//            try {
//                val response = retrofit.queryData(paramObject.toString())
//                Log.i("tag", response.body().toString())
//                if (response.isSuccessful) {
//
//                }
//
//            } catch (e: java.lang.Exception) {
//                e.printStackTrace()
//            }
//        }
//    }
}