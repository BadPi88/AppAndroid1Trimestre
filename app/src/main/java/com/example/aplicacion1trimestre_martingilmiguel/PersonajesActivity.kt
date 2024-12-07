package com.example.aplicacion1trimestre_martingilmiguel

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.aplicacion1trimestre_martingilmiguel.adapters.PersonajesAdapter
import com.example.aplicacion1trimestre_martingilmiguel.databinding.ActivityPersonajesBinding
import com.example.aplicacion1trimestre_martingilmiguel.models.Personaje
import com.example.aplicacion1trimestre_martingilmiguel.providers.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonajesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPersonajesBinding
    var lista = mutableListOf<Personaje>()
    var personajesAdapter = PersonajesAdapter(lista)
    var api = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonajesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setRecycler()

    }

    private fun setRecycler() {
        val layoutManager = GridLayoutManager(this, 1)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = personajesAdapter
        traerDatos()
    }

    private fun traerDatos() {
        lifecycleScope.launch(Dispatchers.IO) {

            // Llamada a la API
            val respuesta = ApiClient.apiClient.getPersonajes()

            // Verificar si la respuesta fue exitosa y manejar el cuerpo
            if (respuesta.isSuccessful && respuesta.body() != null) {

            } else {

                Toast.makeText(
                    this@PersonajesActivity,
                    "Error en la respuesta de la API",
                    Toast.LENGTH_SHORT
                ).show()

            }


        }
    }
}



