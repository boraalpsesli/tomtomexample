package com.example.tomtomexample

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tomtomexample.network.Network
import com.example.tomtomexample.response.testResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Response


class LandingFragmentViewModel:ViewModel() {
    var items=MutableLiveData<testResponse?>()
    fun readJsonFromAssets(context: Context, fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }
    fun parseJsonToModel(jsonString: String): testResponse {
        val gson = Gson()
        return gson.fromJson(jsonString, object : TypeToken<testResponse>() {}.type)
    }
        fun search(term: String){
            Network.service.doSearchByQuery(term).enqueue(object:retrofit2.Callback<testResponse>{
                override fun onResponse(
                    call: Call<testResponse>,
                    response: Response<testResponse>
                ) {
                        if(response.body()!=null && response.isSuccessful){
                            val temp=response.body()
                            items.postValue(temp)
                        }
                    else{
                        println("Error On Response")
                    }
                }

                override fun onFailure(call: Call<testResponse>, t: Throwable) {
                            println("Error")
                    }

            })
        }
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo
            @Suppress("DEPRECATION")
            return networkInfo != null && networkInfo.isConnected
        }
    }
}