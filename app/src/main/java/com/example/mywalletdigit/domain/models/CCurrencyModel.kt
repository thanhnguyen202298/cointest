package com.example.mywalletdigit.domain.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class CCurrencyModel (

    @SerializedName("name")
    @Expose
    var name: String = "",

    @SerializedName("buy_price")
    @Expose
    var buyPrice: Double = 0.0,

    @SerializedName("sell_price")
    @Expose
    var sellPrice: Double = 0.0,

    @SerializedName("icon")
    @Expose
    var icon: String = "",

    @SerializedName("base")
    @Expose
    var base: String = "",

    @SerializedName("counter")
    @Expose
    var counter: String = "",){
    var amount: Double = 0.0
}
