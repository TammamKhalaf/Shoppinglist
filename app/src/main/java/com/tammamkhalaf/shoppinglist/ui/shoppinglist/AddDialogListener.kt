package com.tammamkhalaf.shoppinglist.ui.shoppinglist

import com.tammamkhalaf.shoppinglist.data.entities.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClick(item:ShoppingItem)
}