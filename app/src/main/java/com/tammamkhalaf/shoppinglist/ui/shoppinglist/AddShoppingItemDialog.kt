package com.tammamkhalaf.shoppinglist.ui.shoppinglist

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import androidx.fragment.app.DialogFragment
import com.tammamkhalaf.shoppinglist.R
import com.tammamkhalaf.shoppinglist.data.entities.ShoppingItem
import kotlinx.android.synthetic.main.dialog_add_shopping_item.*

private const val TAG = "AddShoppingItemDialog"

class AddShoppingItemDialog(context: Context,var addDialogListener: AddDialogListener):AppCompatDialog(context){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_shopping_item)

        tvAdd.setOnClickListener{
            val name = etName.text.toString()
            val amount = etAmount.text.toString()

            if(name.isEmpty() || amount.isEmpty()){
                Toast.makeText(context, "Please Enter All Information!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val item = ShoppingItem(name,amount.toInt())

            addDialogListener.onAddButtonClick(item)
            dismiss()
        }

        tvCancel.setOnClickListener{
            cancel()
        }

    }
}