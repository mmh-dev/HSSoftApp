package com.mmh.hssoftapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mmh.hssoftapp.data.entities.Country
import com.mmh.hssoftapp.databinding.CountryListBinding

class CountryAdapter(val onItemClick: (code: String) -> Unit) :
    ListAdapter<Country, CountryAdapter.CountryViewHolder>(CountryDiffUtils()) {

    inner class CountryViewHolder(private val binding: CountryListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(country: Country) {
            with(binding) {
                name.text = country.name
                root.setOnClickListener {
                    onItemClick(country.code)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = CountryListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CountryDiffUtils : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }
    }
}
