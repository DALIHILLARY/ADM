package group.asteriskint.adm.model

import androidx.room.Entity

@Entity
data class CartItem (
    val id : Int,
    val name : String,
    val quantity : Int,
    val amount : Double
        )