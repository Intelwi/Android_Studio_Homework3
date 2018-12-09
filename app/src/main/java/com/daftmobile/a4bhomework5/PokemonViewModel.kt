package com.daftmobile.a4bhomework5

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.provider.Settings
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
/**
 * Created by Konrad Kowalewski.
 */
class PokemonViewModel: ViewModel() {

    private val pokemonLiveData = SingleLiveEvent<PokemonItem>()
    private val errorLiveData = SingleLiveEvent<String>()

    fun newPokemon(): LiveData<PokemonItem> = pokemonLiveData
    fun error(): LiveData<String> = errorLiveData

    fun showPokemonInfo(index: String) {
        val client = OkHttpClient.Builder()
                .build()

        val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl("https://switter.app.daftmobile.com/")
//                  .addConverterFactory(GsonConverterFactory.create())
                .build()

        val pokeApi = retrofit.create(PokeApi::class.java)

        val call = pokeApi.getPokemon(index)
        call.enqueue(object : Callback<PokemonItem> {
            override fun onResponse(call: Call<PokemonItem>, response: Response<PokemonItem>) {
                if (response.isSuccessful) {
                    pokemonLiveData.setValue(response.body())
                }
                else {
                    errorLiveData.setValue("Serwer zwrócił: ${response.code()}")
                }
            }
            override fun onFailure(call: Call<PokemonItem>, t: Throwable) {
                errorLiveData.setValue(t.message ?: "No message")
            }

        })
    }
}
