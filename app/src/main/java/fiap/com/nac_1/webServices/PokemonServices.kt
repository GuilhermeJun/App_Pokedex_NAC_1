package fiap.com.nac_1.webServices

import fiap.com.nac_1.model.Pokemon
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonServices {
    @GET("pokemon/{id}")
    fun getPokemon(@Path("id") id: Int) : Call<Pokemon>
}

class PokemonConnection {
    private val retrofit = Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/").addConverterFactory(GsonConverterFactory.create()).build()
    val pokemonService = retrofit.create(PokemonServices::class.java)
}