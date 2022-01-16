package com.example.base

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T>(
    items: List<T>?,
    emptyView: View?,
    clickListener: BaseRecyclerAdapterClickListener<T>? = null
) : RecyclerView.Adapter<BaseRecyclerAdapter.BaseViewHolder<T>>() {

    private val _items: MutableList<T>? = items?.toMutableList()
    private var _clickListener: BaseRecyclerAdapterClickListener<T>? = clickListener
    private var _emptyView: View? = emptyView

    fun setClickListener (clickListener: BaseRecyclerAdapterClickListener<T>?) {
        _clickListener = clickListener
    }

    fun setEmptyView(view: View) {
        _emptyView = view
    }

    interface BaseRecyclerAdapterClickListener<T> {
        fun onViewClicked(item: T, position: Int, actionId: Int? = 0)
    }

    abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        protected var item: T? = null
        protected lateinit var context: Context

        @CallSuper
        open fun bind(type: T) {
            item = type
            context = itemView.context
        }
    }

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T>

    @CallSuper
    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bind(_items?.get(position)!!)
        holder.itemView.setOnClickListener {
            _clickListener?.onViewClicked(_items[position], position)
        }
    }

    fun addItems(items: List<T>) {
        _items?.addAll(items)
        notifyItemRangeInserted(0, items.size)
        updateEmptyViewVisibility()
    }

    private fun updateEmptyViewVisibility() {
        if (itemCount == 0) _emptyView?.visibility = View.VISIBLE
        else _emptyView?.visibility = View.GONE
    }

    fun clearItems() {
        val count = itemCount
        _items?.clear()
        notifyItemRangeRemoved(0, count)
        updateEmptyViewVisibility()
    }

    override fun getItemCount() = _items?.size ?: 0
}