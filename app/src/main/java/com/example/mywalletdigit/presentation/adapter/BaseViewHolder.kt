package com.example.mywalletdigit.presentation.adapter

import android.util.SparseArray
import android.view.View

import androidx.recyclerview.widget.RecyclerView


class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val mMapView: SparseArray<View>
    fun initViewList(idList: IntArray) {
        for (id in idList) initViewById(id)
    }

    fun initViewById(id: Int) {
        val view: View? = view?.findViewById(id)
        if (view != null) mMapView.put(id, view)
    }

    val view: View?
        get() = getView(0)

    fun getView(id: Int): View {
        if (mMapView.indexOfKey(id) < 0) return mMapView[id] else initViewById(id)
        return mMapView[id]
    }

    init {
        mMapView = SparseArray<View>()
        mMapView.put(0, view)
    }
}