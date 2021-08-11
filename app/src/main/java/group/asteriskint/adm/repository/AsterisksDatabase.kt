package group.asteriskint.adm.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import group.asteriskint.adm.model.CartItem
import group.asteriskint.adm.model.User

@Database(entities = [User::class, CartItem::class], version = 1, exportSchema = false)
abstract class AsterisksDatabase : RoomDatabase() {
    abstract fun databaseDao() : DatabaseDao

    companion object {
        private var instance : AsterisksDatabase? = null
        @Synchronized fun dbinstance(context: Context?) : AsterisksDatabase {
            if(instance == null) {
                instance = Room.databaseBuilder(context!!.applicationContext, AsterisksDatabase::class.java,"asterisk.db")
                    .build()
            }
            return instance as AsterisksDatabase
        }
    }
}