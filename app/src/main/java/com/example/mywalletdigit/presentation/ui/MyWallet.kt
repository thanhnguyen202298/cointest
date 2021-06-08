package com.example.mywalletdigit.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import com.example.mywalletdigit.R
import com.example.mywalletdigit.domain.models.CCurrencyModel
import com.example.mywalletdigit.presentation.adapter.CoinAdapter
import com.example.mywalletdigit.presentation.adapter.OnAdapterListener
import com.example.mywalletdigit.presentation.adapter.WrapGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_my_wallet.*
import kotlin.random.Random

class MyWallet : AppCompatActivity(), OnAdapterListener<CCurrencyModel> {
    private lateinit var listadatper: CoinAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_wallet)

        listadatper = CoinAdapter(this, this, R.layout.item_wallet)
        val wrapGridLayoutManager = WrapGridLayoutManager(this, 1, GridLayout.VERTICAL, false)

        listowner.apply {
            adapter = listadatper
            layoutManager = wrapGridLayoutManager
            setHasFixedSize(true)
        }

        val aarr = ArrayList<CCurrencyModel>()
        for (i in 0..5) {
            val item = CCurrencyModel("BTC$-${i}")
            item.amount = 98.076
            aarr.add(item)
        }

        listadatper.list = aarr
        listadatper.notifyDataSetChanged()
    }
}