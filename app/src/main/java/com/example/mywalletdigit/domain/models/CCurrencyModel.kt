package com.example.mywalletdigit.domain.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CCurrencyModel {
    @SerializedName("base")
    @Expose
    var base: String? = null

    @SerializedName("counter")
    @Expose
    var counter: String? = null

    @SerializedName("buy_price")
    @Expose
    var buyPrice: String? = null

    @SerializedName("sell_price")
    @Expose
    var sellPrice: String? = null

    @SerializedName("icon")
    @Expose
    var icon: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null
}