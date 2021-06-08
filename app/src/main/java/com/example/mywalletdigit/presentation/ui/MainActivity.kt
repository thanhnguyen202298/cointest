package com.example.mywalletdigit.presentation.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.GridLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.mywalletdigit.R
import com.example.mywalletdigit.data.ccurrency
import com.example.mywalletdigit.domain.models.CCurrencyModel
import com.example.mywalletdigit.domain.utils.ConnectionHelper
import com.example.mywalletdigit.presentation.adapter.CoinAdapter
import com.example.mywalletdigit.presentation.adapter.OnAdapterListener
import com.example.mywalletdigit.presentation.adapter.WrapGridLayoutManager
import com.example.mywalletdigit.presentation.components.NetworkComponent
import com.example.mywalletdigit.presentation.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), OnAdapterListener<CCurrencyModel>,
    ConnectionHelper.ShowConnectionListener {
    private lateinit var listadatper: CoinAdapter
    private val mainViewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycle.addObserver(NetworkComponent(this, this))
        viewSetup()
    }

    private fun viewSetup() {
        listadatper = CoinAdapter(this, this)
        val wrapGridLayoutManager = WrapGridLayoutManager(this, 1, GridLayout.VERTICAL, false)

        list.apply {
            adapter = listadatper
            layoutManager = wrapGridLayoutManager
            setHasFixedSize(true)
        }

        mainViewModel.listLiveData.observe(this, Observer {
            listadatper.list = it
            if(it.isNullOrEmpty()){
                showMessageDialog("No data for this choice")
            }
            listadatper.notifyDataSetChanged()
            loading.visibility = View.GONE
        })

        mainViewModel.error.observe(this, {
            loading.visibility= View.GONE
            showMessageDialog(it.localizedMessage)
        })
        animatedY(50f)

        val adaSpin = ArrayAdapter(
            this, android.R.layout.simple_spinner_dropdown_item,
            ccurrency.toList() as ArrayList<String>
        )

        searchCC.adapter = adaSpin
        searchCC.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                loading.visibility= View.VISIBLE
                mainViewModel.getData(ccurrency[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        mywallet.setOnClickListener{l->
            val intent = Intent(this,MyWallet::class.java)
            startActivity(intent);
        }
    }

    private fun animatedY(heig: Float) {
        swipeup.animate()
            .setDuration(1000)
            .translationY(heig)
            .withEndAction({
                if (heig < 0)
                    animatedY(Math.abs(heig))
                else animatedY(-heig)
            })
            .start()

    }

    override fun onShowConnection(isConnection: Boolean) {
        if (isConnection == false) {
            AlertDialog.Builder(this)
                .setTitle("Network warning")
                .setMessage("no internet connection")
                .setPositiveButton("Ok") { dialog, which ->
                    dialog.dismiss()
                }.show()
        }
    }

    private fun showMessageDialog(content: String) {
        AlertDialog.Builder(this)
            .setTitle("Infomation your action")
            .setMessage(content)
            .setPositiveButton("Ok") { dialog, which ->
                dialog.dismiss()
            }.show()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        val execOnResult = { mainViewModel.getData(ccurrency[0]) }
        if (requestCode == NetworkComponent.RequestCode) {
            onPermissionResult(this, execOnResult)
        } else
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


}