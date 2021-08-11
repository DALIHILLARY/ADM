package group.asteriskint.adm.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey
    val id : Int = 1,
    val name : String = "",
    val email: String = "",
    val phoneNumber : String = ""
        )