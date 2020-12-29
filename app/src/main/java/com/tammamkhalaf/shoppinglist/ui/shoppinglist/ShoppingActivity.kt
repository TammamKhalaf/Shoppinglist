package com.tammamkhalaf.shoppinglist.ui.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.tammamkhalaf.shoppinglist.R
import com.tammamkhalaf.shoppinglist.data.db.ShoppingDatabase
import com.tammamkhalaf.shoppinglist.data.entities.ShoppingItem
import com.tammamkhalaf.shoppinglist.data.repositories.ShoppingRepository
import com.tammamkhalaf.shoppinglist.other.ShoppingItemAdapter
import kotlinx.android.synthetic.main.activity_shopping.*

class ShoppingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val database = ShoppingDatabase(this)

        val repository = ShoppingRepository(database)

        val factory = ShoppingViewModelFactory(repository)

        val viewModel=ViewModelProviders.of(this,factory).get(ShoppingViewModel::class.java)

        val adapter=ShoppingItemAdapter(listOf(),viewModel)

        rvShoppingItems.layoutManager = LinearLayoutManager(this)
        rvShoppingItems.adapter = adapter

        val dividerItemDecoration =  DividerItemDecoration(rvShoppingItems.getContext(),
            (rvShoppingItems.layoutManager as LinearLayoutManager).getOrientation());

        rvShoppingItems.addItemDecoration(dividerItemDecoration);

        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        fab.setOnClickListener {
            AddShoppingItemDialog(this,object:AddDialogListener{
                override fun onAddButtonClick(item: ShoppingItem) {
                        viewModel.upsert(item)
                }

            }).show()
        }
    }
}