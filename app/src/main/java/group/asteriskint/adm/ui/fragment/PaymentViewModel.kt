package group.asteriskint.adm.ui.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PaymentViewModel : ViewModel() {
    private val TAG = javaClass.simpleName
    val paymentStatus = MutableLiveData<Result<String>>()

    fun makePayment() {
//        TODO("Perform network call for payment and return result")
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    paymentStatus.postValue(Result.success("PaymentSuccessful"))
                }catch (e : Throwable) {
                    Log.e(TAG,"Something happened with payment",e)
                    paymentStatus.postValue(Result.failure(e))
                }
            }
        }
    }
}