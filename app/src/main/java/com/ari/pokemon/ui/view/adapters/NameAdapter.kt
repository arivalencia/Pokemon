package com.ari.pokemon.ui.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ari.pokemon.databinding.ItemNameBinding
import com.ari.pokemon.data.model.Item

class NameAdapter: RecyclerView.Adapter<NameAdapter.ViewHolder>() {

    private val list = ArrayList<Item>()

    fun setList(newList: List<Item>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemNameBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(private val item: ItemNameBinding): RecyclerView.ViewHolder(item.root) {
        fun bind(obj: Item){ item.item = obj }
    }

}