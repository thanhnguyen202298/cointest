package com.example.mywalletdigit.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mywalletdigit.data.RespositoryCoin
import com.example.mywalletdigit.domain.models.CCurrencyModel
import kotlinx.coroutines.*
import java.lang.Exception

class MainViewModel : ViewModel() {
    private val jobMain = Job()
    private val coroutineCtx = jobMain + Dispatchers.IO
    private val scope = CoroutineScope(coroutineCtx)
    val listLiveData: MutableLiveData<ArrayList<CCurrencyModel>> = MutableLiveData()
    private val respoCoin = RespositoryCoin()

    private val handlerException = { e: Exception ->

    }

    fun getData() {
        scope.launch {
            val result = respoCoin.getCoinInfo("USD", handlerException)
            if (result != null)
                withContext(Dispatchers.Main) { listLiveData.value = result }

        }
    }
}