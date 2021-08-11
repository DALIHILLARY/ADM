package group.asteriskint.adm.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CartItem (
    @PrimaryKey val id : Int,
    val name : String,
    val quantity : Int,
    val amount : Double
        )