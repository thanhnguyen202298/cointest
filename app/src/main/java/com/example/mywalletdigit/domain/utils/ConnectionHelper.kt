package com.example.mywalletdigit.domain.utils

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import java.security.Permissions
import java.util.jar.Manifest


class ConnectionHelper {
    val ACTION_WIFI_CONNECTION = "android.net.wifi.WIFI_STATE_CHANGED"
    val ACTION_CONNECTION = "android.net.conn.CONNECTIVITY_CHANGE"

    private var mConnectionReceiver: ConnectionReceiver? = null
    private var mContext: Activity? = null
    private var mListener: ShowConnectionListener? = null

    interface ShowConnectionListener {
        fun onShowConnection(isConnection: Boolean)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun checkPermission(permiss:Array<String>):ArrayList<String>{
        val sperm = ArrayList<String>()
        for (s in permiss)
        {
            if(mContext?.checkSelfPermission(s)!= PackageManager.PERMISSION_GRANTED)
                sperm.add(s)
        }
        return sperm
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun requestPermission(permiss: Array<String>,requestCode: Int){
        mContext?.requestPermissions(permiss,requestCode)
    }

    constructor(m_context: Activity?, listener: ShowConnectionListener?) {
        mContext = m_context
        mListener = listener
    }

    fun registerBroadcastConnection() {
        if (mConnectionReceiver == null) {
            mConnectionReceiver = object : ConnectionReceiver() {
                override fun handleConnection(statusConnection: Int) {

                    mListener!!.onShowConnection(
                        statusConnection != NetworkUtils.TYPE_NOT_CONNECTED
                    )

                }
            }
        }
        val intentFilter = IntentFilter(ACTION_WIFI_CONNECTION)
        intentFilter.addAction(ACTION_CONNECTION)
        mContext?.registerReceiver(mConnectionReceiver, intentFilter)
    }

    fun unregisterBroadcastConnection(context: Context) {
        if (mConnectionReceiver != null) {
            context.unregisterReceiver(mConnectionReceiver)
            mConnectionReceiver = null
        }
    }

    open class ConnectionReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val statusConnection: Int = NetworkUtils.getConnectivityStatus(context ?: return)
            handleConnection(statusConnection)
        }

        open fun handleConnection(statusConnection: Int) = Unit
    }
}