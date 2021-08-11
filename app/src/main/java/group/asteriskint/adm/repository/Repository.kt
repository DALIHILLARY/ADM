package group.asteriskint.adm.repository

import android.content.Context
import androidx.lifecycle.LiveData
import group.asteriskint.adm.model.CartItem
import group.asteriskint.adm.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val mContext : Context?) {
    private val dbInstance = AsterisksDatabase.dbinstance(mContext)
    private val asteriskDao = dbInstance.databaseDao()

    suspend fun getUser() : User? = withContext(Dispatchers.IO) {asteriskDao.getuser()}

    fun getUserLiveData() : LiveData<User?> = asteriskDao.getLiveUser()

    suspend fun insertUser(user : User) = withContext(Dispatchers.IO) { asteriskDao.insertUser(user)}

    suspend fun insertCartItem(item: CartItem) = withContext(Dispatchers.IO) { asteriskDao.insertCartItem(item)}

    suspend fun removeUser(user : User) = withContext(Dispatchers.IO) { asteriskDao.deleteUser(user)}

    suspend fun removeCartItem(item: CartItem) = withContext(Dispatchers.IO) {asteriskDao.deleteCartItem(item)}

    suspend fun clearCart() = withContext(Dispatchers.IO) {asteriskDao.clearCart()}

    suspend fun getCartItem() : List<CartItem> = withContext(Dispatchers.IO) {asteriskDao.getCartItems()}
}