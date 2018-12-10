package com.daftmobile.a4bhomework5

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pokemon.*

class PokemonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)

        val intent = intent
        val pokemon = intent?.extras?.get("pokemon") as? PokemonItem
        nameView.text = pokemon?.name
        numberView.text = pokemon?.number
       // imageView.setBackgroundColor(pokemon?.color ?: 1) //NIE WIEM JAK Z TEGO WYCIAGNAC KOLOR
    }
}
