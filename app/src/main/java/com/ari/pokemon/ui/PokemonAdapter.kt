package com.ari.pokemon.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ari.pokemon.databinding.ItemPokemonBinding
import com.ari.pokemon.model.pojos.SinglePokemon

class PokemonAdapter(
    private val listener: (element: SinglePokemon) -> Unit // listener for item click pokemon
): RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    private val list = ArrayList<SinglePokemon>();

    fun setList(newList: List<SinglePokemon>) {
        // Refresh list
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflate item view
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemPokemonBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(private val item: ItemPokemonBinding): RecyclerView.ViewHolder(item.root) {
        fun bind(singlePokemon: SinglePokemon) {
            item.pokemon = singlePokemon

            // On click pokemon element
            item.root.setOnClickListener { listener(singlePokemon) }
        }
    }

}