package group.asteriskint.adm.ui.fragment

import androidx.lifecycle.ViewModel
import group.asteriskint.adm.model.Product

class ProductListViewModel : ViewModel() {
    private val productList = listOf(
        Product(id = 1, name = "5YYGFF",price = 4534557.47, category_id = 4),
        Product(id = 2,name = "HBDWEFWGER",price = 52567.47, category_id = 4),
        Product(id = 3,name = "eGERGFSVSVu",price = 744567.47, category_id = 1),
        Product(id = 4,name = "eFSGFGSFVFgu",price = 22454567.47, category_id = 1),
        Product(id = 5,name = "eeGFFGFGVgu",price = 3344567.47, category_id = 2),
        Product(id = 6,name = "GRFGERGSGegu",price = 554567.47, category_id = 2),
        Product(id = 7,name = "THTHFHBgu",price = 9994567.47, category_id = 3),
        Product(id = 8,name = "RRGGfuegu",price = 88867.47, category_id = 3),
        Product(id = 9,name = "AERWQRDSFGuegu",price = 774567.47, category_id = 5),
        Product(id = 10,name = "TT3TTT",price =3334567.47, category_id = 5),
    )


    fun fetchProductList(categoryId : Int) : List<Product> {
//        TODO("Do network call to fetch products here")
        return productList.filter { it.category_id == categoryId }
    }
}