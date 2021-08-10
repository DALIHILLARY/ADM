package group.asteriskint.adm.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import group.asteriskint.adm.R

class ItemShowFragment : Fragment() {


    companion object {
        fun newInstance() = ItemShowFragment()
    }

    private lateinit var viewModel: ItemShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args :  ItemShowFragmentArgs by navArgs()
        val viewModel : ItemShowViewModel by viewModels()
        val view =  inflater.inflate(R.layout.item_show_fragment, container, false)
        val productId = args.productId
        val product = viewModel.fetchProduct(productId)
        val image : ImageView = view.findViewById(R.id.itemImage)
        val cartAmount : TextView = view.findViewById(R.id.cart_amount)
        val cartQuantity : TextView = view.findViewById(R.id.cart_quantity)
        val cartAdd :ImageView = view.findViewById(R.id.cart_add)
        val cartSubtract : ImageView = view.findViewById(R.id.cart_subtract)
        val addToCart : View = view.findViewById(R.id.add_to_cart)
        Glide.with(this.requireActivity().applicationContext).load(R.drawable.mask2).diskCacheStrategy(
                DiskCacheStrategy.ALL).skipMemoryCache(true).fitCenter().into(image)

        viewModel.quantity.observe(this, Observer {
            if(it == 1) {
                cartSubtract.visibility = View.GONE
            }else{
                cartSubtract.visibility = View.VISIBLE
            }
            cartQuantity.text = it.toString()
            cartAmount.text = (it * product.price).toString()
        })
        cartAdd.setOnClickListener {
            viewModel.add()
        }
        cartSubtract.setOnClickListener {
            viewModel.subtract()
        }
        addToCart.setOnClickListener {
            viewModel.addToCart(product)
            val action = ItemShowFragmentDirections.actionItemShowFragmentToCartFragment()
            view.findNavController().navigate(action)
        }

        return view
    }

}