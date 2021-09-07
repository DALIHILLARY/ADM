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
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: HomeViewModel by viewModels()
        category_shimmer.startShimmer()
        viewModel.fetchCategories()
        viewModel.categoryList.observe(viewLifecycleOwner) {
            it?.let {
                category_shimmer.stopShimmer()
                category_shimmer.visibility = View.GONE
                product_category_recycleView.visibility = View.VISIBLE
                val categoryAdapter = ProductCategoryAdapter(this.requireContext())
                val recyclerView: RecyclerView = view.findViewById(R.id.product_category_recycleView)
                recyclerView.apply{
                    layoutManager = LinearLayoutManager(activity)
                    adapter = categoryAdapter
                }
                categoryAdapter.submitList(it)
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        Glide.get(this.requireContext().applicationContext).clearMemory()
    }

}