package group.asteriskint.adm.ui.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.kittinunf.fuel.Fuel
import com.google.gson.Gson
import group.asteriskint.adm.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductListViewModel : ViewModel() {
//        private val productListx = listOf(
//        Product(id = 1, name = "5YYGFF",price = 4534557.47, category_id = "Books"),
//        Product(id = 2,name = "HBDWEFWGER",price = 52567.47, category_id = "Books"),
//        Product(id = 3,name = "eGERGFSVSVu",price = 744567.47, category_id = "Books"),
//        Product(id = 4,name = "eFSGFGSFVFgu",price = 22454567.47, category_id = "Books"),
//        Product(id = 5,name = "eeGFFGFGVgu",price = 3344567.47, category_id = "Books"),
//        Product(id = 6,name = "GRFGERGSGegu",price = 554567.47, category_id = "Books"),
//        Product(id = 7,name = "THTHFHBgu",price = 9994567.47, category_id = "Books"),
//        Product(id = 8,name = "RRGGfuegu",price = 88867.47, category_id = "Books"),
//        Product(id = 9,name = "AERWQRDSFGuegu",price = 774567.47, category_id = "Books"),
//        Product(id = 10,name = "TT3TTT",price =3334567.47, category_id = "Books"),
//    )
    data class CloudProduct(
        val id : Int,
        val title : String,
        val price : String,
        val regularPrice : String,
        val related_ids : List<Int>,
        val categories : List<String>,
        val featured_src : String

    )
    val productList = MutableLiveData<List<Product>?>(null)
    data class ProductList(
        val products : List<CloudProduct>
    )
    fun fetchProductList(categoryId : String) {
        viewModelScope.launch{
            val list = withContext(Dispatchers.IO) {
                val json = Fuel.get("https://ec.adm.ug/wc-api/v2/products/?filter[categories]?&consumer_key=ck_57e29bf4c2aa93b765f35c608bb2bf03183030e6&consumer_secret=cs_9de94434632cd2865ac417ca49aba366160df9fa").responseString().second.data
                Log.d("FETCH ", String(json))
                try {
                    Gson().fromJson(String(json), ProductList::class.java).products
                }catch (e: Throwable) {
                    Log.e("productViewModel","error occurred",e)
                    null
                }
            }
            val filteredList = list?.filter { categoryId in it.categories}?.map {
                Product(
                    id = it.id,
                    name = it.title,
                    price = it.price.toDouble(),
                    category_id = categoryId,
                    image = it.featured_src
                )
            }
            productList.value = filteredList
//            productList.value = productListx
        }
    }
}