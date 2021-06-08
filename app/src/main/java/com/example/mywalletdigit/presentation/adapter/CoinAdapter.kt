package com.example.mywalletdigit.presentation.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.mywalletdigit.R
import com.example.mywalletdigit.domain.models.CCurrencyModel
import kotlinx.android.synthetic.main.item_layout.view.*

class CoinAdapter(activity:Activity, listener: OnAdapterListener<CCurrencyModel>): BaseAdapter<CCurrencyModel>(activity,listener) {
    override fun createView(
        context: Context?,
        viewGroup: ViewGroup?,
        viewType: Int
    ): BaseViewHolder {
        val inflater = LayoutInflater.from(getActivity())
        val view = inflater.inflate(R.layout.item_layout,null,false)
        return BaseViewHolder(view)
    }

    override fun bindView(item: CCurrencyModel?, position: Int, baseViewHolder: BaseViewHolder?) {
        baseViewHolder?.itemView?.apply {
            name.setText(item?.name)
            selprice.setText("${item?.sellPrice}")
            buyprice.setText("${item?.buyPrice}")
            unit1.setText("(${item?.counter})")
            unit2.setText("(${item?.counter})")

            Glide.with(this)
                .load(item?.icon?:return)
                .placeholder(getActivity().resources.getDrawable(R.drawable.coinblank))
                .into(iconCoin)
        }

    }
}