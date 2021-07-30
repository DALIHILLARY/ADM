package group.asteriskint.adm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import group.asteriskint.adm.model.User

class UserViewModel : ViewModel() {
    val user : MutableLiveData<User>  by lazy {
        MutableLiveData<User>().also {
            null
        }
     }

    fun login(email: String, password: String) : Boolean {
        TODO("do network call and save to dbn return result")
    }
}