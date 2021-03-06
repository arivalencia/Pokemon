package com.ari.pokemon.presentation.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ari.pokemon.BR
import com.ari.pokemon.R
import com.ari.pokemon.core.Toaster
import com.ari.pokemon.databinding.FragmentPokemonListBinding
import com.ari.pokemon.domain.model.ResultDomain
import com.ari.pokemon.domain.model.SinglePokemonDomain
import com.ari.pokemon.presentation.viewModel.PokemonViewModel
import com.novu.adapter.GAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonListFragment: Fragment() {

    private var _binding: FragmentPokemonListBinding? = null
    private val binding: FragmentPokemonListBinding get() = _binding!!

    private lateinit var viewModel: PokemonViewModel
    private lateinit var pokemonListAdapter: GAdapter<SinglePokemonDomain>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Init layout
        _binding = FragmentPokemonListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        setTextChangeListener()

        // Observe results
        observePokemonList()

        // Get data
        viewModel.getPokemonList()

    }

    private fun setTextChangeListener() {

        // On write in input_search
        binding.inputSearch.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                viewModel.filterListBySearch(p0.toString())
            }
        })
    }

    private fun observePokemonList() {
        viewModel.pokemonListToShowObservable.observe(requireActivity()) { result ->
            when(result) {
                is ResultDomain.Loading -> { }
                is ResultDomain.Error -> Toaster.show(requireContext(), result.error!!)
                is ResultDomain.Success -> pokemonListAdapter.setList(result.result)
            }
        }
    }

    private fun init() {

        // Init PokemonListViewModel
        viewModel = ViewModelProvider(requireActivity()).get(PokemonViewModel::class.java)

        // Init PokemonAdapter
        pokemonListAdapter = GAdapter(R.layout.item_pokemon, BR.pokemon, object: GAdapter.BaseListener<SinglePokemonDomain>{
            override fun onClickItem(singlePokemon: SinglePokemonDomain, position: Int) {
                val bundle = bundleOf(PokemonDetailFragment.POKEMON_EXTRA to singlePokemon)
                Navigation.findNavController(binding.root).navigate(R.id.pokemonDetailFragment, bundle)
            }
        })
        // Set adapter to recyclerView
        binding.pokemonList.adapter = pokemonListAdapter

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}