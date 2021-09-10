package group.asteriskint.adm.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import group.asteriskint.adm.R
import group.asteriskint.adm.adapter.ProductListAdapter
import group.asteriskint.adm.viewmodel.HomeActivityViewModel
import kotlinx.android.synthetic.main.product_list_fragment.*

class ProductListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.product_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var productQuery = ""
        val args : ProductListFragmentArgs by navArgs()
        val viewModel : ProductListViewModel by viewModels()
        val activityViewModel : HomeActivityViewModel by activityViewModels()
        val categoryName = args.categoryName
        product_list_shimmer.startShimmer()
        viewModel.fetchProductList(categoryName)
        viewModel.productList.observe(viewLifecycleOwner) {
            it?.let {
                product_list_shimmer.stopShimmer()
                product_list_shimmer.visibility = View.GONE
                product_list_recycleView.visibility = View.VISIBLE
                val productListAdapter = ProductListAdapter(this.requireContext())
                val recycleView : RecyclerView = view.findViewById(R.id.product_list_recycleView)

                recycleView.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = productListAdapter
                }
//                if(productQuery.isNotEmpty()) {
//                    productListAdapter.filter.filter(productQuery)
//                }else{

                productListAdapter.submitList(it)
                Log.d("productListFragment","list $it")
//                }
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        Glide.get(this.requireContext().applicationContext).clearMemory()
    }
}