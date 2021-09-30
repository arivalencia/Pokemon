package com.ari.pokemon.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ari.pokemon.R
import com.ari.pokemon.databinding.FragmentPokemonListBinding
import com.ari.pokemon.viewModel.PokemonViewModel

class PokemonListFragment: Fragment() {

    private lateinit var binding: FragmentPokemonListBinding
    private lateinit var viewModel: PokemonViewModel
    private lateinit var pokemonListAdapter: PokemonAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Init layout
        binding = FragmentPokemonListBinding.inflate(layoutInflater)
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
        // On SUCCESS request
        viewModel.getPokemonListToShowObservable().observe(requireActivity()){ pokemonList ->
            pokemonListAdapter.setList(pokemonList)
        }

        // On FAILURE request
        viewModel.getPokemonListErrorObservable().observe(requireActivity()){ error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        }
    }

    private fun init() {

        // Init PokemonListViewModel
        viewModel = ViewModelProvider(requireActivity()).get(PokemonViewModel::class.java)

        // Init PokemonAdapter
        pokemonListAdapter = PokemonAdapter { singlePokemon ->
            val bundle = bundleOf("pokemon" to singlePokemon)
            Navigation.findNavController(binding.root).navigate(R.id.pokemonDetailFragment, bundle)
        }
        // Set adapter to recyclerView
        binding.pokemonList.adapter = pokemonListAdapter

    }
}