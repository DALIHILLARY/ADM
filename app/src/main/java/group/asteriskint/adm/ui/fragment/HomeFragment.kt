package group.asteriskint.adm.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import group.asteriskint.adm.R
import group.asteriskint.adm.adapter.ProductCategoryAdapter

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_fragment, container, false)
        val viewModel: HomeViewModel by viewModels()
        val categoryAdapter = ProductCategoryAdapter(this.requireContext())
        val recyclerView: RecyclerView = view.findViewById(R.id.product_category_recycleView)
        recyclerView.apply{
            layoutManager = LinearLayoutManager(activity)
            adapter = categoryAdapter
        }
        viewModel.fetchCategories()
        viewModel.categoryList.observe(this,{
            it?.let {
                categoryAdapter.submitList(it)
            }
        })

        return view
    }


    override fun onDestroy() {
        super.onDestroy()
        Glide.get(this.requireContext().applicationContext).clearMemory()
    }

}