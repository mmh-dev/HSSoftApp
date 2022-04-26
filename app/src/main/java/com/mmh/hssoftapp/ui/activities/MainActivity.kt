package com.mmh.hssoftapp.ui.activities

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mmh.hssoftapp.R
import com.mmh.hssoftapp.databinding.ActivityMainBinding
import com.mmh.hssoftapp.ui.fragments.ListFragment
import com.mmh.hssoftapp.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            supportFragmentManager.beginTransaction().replace(R.id.countries_view, ListFragment()).commit()
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.countries_view, ListFragment()).commit()
        }
    }
}