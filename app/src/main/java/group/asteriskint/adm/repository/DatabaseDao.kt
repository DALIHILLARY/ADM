package group.asteriskint.adm.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import group.asteriskint.adm.model.CartItem
import group.asteriskint.adm.model.User

@Dao
interface DatabaseDao {
    @Insert(entity = User::class, onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user : User)

    @Insert(entity = CartItem::class, onConflict = OnConflictStrategy.REPLACE)
    fun insertCartItem(item : CartItem)

    @Query("SELECT * FROM user")
    fun getuser() : User?

    @Query("SELECT * FROM user")
    fun getLiveUser() : LiveData<User?>

    @Delete(entity = User::class)
    fun deleteUser(user: User)

    @Delete(entity = CartItem::class)
    fun deleteCartItem(item : CartItem)

    @Query("SELECT * FROM cartitem")
    fun getCartItems() : List<CartItem>

    @Query("DELETE FROM cartitem")
    fun clearCart()
}