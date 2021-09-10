package group.asteriskint.adm.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import group.asteriskint.adm.R
import group.asteriskint.adm.model.Product
import group.asteriskint.adm.ui.fragment.ProductListFragmentDirections

class ProductListAdapter(private val mContext: Context) : ListAdapter<Product,ProductListAdapter.ProductViewHolder>(DIFF_CALLBACK){
    inner class ProductViewHolder(private val productView: View) : RecyclerView.ViewHolder(productView) {
        private val image : ImageView = productView.findViewById(R.id.product_image)
        private val name : TextView = productView.findViewById(R.id.product_name)
        private val price: TextView = productView.findViewById(R.id.product_price)

        fun bind(product: Product) {
            Log.d("productAdapter","$product")
            Glide.with(mContext.applicationContext).load(product.image).diskCacheStrategy(DiskCacheStrategy.ALL).skipMemoryCache(true).fitCenter().into(image)
            name.text = product.name
            price.text = product.price.toString()
            productView.setOnClickListener {
                val action = ProductListFragmentDirections.actionProductListFragmentToItemShowFragment(productId = product.id, productName = product.name, productImage = product.image,productPrice = product.price.toFloat(),productCategory = product.category_id)
                productView.findNavController().navigate(action)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val productView = LayoutInflater.from(parent.context).inflate(R.layout.product,parent,false)
        return ProductViewHolder(productView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Product>(){
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem.image == newItem.image
            }
        }

    }

//    override fun getFilter(): Filter {
//        TODO("TO BE IMPLEMENTAED")
//        return object: Filter() {
//            override fun performFiltering(constraint: CharSequence?): FilterResults {
//                val charSearch = constraint.toString()
//                if(charSearch.isEmpty()){
//                    filteredProductList = currentList
//                }else{
//                    val resultSet = mutableSetOf<Product>()
//                    currentList.forEach {
//                        val name = it.name
//                        if(name.contains(charSearch)) {
//                            resultSet.add(it)
//                        }
//                    }
//                    filteredProductList = resultSet.toList()
//                }
//                val filterResults = FilterResults()
//                filterResults.values = filteredProductList
//                filterResults.count = filteredProductList.size
//                return filterResults
//            }
//
//            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
//                filteredProductList = results?.values as List<Product>
//                submitList(filteredProductList)
//            }
//
//        }
//    }
}