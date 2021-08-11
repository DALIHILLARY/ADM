package group.asteriskint.adm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import group.asteriskint.adm.model.User

class UserViewModel : ViewModel() {
    val user = MutableLiveData<User>(null)

    fun login(email: String, password: String) : Boolean {
//        TODO("do network call and save to dbn return result")
        return true
    }
}