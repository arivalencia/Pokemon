package com.ari.pokemon.viewModel

import androidx.lifecycle.*
import com.ari.pokemon.model.pojos.Pokemon
import com.ari.pokemon.model.pojos.SinglePokemon
import com.ari.pokemon.model.repository.PokemonRepository
import kotlinx.coroutines.launch

class PokemonViewModel: ViewModel() {

    private val repository: PokemonRepository = PokemonRepository()

    private val pokemonListToShow = MutableLiveData<Result<List<SinglePokemon>>>() // Response to show
    val pokemonListToShowObservable: LiveData<Result<List<SinglePokemon>>> = pokemonListToShow
    private fun setListToShow(data: Result<List<SinglePokemon>>) = pokemonListToShow.postValue(data)

    private val originalPokemonList = arrayListOf<SinglePokemon>() // Response original
    private fun getOriginalList(): List<SinglePokemon> = originalPokemonList
    private fun setOriginalList(newList: List<SinglePokemon>) {
        originalPokemonList.clear()
        originalPokemonList.addAll(newList)
    }

    fun getPokemonList(limit: Int = 500) = viewModelScope.launch {
        when(val result = repository.getPokemonList(limit)) {
            is Result.Loading -> { }
            is Result.Error -> setListToShow(Result.Error(result.error!!))
            is Result.Success -> {
                val pokemonList = result.result!!
                setOriginalList(pokemonList.results)
                setListToShow(Result.Success(pokemonList.results))
            }
        }
    }

    private val pokemonResponse = MutableLiveData<Result<Pokemon>>()
    val pokemonObservable: LiveData<Result<Pokemon>> = pokemonResponse
    fun getPokemonByUrl(url: String) = viewModelScope.launch {
        pokemonResponse.postValue(Result.Loading())
        pokemonResponse.postValue(repository.getPokemonByUrl(url))
    }

    /**
     * Filter list by search
     */
    fun filterListBySearch(search: String) {
        val listFiltered = originalPokemonList.filter { it.name.contains(search.trim()) }
        setListToShow(Result.Success(listFiltered)) // Notify changes
    }

    fun nextPokemon(singlePokemon: SinglePokemon?): SinglePokemon? {
        if (singlePokemon == null) return null

        val index = getIndexOfSinglePokemon(singlePokemon)
        if (index != -1 && index < (getListSize() -1)) {
            val next = getOriginalList()[index+1]
            getPokemonByUrl(next.url)
            return next
        }

        return null
    }

    fun previousPokemon(singlePokemon: SinglePokemon?): SinglePokemon? {
        if (singlePokemon == null) return null

        val index = getIndexOfSinglePokemon(singlePokemon)
        if (index > 0) {
            val previous = getOriginalList()[index-1]
            getPokemonByUrl(previous.url)
            return previous
        }

        return null
    }

    private fun getIndexOfSinglePokemon(singlePokemon: SinglePokemon): Int = getOriginalList().indexOf(singlePokemon)

    private fun getListSize(): Int = getOriginalList().size

}