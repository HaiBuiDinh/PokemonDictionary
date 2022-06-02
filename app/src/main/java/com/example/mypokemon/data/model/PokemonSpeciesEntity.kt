package com.example.mypokemon.data.model


import com.google.gson.annotations.SerializedName

data class PokemonSpeciesEntity(
    @SerializedName("base_happiness")
    val baseHappiness: Int?, // 50
    @SerializedName("capture_rate")
    val captureRate: Int?, // 45
    @SerializedName("color")
    val color: Color?,
    @SerializedName("egg_groups")
    val eggGroups: List<EggGroup?>?,
    @SerializedName("evolution_chain")
    val evolutionChain: EvolutionChain?,
    @SerializedName("evolves_from_species")
    val evolvesFromSpecies: EvolvesFromSpecies?,
    @SerializedName("flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntry?>?,
    @SerializedName("form_descriptions")
    val formDescriptions: List<FormDescription?>?,
    @SerializedName("forms_switchable")
    val formsSwitchable: Boolean?, // true
    @SerializedName("gender_rate")
    val genderRate: Int?, // 4
    @SerializedName("genera")
    val genera: List<Genera?>?,
    @SerializedName("generation")
    val generation: Generation?,
    @SerializedName("growth_rate")
    val growthRate: GrowthRate?,
    @SerializedName("habitat")
    val habitat: Any?, // null
    @SerializedName("has_gender_differences")
    val hasGenderDifferences: Boolean?, // false
    @SerializedName("hatch_counter")
    val hatchCounter: Int?, // 20
    @SerializedName("id")
    val id: Int?, // 681
    @SerializedName("is_baby")
    val isBaby: Boolean?, // false
    @SerializedName("is_legendary")
    val isLegendary: Boolean?, // false
    @SerializedName("is_mythical")
    val isMythical: Boolean?, // false
    @SerializedName("name")
    val name: String?, // aegislash
    @SerializedName("names")
    val names: List<Name?>?,
    @SerializedName("order")
    val order: Int?, // 682
    @SerializedName("pal_park_encounters")
    val palParkEncounters: List<Any?>?,
    @SerializedName("pokedex_numbers")
    val pokedexNumbers: List<PokedexNumber?>?,
    @SerializedName("shape")
    val shape: Shape?,
    @SerializedName("varieties")
    val varieties: List<Variety?>?
) {
    data class Color(
        @SerializedName("name")
        val name: String?, // brown
        @SerializedName("url")
        val url: String? // https://pokeapi.co/api/v2/pokemon-color/3/
    )

    data class EggGroup(
        @SerializedName("name")
        val name: String?, // mineral
        @SerializedName("url")
        val url: String? // https://pokeapi.co/api/v2/egg-group/10/
    )

    data class EvolutionChain(
        @SerializedName("url")
        val url: String? // https://pokeapi.co/api/v2/evolution-chain/349/
    )

    data class EvolvesFromSpecies(
        @SerializedName("name")
        val name: String?, // doublade
        @SerializedName("url")
        val url: String? // https://pokeapi.co/api/v2/pokemon-species/680/
    )

    data class FlavorTextEntry(
        @SerializedName("flavor_text")
        val flavorText: String?, // れきだいの　おうが　つれていた。れいりょくで　ひとや　ポケモンのこころを　あやつり　したがわせる。
        @SerializedName("language")
        val language: Language?,
        @SerializedName("version")
        val version: Version?
    ) {
        data class Language(
            @SerializedName("name")
            val name: String?, // ja-Hrkt
            @SerializedName("url")
            val url: String? // https://pokeapi.co/api/v2/language/1/
        )

        data class Version(
            @SerializedName("name")
            val name: String?, // x
            @SerializedName("url")
            val url: String? // https://pokeapi.co/api/v2/version/23/
        )
    }

    data class FormDescription(
        @SerializedName("description")
        val description: String?, // Forms have different stats.  If Aegislash's ability is stance change, it changes to Blade Forme before using a damaging move, and reverts to Shield Forme before using King's Shield or upon leaving the field.
        @SerializedName("language")
        val language: Language?
    ) {
        data class Language(
            @SerializedName("name")
            val name: String?, // en
            @SerializedName("url")
            val url: String? // https://pokeapi.co/api/v2/language/9/
        )
    }

    data class Genera(
        @SerializedName("genus")
        val genus: String?, // おうけんポケモン
        @SerializedName("language")
        val language: Language?
    ) {
        data class Language(
            @SerializedName("name")
            val name: String?, // ja-Hrkt
            @SerializedName("url")
            val url: String? // https://pokeapi.co/api/v2/language/1/
        )
    }

    data class Generation(
        @SerializedName("name")
        val name: String?, // generation-vi
        @SerializedName("url")
        val url: String? // https://pokeapi.co/api/v2/generation/6/
    )

    data class GrowthRate(
        @SerializedName("name")
        val name: String?, // medium
        @SerializedName("url")
        val url: String? // https://pokeapi.co/api/v2/growth-rate/2/
    )

    data class Name(
        @SerializedName("language")
        val language: Language?,
        @SerializedName("name")
        val name: String? // ギルガルド
    ) {
        data class Language(
            @SerializedName("name")
            val name: String?, // ja-Hrkt
            @SerializedName("url")
            val url: String? // https://pokeapi.co/api/v2/language/1/
        )
    }

    data class PokedexNumber(
        @SerializedName("entry_number")
        val entryNumber: Int?, // 681
        @SerializedName("pokedex")
        val pokedex: Pokedex?
    ) {
        data class Pokedex(
            @SerializedName("name")
            val name: String?, // national
            @SerializedName("url")
            val url: String? // https://pokeapi.co/api/v2/pokedex/1/
        )
    }

    data class Shape(
        @SerializedName("name")
        val name: String?, // blob
        @SerializedName("url")
        val url: String? // https://pokeapi.co/api/v2/pokemon-shape/5/
    )

    data class Variety(
        @SerializedName("is_default")
        val isDefault: Boolean?, // true
        @SerializedName("pokemon")
        val pokemon: Pokemon?
    ) {
        data class Pokemon(
            @SerializedName("name")
            val name: String?, // aegislash-shield
            @SerializedName("url")
            val url: String? // https://pokeapi.co/api/v2/pokemon/681/
        )
    }
}