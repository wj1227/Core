package com.example.core.data.orderlist

import com.example.core.data.order.Order
import com.example.core.data.selfcall.SelfCallItem
import com.example.core.data.suggestion.SuggestionItem

data class OrderList(
    val order: List<Order>? = null,
    val suggestion: List<SuggestionItem>? = null,
    val selfCall: List<SelfCallItem>? = null
)