package com.example.mywalletdigit.data

import com.example.mywalletdigit.domain.models.ResponseCurrencies
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CooinService {
    @GET("v3/price/all_prices_for_mobile")
    fun getData(@Query("counter_currency") couter:String):Deferred<ResponseCurrencies>

}