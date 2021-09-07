package group.asteriskint.adm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import group.asteriskint.adm.R
import group.asteriskint.adm.model.CartItem
import group.asteriskint.adm.repository.Repository
import kotlinx.coroutines.runBlocking

class CartItemAdapter(private val mContext: Context) : ListAdapter<CartItem, CartItemAdapter.ItemViewHolder>(DIFF_CALLBACK) {

    inner class ItemViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name : TextView = itemView.findViewById(R.id.cart_item_name)
        private val quantity : TextView = itemView.findViewById(R.id.cart_item_quantity)
        private val amount : TextView = itemView.findViewById(R.id.cart_item_amount)
        private val cartItemDelete : ImageView = itemView.findViewById(R.id.cart_item_cancel)

        fun bind(cartItem : CartItem) {
            name.text = cartItem.name
            quantity.text = cartItem.quantity.toString()
            amount.text = cartItem.amount.toString()
            cartItemDelete.setOnClickListener {
                runBlocking {
                    Repository(mContext).removeCartItem(cartItem)
                }
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val cartItemView = LayoutInflater.from(parent.context).inflate(R.layout.cart_item,parent,false)
        return ItemViewHolder(cartItemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CartItem>(){
            override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
                return oldItem.quantity == newItem.quantity && oldItem.amount == newItem.amount
            }
        }

    }
}