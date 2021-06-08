package com.example.mywalletdigit.presentation.adapter

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mywalletdigit.domain.models.CCurrencyModel

abstract class BaseAdapter<T> @JvmOverloads constructor(
    private val activity: Activity,
    private var listener: OnAdapterListener<T>? = null
) :
    RecyclerView.Adapter<BaseViewHolder>() {
    //region Properties
    var list: List<T>? = ArrayList()
    var prePosChoose = -1

    //endregion
    //region Getter - Setter
    fun setOnAdapterListener(listener: OnAdapterListener<T>?) {
        this.listener = listener
    }

    fun getListener(): OnAdapterListener<T>? {
        return listener
    }

    //endregion
    //region Abstract methods
    protected abstract fun createView(
        context: Context?,
        viewGroup: ViewGroup?,
        viewType: Int
    ): BaseViewHolder

    protected abstract fun bindView(item: T?, position: Int, baseViewHolder: BaseViewHolder?)

    //endregion
    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): com.example.mywalletdigit.presentation.adapter.BaseViewHolder {
        return createView(activity, viewGroup, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        bindView(getItem(position), position, holder)
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    fun getItem(index: Int): T? {
        return if (list != null && index >= 0 && index < list!!.size) list!![index] else null
    }

    fun getActivity(): Context {
        return activity
    }
    //endregion
}

interface OnAdapterListener<T>{
    fun onItemClick(position:Int, data:T,view:View)=Unit
}