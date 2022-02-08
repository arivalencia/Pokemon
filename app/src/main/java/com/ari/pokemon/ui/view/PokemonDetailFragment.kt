package com.ari.pokemon.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ari.pokemon.BR
import com.ari.pokemon.R
import com.ari.pokemon.core.Toast
import com.ari.pokemon.data.model.Item
import com.ari.pokemon.databinding.FragmentPokemonDetailBinding
import com.ari.pokemon.ui.viewModel.PokemonViewModel
import com.ari.pokemon.ui.viewModel.Result
import com.novu.adapter.GAdapter
import dagger.hilt.android.AndroidEntryPoint
import com.ari.pokemon.data.model.SinglePokemon as SinglePokemon1

@AndroidEntryPoint
class PokemonDetailFragment: Fragment() {

    companion object {
        const val POKEMON_EXTRA = "POKEMON_EXTRA"
    }

    private lateinit var binding: FragmentPokemonDetailBinding

    private lateinit var viewModel: PokemonViewModel
    private lateinit var abilitiesAdapter: GAdapter<Item>
    private lateinit var typesAdapter: GAdapter<Item>
    private var singlePokemon: SinglePokemon1? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonDetailBinding.inflate(layoutInflater);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        setOnClickListeners()
        observePokemon()

        singlePokemon = arguments?.getParcelable(POKEMON_EXTRA)

        singlePokemon?.let {
            // Get pokemon data
            getPokemonByUrl(it.url)

        } ?: Toast.show(requireContext(), getString(R.string.no_data))

    }

    private fun observePokemon() {
        viewModel.pokemonObservable.observe(requireActivity()) { result ->
            when(result) {
                is Result.Loading -> { }
                is Result.Error -> Toast.show(requireContext(), result.error!!)
                is Result.Success -> {
                    val pokemon = result.result!!
                    binding.pokemon = pokemon
                    abilitiesAdapter.setList(pokemon.abilities.map { it.ability })
                    typesAdapter.setList(pokemon.types.map { it.type })
                }
            }
        }
    }

    private fun setOnClickListeners() {

        // On click button next
        binding.btnNext.setOnClickListener { nextPokemon() }

        // On click button previous
        binding.btnPrevious.setOnClickListener { previousPokemon() }

        binding.imgBack.setOnClickListener { requireActivity().onBackPressed() }
    }

    private fun getPokemonByUrl(url: String) = viewModel.getPokemonByUrl(url)

    private fun init() {
        // Init PokemonViewModel
        viewModel = ViewModelProvider(requireActivity()).get(PokemonViewModel::class.java)

        // Init adapters for lists
        abilitiesAdapter = GAdapter(R.layout.item_name, BR.item)
        binding.listAbilities.adapter = abilitiesAdapter

        typesAdapter = GAdapter(R.layout.item_name, BR.item)
        binding.listTypes.adapter = typesAdapter
    }

    private fun nextPokemon() {
        val nextPokemon = viewModel.nextPokemon(singlePokemon)
        nextPokemon?.let { singlePokemon = it }
    }

    private fun previousPokemon() {
        val previous = viewModel.previousPokemon(singlePokemon)
        previous?.let { singlePokemon = it }
    }

}