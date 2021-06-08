package com.example.mywalletdigit.data

import com.example.mywalletdigit.domain.models.CCurrencyModel
import retrofit2.Response
import java.io.IOException
import java.lang.Exception

class RespositoryCoin: BaseResponseWrapper() {

    suspend fun getCoinInfo(couter: String, onerror: (Exception)-> Unit):ArrayList<CCurrencyModel>?{
        val coinResult = apiCallResult(
            call= { RetrofitService.CooinSevice.getCoinData(couter).await() },
            onerror
        )
        return coinResult?.data
    }

}

open class BaseResponseWrapper {
    private suspend fun <T : Any> baseResult(
        call: suspend () -> Response<T>
    ): Result<T> {
        val respone = call.invoke()
        if (respone.isSuccessful) return Result.Success(respone.body()!!)
        return Result.Error(IOException("${respone.message()}"))
    }

    suspend fun <T : Any> apiCallResult(call: suspend () -> Response<T>, onerror: (Exception)-> Unit): T? {
        val result: Result<T> = baseResult(call)
        when(result){
            is Result.Success -> return result.data
            else -> onerror((result as Result.Error).exception)
        }
        return null
    }
}