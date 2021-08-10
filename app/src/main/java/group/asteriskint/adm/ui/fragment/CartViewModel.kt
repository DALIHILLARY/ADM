package group.asteriskint.adm.ui.fragment

import androidx.lifecycle.ViewModel
import group.asteriskint.adm.model.CartItem

class CartViewModel : ViewModel() {
    private val cartList = listOf(
        CartItem(2,"apple",12,5000.00),
        CartItem(1,"apple1",12,50340.00),
        CartItem(3,"apple3",12,50430.00),
        CartItem(4,"apple4",12,500430.00),
        CartItem(5,"apple5",12,50003.00),
        CartItem(6,"apple7",12,50050.00)
    )

    fun fetchCart(id : Int?) : List<CartItem> {
//        TODO("fetch data from repo and cloud")
        return cartList
    }
}