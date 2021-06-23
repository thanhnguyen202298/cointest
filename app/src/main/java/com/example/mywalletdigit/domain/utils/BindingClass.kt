package com.example.mywalletdigit.domain.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mywalletdigit.domain.models.CCurrencyModel
import com.example.mywalletdigit.presentation.adapter.BaseAdapter

@BindingAdapter("data")
fun onBindadapter(view: RecyclerView, data: ArrayList<CCurrencyModel>) {
    (view.adapter as BaseAdapter<CCurrencyModel>).apply {
        if (list.isNullOrEmpty()) list = ArrayList()
        val start = list!!.size
        list?.addAll(data)
        notifyDataSetChanged()
    }

}