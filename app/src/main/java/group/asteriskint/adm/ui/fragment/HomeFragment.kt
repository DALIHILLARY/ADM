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
import group.asteriskint.adm.model.Category

class HomeFragment : Fragment() {
    private val categoryList = listOf(
        Category(name = "BOOKS",id = 2),
        Category(name = "BOOKS",id = 1),
        Category(name = "B2435OOKS2",id = 3),
        Category(name = "BOOK4534S",id = 4),
        Category(name = "BO454OKS",id = 5),
        Category(name = "BOO43KS",id = 26),
        Category(name = "BO443OKS",id = 27),
        Category(name = "BOO44KS",id = 28),
    )
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
        categoryAdapter.submitList(categoryList)
        return view
    }


    override fun onDestroy() {
        super.onDestroy()
        Glide.get(this.requireContext().applicationContext).clearMemory()
    }

}