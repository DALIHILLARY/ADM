package group.asteriskint.adm.ui.fragment

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import group.asteriskint.adm.model.CartItem
import group.asteriskint.adm.model.Product
import group.asteriskint.adm.repository.Repository
import kotlinx.coroutines.runBlocking

class ItemShowViewModel : ViewModel() {
//    private val productList = listOf(
//        Product(id = 1, name = "5YYGFF",price = 4534557.47, category_id = 4),
//        Product(id = 2,name = "HBDWEFWGER",price = 52567.47, category_id = 4),
//        Product(id = 3,name = "eGERGFSVSVu",price = 744567.47, category_id = 1),
//        Product(id = 4,name = "eFSGFGSFVFgu",price = 22454567.47, category_id = 1),
//        Product(id = 5,name = "eeGFFGFGVgu",price = 3344567.47, category_id = 2),
//        Product(id = 6,name = "GRFGERGSGegu",price = 554567.47, category_id = 2),
//        Product(id = 7,name = "THTHFHBgu",price = 9994567.47, category_id = 3),
//        Product(id = 8,name = "RRGGfuegu",price = 88867.47, category_id = 3),
//        Product(id = 9,name = "AERWQRDSFGuegu",price = 774567.47, category_id = 5),
//        Product(id = 10,name = "TT3TTT",price =3334567.47, category_id = 5),
//    )
    val quantity = MutableLiveData(1)


    fun add() {
        val currentQuantity = quantity.value
        quantity.value = currentQuantity!! + 1
    }
    fun subtract() {
        val currentQuantity = quantity.value
        quantity.value = currentQuantity!! - 1
    }

//    fun fetchProduct(productId : Int?) : Product {
////        TODO("DO NETWORK CALL FOR PRODUCT BY ID")
//        return productList.first { it.id == productId }
//    }
    fun addToCart(product : Product, context: Context) : Boolean{
//        TODO("FETCH USER CART BY ID AND PERSIST & SYNC DATA IN DATABASE")
        var result = false
        quantity.value?.let {
            result = true
            runBlocking{Repository(context).insertCartItem(CartItem(product.id,product.name,it,it*product.price))}
        }
        return result

    }
}