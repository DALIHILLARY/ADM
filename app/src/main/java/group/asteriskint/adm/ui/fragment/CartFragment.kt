package group.asteriskint.adm.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import group.asteriskint.adm.R
import group.asteriskint.adm.adapter.CartItemAdapter
import group.asteriskint.adm.viewmodel.UserViewModel

class CartFragment : Fragment() {

    companion object {
        fun newInstance() = CartFragment()
    }

    private lateinit var viewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel : CartViewModel by viewModels()
        val userViewModel : UserViewModel by activityViewModels()
        val view = inflater.inflate(R.layout.cart_fragment, container, false)
        val recycleView : RecyclerView = view.findViewById(R.id.cart_list_recycleView)
        val purchaseButton : Button = view.findViewById(R.id.purchase_button)
        val cartAdapter = CartItemAdapter(this.requireContext())
        recycleView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = cartAdapter
        }
        val userId = userViewModel.user.value?.id
        val cartItems = viewModel.fetchCart(userId,this.requireContext())
        cartAdapter.submitList(cartItems)
        purchaseButton.setOnClickListener {
            val action = CartFragmentDirections.actionCartFragmentToPaymentFragment()
            view.findNavController().navigate(action)
        }

        return view
    }

}