package com.mmh.hssoftapp.utils

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.mmh.hssoftapp.R
import com.mmh.hssoftapp.ui.fragments.DetailsFragment
import com.mmh.hssoftapp.ui.fragments.ListFragment

fun Fragment.showToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}