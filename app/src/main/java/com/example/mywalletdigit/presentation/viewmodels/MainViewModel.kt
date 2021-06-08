package com.example.mywalletdigit.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mywalletdigit.data.RespositoryCoin
import com.example.mywalletdigit.domain.models.CCurrencyModel
import kotlinx.coroutines.*
import java.lang.Exception

class MainViewModel(val respoCoin : RespositoryCoin) : ViewModel() {
    private val jobMain = Job()
    private val coroutineCtx = jobMain + Dispatchers.IO
    private val scope = CoroutineScope(coroutineCtx)
    val listLiveData: MutableLiveData<ArrayList<CCurrencyModel>> = MutableLiveData()
    val error: MutableLiveData<Exception> = MutableLiveData()

    private val handlerException = { e: Exception ->
       scope.launch {
           withContext(Dispatchers.Main){
               error.value = e
           }
       }

    }


    fun getData(counter:String) {
        scope.launch {
            val result = respoCoin.getCoinInfo(counter, handlerException)
            if (result != null)
                withContext(Dispatchers.Main) { listLiveData.value = result }

        }
    }
}