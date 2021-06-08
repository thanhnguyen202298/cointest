package com.example.mywalletdigit.presentation.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.mywalletdigit.R
import com.example.mywalletdigit.domain.models.CCurrencyModel
import kotlinx.android.synthetic.main.item_market.view.*

class CoinAdapter(
    activity: Activity,
    listener: OnAdapterListener<CCurrencyModel>,
    val idlayout: Int?=null
) : BaseAdapter<CCurrencyModel>(activity, listener) {
    override fun createView(
        context: Context?,
        viewGroup: ViewGroup?,
        viewType: Int
    ): BaseViewHolder {
        val inflater = LayoutInflater.from(getActivity())

        val view = if (idlayout == null)
            inflater.inflate(R.layout.item_market, null, false)
        else inflater.inflate(idlayout, null, false)
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
                .load(item?.icon ?: return)
                .placeholder(getActivity().resources.getDrawable(R.drawable.coinblank))
                .into(iconCoin)
        }

        val amount = baseViewHolder?.itemView?.findViewById<TextView>(R.id.amount)
        amount?.setText("${item?.amount}")

    }
}