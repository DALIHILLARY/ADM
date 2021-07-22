package group.asteriskint.adm.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val name : String = "",
    val price : Double = 0.0,
    val imagePath: String = ""
    )