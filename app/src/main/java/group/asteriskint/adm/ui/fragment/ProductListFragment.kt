package group.asteriskint.adm.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import group.asteriskint.adm.R
import group.asteriskint.adm.adapter.ProductListAdapter

class ProductListFragment : Fragment() {

    companion object {
        fun newInstance() = ProductListFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args : ProductListFragmentArgs by navArgs()
        val viewModel : ProductListViewModel by viewModels()
        val categoryId = args.categoryId
        val view =  inflater.inflate(R.layout.product_list_fragment, container, false)
        val productListAdapter = ProductListAdapter(this.requireContext())
        val recycleView : RecyclerView= view.findViewById(R.id.product_list_recycleView)
        recycleView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = productListAdapter
        }
        val newProductList= viewModel.fetchProductList(categoryId)
        productListAdapter.submitList(newProductList)
        return view
    }


    override fun onDestroy() {
        super.onDestroy()
        Glide.get(this.requireContext().applicationContext).clearMemory()
    }
}