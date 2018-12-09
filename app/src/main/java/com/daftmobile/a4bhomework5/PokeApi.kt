package com.daftmobile.a4bhomework5
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApi {
    @GET("/api/pokemon/{number}/peek")
    fun getPokemon(@Path("number") number: String): Call<PokemonItem>
}