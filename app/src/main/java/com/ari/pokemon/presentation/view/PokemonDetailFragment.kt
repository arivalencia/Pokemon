package com.ari.pokemon.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ari.pokemon.BR
import com.ari.pokemon.R
import com.ari.pokemon.core.Toaster
import com.ari.pokemon.databinding.FragmentPokemonDetailBinding
import com.ari.pokemon.domain.model.ItemDomain
import com.ari.pokemon.domain.model.ResultDomain
import com.ari.pokemon.domain.model.SinglePokemonDomain
import com.ari.pokemon.presentation.viewModel.PokemonViewModel
import com.novu.adapter.GAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailFragment: Fragment() {

    companion object {
        const val POKEMON_EXTRA = "POKEMON_EXTRA"
    }

    private lateinit var binding: FragmentPokemonDetailBinding

    private lateinit var viewModel: PokemonViewModel
    private lateinit var abilitiesAdapter: GAdapter<ItemDomain>
    private lateinit var typesAdapter: GAdapter<ItemDomain>
    private var singlePokemon: SinglePokemonDomain? = null

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

        } ?: Toaster.show(requireContext(), getString(R.string.no_data))

    }

    private fun observePokemon() {
        viewModel.pokemonObservable.observe(requireActivity()) { result ->
            when(result) {
                is ResultDomain.Loading -> { }
                is ResultDomain.Error -> Toaster.show(requireContext(), result.error!!)
                is ResultDomain.Success -> {
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