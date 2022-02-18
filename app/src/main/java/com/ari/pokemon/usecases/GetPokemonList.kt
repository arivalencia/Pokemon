package com.ari.pokemon.usecases

import com.ari.pokemon.data.model.PokemonListResponseData
import com.ari.pokemon.data.repository.PokemonRepository
import com.ari.pokemon.domain.model.PokemonListResponseDomain
import com.ari.pokemon.domain.model.ResultDomain
import com.ari.pokemon.domain.model.toDomain
import javax.inject.Inject

class GetPokemonList @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {

    suspend operator fun invoke(limit: Int = 500): ResultDomain<PokemonListResponseDomain> {
        return when(val result: ResultDomain<PokemonListResponseData> = pokemonRepository.getPokemonList(limit).toDomain()) {
            is ResultDomain.Loading -> ResultDomain.Loading()
            is ResultDomain.Error -> ResultDomain.Error(result.error)
            is ResultDomain.Success -> ResultDomain.Success(result.result.toDomain())
        }
    }

}