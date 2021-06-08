package com.example.mywalletdigit

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.GridLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mywalletdigit.domain.models.CCurrencyModel
import com.example.mywalletdigit.domain.utils.ConnectionHelper
import com.example.mywalletdigit.presentation.adapter.CoinAdapter
import com.example.mywalletdigit.presentation.adapter.OnAdapterListener
import com.example.mywalletdigit.presentation.adapter.WrapGridLayoutManager
import com.example.mywalletdigit.presentation.components.NetworkComponent
import com.example.mywalletdigit.presentation.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnAdapterListener<CCurrencyModel>,
    ConnectionHelper.ShowConnectionListener {
    private lateinit var listadatper: CoinAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycle.addObserver(NetworkComponent(this, this))

        mainViewModel = ViewModelProvider.NewInstanceFactory().create(MainViewModel::class.java)
        try {

        } catch (ex: Exception) {
            ex.printStackTrace()
        }

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
            listadatper.notifyDataSetChanged()
        })

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

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == NetworkComponent.RequestCode) {
            if(checkSelfPermission(NetworkComponent.permission[0])==PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(NetworkComponent.permission[1])==PackageManager.PERMISSION_GRANTED)
                    mainViewModel.getData()

        } else
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

}