package com.example.mywalletdigit.cores

import com.example.mywalletdigit.data.RespositoryCoin
import com.example.mywalletdigit.presentation.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appmodule = module{
    single { RespositoryCoin() }
    viewModel { MainViewModel(get()) }
}