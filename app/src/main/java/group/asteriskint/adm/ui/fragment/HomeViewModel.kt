package group.asteriskint.adm.ui.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.kittinunf.fuel.Fuel
import com.google.gson.Gson
import group.asteriskint.adm.model.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {
    val categoryList = MutableLiveData<List<Category>>(null)
    data class CategoryList(
        val product_categories : List<Category>
    )
    fun fetchCategories()  {
        viewModelScope.launch{
            val list = withContext(Dispatchers.IO) {
                val json = Fuel.get("https://interns1.adm.ug/wc-api/v2/products/categories?&consumer_key=ck_11eabcc20c694cddd02a3fda1b9315d1159869bb&consumer_secret=cs_baae5205f3c7e8900f4d545fb58c001d73683d1a").responseString().second.data
                Log.d("FETCH ", String(json))
                Gson().fromJson(String(json),CategoryList::class.java).product_categories
            }
            categoryList.value = list
        }

    }
}