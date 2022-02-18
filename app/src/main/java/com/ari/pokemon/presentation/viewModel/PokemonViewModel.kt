package com.ari.pokemon.presentation.viewModel

import androidx.lifecycle.*
import com.ari.pokemon.usecases.GetPokemonByUrl
import com.ari.pokemon.usecases.GetPokemonList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.ari.pokemon.domain.model.PokemonDomain
import com.ari.pokemon.domain.model.ResultDomain
import com.ari.pokemon.domain.model.SinglePokemonDomain

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val getPokemonList: GetPokemonList,
    private val getPokemonByUrl: GetPokemonByUrl
): ViewModel() {

    private val pokemonListToShow = MutableLiveData<ResultDomain<List<SinglePokemonDomain>>>() // Response to show
    val pokemonListToShowObservable: LiveData<ResultDomain<List<SinglePokemonDomain>>> = pokemonListToShow
    private fun setListToShow(data: ResultDomain<List<SinglePokemonDomain>>) = pokemonListToShow.postValue(data)

    private val originalPokemonList = arrayListOf<SinglePokemonDomain>() // Response original
    private fun getOriginalList(): List<SinglePokemonDomain> = originalPokemonList
    private fun setOriginalList(newList: List<SinglePokemonDomain>) {
        originalPokemonList.clear()
        originalPokemonList.addAll(newList)
    }

    fun getPokemonList(limit: Int = 500) = viewModelScope.launch {
        when(val result = getPokemonList.invoke()) {
            is ResultDomain.Error -> setListToShow(ResultDomain.Error(result.error!!))
            is ResultDomain.Success -> {
                val pokemonList = result.result!!
                setOriginalList(pokemonList.results)
                setListToShow(ResultDomain.Success(pokemonList.results))
            }
        }
    }

    private val pokemonResponse = MutableLiveData<ResultDomain<PokemonDomain>>()
    val pokemonObservable: LiveData<ResultDomain<PokemonDomain>> = pokemonResponse
    fun getPokemonByUrl(url: String) = viewModelScope.launch {
        pokemonResponse.postValue(ResultDomain.Loading())
        pokemonResponse.postValue(getPokemonByUrl.invoke(url))
    }

    /**
     * Filter list by search
     */
    fun filterListBySearch(search: String) {
        val listFiltered = originalPokemonList.filter { it.name.contains(search.trim()) }
        setListToShow(ResultDomain.Success(listFiltered)) // Notify changes
    }

    fun nextPokemon(singlePokemon: SinglePokemonDomain?): SinglePokemonDomain? {
        if (singlePokemon == null) return null

        val index = getIndexOfSinglePokemon(singlePokemon)
        if (index != -1 && index < (getListSize() -1)) {
            val next = getOriginalList()[index+1]
            getPokemonByUrl(next.url)
            return next
        }

        return null
    }

    fun previousPokemon(singlePokemon: SinglePokemonDomain?): SinglePokemonDomain? {
        if (singlePokemon == null) return null

        val index = getIndexOfSinglePokemon(singlePokemon)
        if (index > 0) {
            val previous = getOriginalList()[index-1]
            getPokemonByUrl(previous.url)
            return previous
        }

        return null
    }

    private fun getIndexOfSinglePokemon(singlePokemon: SinglePokemonDomain): Int = getOriginalList().indexOf(singlePokemon)

    private fun getListSize(): Int = getOriginalList().size

}