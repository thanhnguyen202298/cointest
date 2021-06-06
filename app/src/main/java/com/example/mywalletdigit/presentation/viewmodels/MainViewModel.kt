package com.example.mywalletdigit.presentation.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {
    suspend fun getData(){
        withContext(Dispatchers.IO){

        }
    }
}