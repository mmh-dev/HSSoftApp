package com.mmh.hssoftapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mmh.hssoftapp.R
import com.mmh.hssoftapp.databinding.ActivityMainBinding
import com.mmh.hssoftapp.ui.fragments.ListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().replace(R.id.countries_view, ListFragment()).commit()
    }
}