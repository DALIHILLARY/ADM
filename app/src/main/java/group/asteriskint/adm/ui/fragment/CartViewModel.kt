package group.asteriskint.adm.ui.fragment

import android.content.Context
import androidx.lifecycle.ViewModel
import group.asteriskint.adm.model.CartItem
import group.asteriskint.adm.repository.Repository
import kotlinx.coroutines.runBlocking

class CartViewModel : ViewModel() {

    fun fetchCart(userId : Int?, mContext : Context) : List<CartItem> {
//        TODO("fetch data from repo and cloud")
        val cartList = runBlocking { Repository(mContext).getCartItem() }

        return cartList
    }
}