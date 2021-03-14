package com.example.core.ui.orderlist.suggestion

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.core.R
import com.example.core.constants.SUGGESTION
import com.example.core.data.suggestion.SuggestionItem

class OrderListSuggestionActivity : AppCompatActivity() {

    private val suggestion by lazy {
        intent.getParcelableArrayListExtra<SuggestionItem>(SUGGESTION)
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: OrderListSuggestionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orderlist_suggestion)

        initView()
        initAdapter()

        suggestion?.let {
            adapter.addItem(it)
        }
    }

    private fun initView() {
        recyclerView = findViewById(R.id.recycler_view)
    }

    private fun initAdapter() {
        adapter = OrderListSuggestionAdapter()
        recyclerView.adapter = adapter
    }
}