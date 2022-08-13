package fiap.com.nac_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import fiap.com.nac_1.databinding.ActivityMainBinding
import fiap.com.nac_1.model.Pokemon
import fiap.com.nac_1.webServices.PokemonConnection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btBuscar.setOnClickListener {
            val id = binding.etSearch.text.toString().toInt()
            searchPokemon(id)
        }
    }

    private fun searchPokemon(id: Int) {
        val callback = object : Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                val pokemon = response.body()!!
                binding.tvPokemonName.text = pokemon.name
                Glide.with(this@MainActivity).load(pokemon.sprites.front).into(binding.ivPokemonImage)
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Erro ao buscar o Pokémon", Toast.LENGTH_SHORT).show()
            }

        }
        PokemonConnection().pokemonService.getPokemon(id).enqueue(callback)
    }

}