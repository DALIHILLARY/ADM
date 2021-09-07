package group.asteriskint.adm.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import java.net.URL

class SplashActivityViewModel : ViewModel() {
    val isConnected = MutableLiveData(false)

    fun checkConnection() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                while(isActive) {
                    try {
                        val url = URL("https://ec.adm.ug")
                        val connection  = url.openConnection()
                        connection.connect()
                        if(isConnected.value == false)
                            isConnected.postValue(true)
                    } catch (e: Exception) {
                        Log.e("Comet","FAILED TO CONNECT TO SERVER",e)
                        if(isConnected.value == true)
                            isConnected.postValue(false)
                    }
                    delay(1000L)
                }
            }
        }
    }
}