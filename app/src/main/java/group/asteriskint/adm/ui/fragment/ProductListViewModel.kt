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
    data class CloudProduct(
        val id : Int,
        val title : String,
        val price : String,
        val regularPrice : String,
        val related_ids : List<Int>,
        val categories : List<String>,
        val featured_src : String

    )
    val productList = MutableLiveData<List<Product>>(null)
    data class ProductList(
        val products : List<CloudProduct>
    )
    fun fetchProductList(categoryId : String) {
//        TODO("Do network call to fetch products here")
        viewModelScope.launch{
            val list = withContext(Dispatchers.IO) {
                val json = Fuel.get("https://ec.adm.ug/wc-api/v2/products/?filter[categories]?&consumer_key=ck_57e29bf4c2aa93b765f35c608bb2bf03183030e6&consumer_secret=cs_9de94434632cd2865ac417ca49aba366160df9fa").responseString().second.data
                Log.d("FETCH ", String(json))
                try {
                    Gson().fromJson(String(json), ProductList::class.java).products
                }catch (e: Throwable) {
                    Log.e("productViewModel","error occurred",e)
                    listOf<CloudProduct>()
                }
            }
            val filteredList = list.filter { categoryId in it.categories}.map {
                Product(
                    id = it.id,
                    name = it.title,
                    price = it.price.toDouble(),
                    category_id = categoryId,
                    image = it.featured_src
                )
            }
            productList.value = filteredList
        }
    }
}