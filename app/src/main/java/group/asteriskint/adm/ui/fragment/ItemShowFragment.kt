package group.asteriskint.adm.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import group.asteriskint.adm.R
import group.asteriskint.adm.model.Product
import kotlinx.android.synthetic.main.item_show_fragment.*

class ItemShowFragment : Fragment() {

    private lateinit var viewModel: ItemShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_show_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args :  ItemShowFragmentArgs by navArgs()
        val viewModel : ItemShowViewModel by viewModels()

        val productId = args.productId
        val productName = args.productName
        val productImage = args.productImage
        val productPrice = args.productPrice.toDouble()
        val productCategory = args.productCategory

//        val product = viewModel.fetchProduct(productId)
        val product = Product(productId,productName,productPrice,productImage,productCategory)

        item_name.text = productName
        item_price.text = productPrice.toString()
        Glide.with(this.requireActivity().applicationContext).load(productId).diskCacheStrategy(
            DiskCacheStrategy.ALL).skipMemoryCache(true).fitCenter().into(itemImage)

        viewModel.quantity.observe(this, Observer {
            if(it == 1) {
                cart_subtract.visibility = View.GONE
            }else{
                cart_subtract.visibility = View.VISIBLE
            }
            cart_quantity.text = it.toString()
            cart_amount.text = (it * product.price).toString()
        })
        cart_add.setOnClickListener {
            viewModel.add()
        }
        cart_subtract.setOnClickListener {
            viewModel.subtract()
        }
        add_to_cart.setOnClickListener {
            viewModel.addToCart(product,this.requireContext())
            view.findNavController().navigate(R.id.homeFragment)
//            val action = ItemShowFragmentDirections.actionItemShowFragmentToCartFragment()
//            view.findNavController().navigate(action)
        }
    }

}