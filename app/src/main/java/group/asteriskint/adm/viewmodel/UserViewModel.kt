package group.asteriskint.adm.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import group.asteriskint.adm.model.User
import group.asteriskint.adm.repository.Repository

class UserViewModel : ViewModel() {
    val user = MutableLiveData<User>(null)

    fun login(email: String, password: String) : Boolean {
//        TODO("do network call and save to dbn return result")
        return true
    }
    fun getUser(mContext : Context) = Repository(mContext).getUserLiveData()
}