package com.example.tekotest.data.database.daos

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tekotest.data.database.daos.product.ProductDao
import com.example.tekotest.data.database.daos.product.ProductUpdateDao
import com.example.tekotest.data.models.Product
import com.example.tekotest.data.models.ProductUpdate

@Database(entities = [Product::class,ProductUpdate::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun productUpdateDao():ProductUpdateDao

    companion object {

        private var INSTANT: AppDatabase? = null

        @Synchronized
        @JvmStatic
        fun getInstant(context: Context): AppDatabase {
            if (INSTANT == null) {
                INSTANT = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "test.db"
                ).allowMainThreadQueries().build()
            }

            return INSTANT!!
        }

    }
}