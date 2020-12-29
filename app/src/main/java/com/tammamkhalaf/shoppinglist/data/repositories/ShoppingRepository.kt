package com.tammamkhalaf.shoppinglist.data.repositories

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.tammamkhalaf.shoppinglist.data.db.ShoppingDatabase
import com.tammamkhalaf.shoppinglist.data.entities.ShoppingItem

class ShoppingRepository(private val db:ShoppingDatabase) {

    suspend fun upsert(item:ShoppingItem) = db.getShoppingDao().upsert(item)

    suspend fun delete(item: ShoppingItem) = db.getShoppingDao().delete(item)

    fun getAllShoppingItems(): LiveData<List<ShoppingItem>> = db.getShoppingDao().getAllShoppingItems()

}