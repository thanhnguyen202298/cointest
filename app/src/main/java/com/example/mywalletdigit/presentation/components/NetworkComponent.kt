package com.example.mywalletdigit.presentation.components

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.example.mywalletdigit.domain.utils.ConnectionHelper

class NetworkComponent(
    private val m_context: Activity,
    private val m_listener: ConnectionHelper.ShowConnectionListener
) :
    LifecycleObserver {
    private var mConnectionHelper: ConnectionHelper? = null

    companion object {
        val RequestCode = 919
        val permission =
            arrayOf(Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE)

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        mConnectionHelper = ConnectionHelper(m_context, m_listener)
        mConnectionHelper!!.registerBroadcastConnection()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        requestPermiss()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun requestPermiss() {
        val needs = mConnectionHelper?.checkPermission(permission)
//        if (needs != null && needs.size > 0) {
        mConnectionHelper?.requestPermission(permission, RequestCode)
//        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        if (mConnectionHelper == null) return
        mConnectionHelper!!.unregisterBroadcastConnection(m_context)
        mConnectionHelper = null
    }

}