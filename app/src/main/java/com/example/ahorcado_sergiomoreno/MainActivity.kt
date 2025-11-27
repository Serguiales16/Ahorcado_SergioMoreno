package com.example.ahorcado_sergiomoreno

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ahorcado_sergiomoreno.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        fun asignacion() {

            val palabras = arrayOf(
                "C,A,S,A,S",
                "P,E,R,R,O",
                "G,A,T,O,S",
                "J,U,E,G,O",
                "S,A,L,O,N",
                "P,L,A,Y,A",
                "T,E,C,L,A",
                "R,E,D,E,S",
                "C,L,A,S,E",
                "L,I,B,R,O",
                "C,O,D,I,G",
                "A,H,O,R,C",
                "N,U,B,E,S",
                "P,I,E,D,A",
                "V,I,D,E,O",
                "M,O,U,S,E",
                "P,I,X,E,L",
                "S,O,N,I,D",
                "F,O,R,M,A",
                "L,O,G,I,C"
            )

            val palabraLetras = (palabras[Random.nextInt(palabras.size)].split(","))


            //   var palabraLetras = arrayOf(palabras[Random.nextInt(palabras.size)].split(","))

            binding.letra1.visibility = View.INVISIBLE
            binding.letra2.visibility = View.INVISIBLE
            binding.letra3.visibility = View.INVISIBLE
            binding.letra4.visibility = View.INVISIBLE
            binding.letra5.visibility = View.INVISIBLE

            binding.letra1.text = palabraLetras[0].toString()
            binding.letra2.text = palabraLetras[1].toString()
            binding.letra3.text = palabraLetras[2].toString()
            binding.letra4.text = palabraLetras[3].toString()
            binding.letra5.text = palabraLetras[4].toString()

        }



        binding.enviar.setOnClickListener {

            var letraSeleccionada = binding.letra.text.toString().uppercase()




        }

    }
}