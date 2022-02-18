package com.ari.pokemon.usecases

import com.ari.pokemon.data.model.PokemonData
import com.ari.pokemon.data.model.ResultData
import com.ari.pokemon.data.repository.PokemonRepository
import com.ari.pokemon.domain.model.PokemonDomain
import com.ari.pokemon.domain.model.ResultDomain
import com.ari.pokemon.domain.model.toDomain
import javax.inject.Inject

class GetPokemonByUrl @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {

    suspend operator fun invoke(url: String): ResultDomain<PokemonDomain> {
        return when (val result: ResultDomain<PokemonData> = pokemonRepository.getPokemonByUrl(url).toDomain()) {
            is ResultDomain.Error -> ResultDomain.Error(result.error)
            is ResultDomain.Loading -> ResultDomain.Loading()
            is ResultDomain.Success -> ResultDomain.Success(result.result.toDomain())
        }
    }

}