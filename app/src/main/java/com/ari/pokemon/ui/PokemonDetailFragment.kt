package com.ari.pokemon.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ari.pokemon.R
import com.ari.pokemon.databinding.FragmentPokemonDetailBinding
import com.ari.pokemon.viewModel.PokemonViewModel
import com.ari.pokemon.model.pojos.SinglePokemon as SinglePokemon1

class PokemonDetailFragment: Fragment() {

    private val TAG = PokemonDetailFragment::class.java.simpleName
    private lateinit var binding: FragmentPokemonDetailBinding
    private lateinit var viewModel: PokemonViewModel
    private var singlePokemon: SinglePokemon1? = null
    private lateinit var abilitiesAdapter: NameAdapter
    private lateinit var typesAdapter: NameAdapter

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
        setOnClickListeners();
        observePokemon()

        singlePokemon = arguments?.getParcelable("pokemon")

        if (singlePokemon == null) {
            // No data
            showToast(getString(R.string.no_data))
        } else {
            // Get pokemon data
            viewModel.getPokemonByUrl(singlePokemon!!.url)
        }

    }

    private fun setOnClickListeners() {

        // On click button next
        binding.btnNext.setOnClickListener { nextPokemon() }

        // On click button previous
        binding.btnPrevious.setOnClickListener { previousPokemon() }

        binding.imgBack.setOnClickListener { Navigation.findNavController(binding.root).navigate(R.id.pokemonListFragment) }
    }

    private fun observePokemon() {
        // On SUCCESS request
        viewModel.getPokemonObservable().observe(requireActivity()){ pokemon ->
            Log.e(TAG, pokemon.toString())
            binding.pokemon = pokemon
            abilitiesAdapter.setList(pokemon.abilities.map { it.ability })
            typesAdapter.setList(pokemon.types.map { it.type })
        }

        // On FAILURE request
        viewModel.getPokemonErrorObservable().observe(requireActivity()){ error ->
            showToast(error)
        }
    }

    private fun init() {
        // Init PokemonViewModel
        viewModel = ViewModelProvider(requireActivity()).get(PokemonViewModel::class.java)

        // Init adapters for lists
        abilitiesAdapter = NameAdapter()
        binding.listAbilities.adapter = abilitiesAdapter

        typesAdapter = NameAdapter()
        binding.listTypes.adapter = typesAdapter
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun nextPokemon() {
        val currentPokemon = viewModel.nextPokemon(singlePokemon)
        if (currentPokemon != null) {
            singlePokemon = currentPokemon
        }
    }

    private fun previousPokemon() {
        val currentPokemon = viewModel.previousPokemon(singlePokemon)
        if (currentPokemon != null) {
            singlePokemon = currentPokemon
        }
    }

}