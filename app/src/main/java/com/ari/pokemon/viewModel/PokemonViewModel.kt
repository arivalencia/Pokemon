package com.ari.pokemon.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ari.pokemon.model.events.Request
import com.ari.pokemon.model.pojos.Pokemon
import com.ari.pokemon.model.pojos.PokemonListResponse
import com.ari.pokemon.model.pojos.SinglePokemon
import com.ari.pokemon.model.repository.PokemonRepository
import java.util.*

class PokemonViewModel: ViewModel() {

    private val repository: PokemonRepository = PokemonRepository()

    private val pokemonListToShow = MutableLiveData<List<SinglePokemon>>() // Response to show

    /**
     * START Get pokemon list
     */
    private val pokemonListResponse = MutableLiveData<PokemonListResponse>() // Response original
    private val pokemonListErrorResponse = MutableLiveData<String>()

    // Getters for view
    fun getPokemonListToShowObservable(): LiveData<List<SinglePokemon>> = pokemonListToShow
    fun getPokemonListErrorObservable(): LiveData<String> = pokemonListErrorResponse

    // Get pokemon list
    fun getPokemonList(limit: Int = 500) {
        repository.getPokemonList(limit, object: Request<PokemonListResponse>{
            override fun onSuccess(response: PokemonListResponse) {
                pokemonListResponse.value = response
                pokemonListToShow.value = response.results
            }

            override fun onFailure(error: String) {
                pokemonListErrorResponse.value = error
            }
        })
    }
    /**
     * END Get pokemon list
     */


    /**
     * START Get pokemon by id
     */
    private val pokemonResponse = MutableLiveData<Pokemon>()
    private val pokemonErrorResponse = MutableLiveData<String>()

    // Getters for view
    fun getPokemonObservable(): LiveData<Pokemon> = pokemonResponse
    fun getPokemonErrorObservable(): LiveData<String> = pokemonErrorResponse

    fun getPokemonByUrl(url: String) {
        repository.getPokemonByUrl(url, object: Request<Pokemon>{
            override fun onSuccess(response: Pokemon) {
                pokemonResponse.value = response
            }

            override fun onFailure(error: String) {
                pokemonErrorResponse.value = error
            }
        })
    }

    /**
     * START Get pokemon by id
     */


    /**
     * START Filter list by search
     */
    fun filterListBySearch(search: String) {
        if (pokemonListResponse.value?.results == null) return
        // Filter list
        val listFiltered = pokemonListResponse.value?.results?.filter { it.name.contains(search.trim()) }
        // Notify changes
        pokemonListToShow.value = listFiltered!!
    }
    /**
     * END Filter list by search
     */


    fun nextPokemon(singlePokemon: SinglePokemon?): SinglePokemon? {
        if (singlePokemon == null) return null

        val index = getIndexOfSinglePokemon(singlePokemon)
        if (index != -1 && index < (getListSize() -1)) {
            val next = getList()[index+1]
            getPokemonByUrl(next.url)
            return next
        }

        return null
    }

    fun previousPokemon(singlePokemon: SinglePokemon?): SinglePokemon? {
        if (singlePokemon == null) return null

        val index = getIndexOfSinglePokemon(singlePokemon)
        if (index != -1 && index > 0) {
            val previous = getList()[index-1]
            getPokemonByUrl(previous.url)
            return previous
        }

        return null
    }

    private fun getIndexOfSinglePokemon(singlePokemon: SinglePokemon): Int {
        if (pokemonListResponse.value == null) return -1
        if (pokemonListResponse.value!!.results == null) return -1
        return pokemonListResponse.value!!.results!!.indexOf(singlePokemon)
    }

    private fun getListSize(): Int {
        if (pokemonListResponse.value == null) return 0
        if (pokemonListResponse.value!!.results == null) return 0
        return pokemonListResponse.value!!.results!!.size
    }

    private fun getList(): List<SinglePokemon> {
        if (pokemonListResponse.value == null) return listOf()
        if (pokemonListResponse.value!!.results == null) return listOf()
        return pokemonListResponse.value!!.results!!
    }
}