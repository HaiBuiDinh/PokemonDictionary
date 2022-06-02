package com.example.mypokemon.data.model


import com.google.gson.annotations.SerializedName

data class PokemonResultEntity(
    @SerializedName("count")
    val count: Int?, // 1126
    @SerializedName("next")
    val next: String?, // https://pokeapi.co/api/v2/pokemon?offset=1000&limit=126
    @SerializedName("previous")
    val previous: Any?, // null
    @SerializedName("results")
    val results: List<Result>
) {
    data class Result(
        @SerializedName("name")
        val name: String, // bulbasaur
        @SerializedName("url")
        val url: String // https://pokeapi.co/api/v2/pokemon/1/
    )
}