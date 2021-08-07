package group.asteriskint.adm.adapter

import android.content.Context
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
import group.asteriskint.adm.model.Category
import group.asteriskint.adm.ui.fragment.HomeFragmentDirections

class ProductCategoryAdapter(private val mContext: Context) : ListAdapter<Category, ProductCategoryAdapter.CategoryViewHolder>(DIFF_CALLBACK) {

    inner class CategoryViewHolder(private val categoryView : View) : RecyclerView.ViewHolder(categoryView) {
        private val image : ImageView = categoryView.findViewById(R.id.product_category_image)
        private val name : TextView = categoryView.findViewById(R.id.product_category_name)

        fun bind(category: Category) {
            Glide.with(mContext.applicationContext).load(R.drawable.mask2).diskCacheStrategy(
                DiskCacheStrategy.ALL).skipMemoryCache(true).fitCenter().into(image)
            name.text = category.name

            categoryView.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToProductListFragment(category.id)
                categoryView.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val categoryView = LayoutInflater.from(parent.context).inflate(R.layout.product_category,parent,false)
        return CategoryViewHolder(categoryView)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Category>(){
            override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem.imageUrl == newItem.imageUrl
            }
        }

    }
}