package com.example.ahorcado_sergiomoreno

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ahorcado_sergiomoreno.databinding.ActivityMainBinding
import kotlin.random.Random
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    val imagen = arrayOf(R.mipmap.tori_cabeza_foreground, R.mipmap.tori_torso_foreground, R.mipmap.tori_brazos_foreground,
        R.mipmap.tori_piernas_foreground, R.mipmap.tori_enemigo_foreground, R.mipmap.tori_muerte_foreground)

    var imagenID = 0;
    var errores = 0
    val palabras = arrayOf(
        "A,R,B,O,L",
        "C,A,M,P,O",
        "S,U,E,L,O",
        "H,O,G,A,R",
        "M,U,N,D,O",
        "F,O,R,M,A",
        "N,O,C,H,E",
        "C,R,E,M,A",
        "R,I,E,G,O",
        "B,R,A,Z,O",
        "T,R,A,J,E",
        "C,A,L,O,R",
        "C,O,L,O,R"
    )


    val palabraLetras = (palabras[Random.nextInt(palabras.size)].split(","))

    fun verificar() {

        errores = 0



        val letraSeleccionada = binding.letra.text.toString().uppercase()


        if (letraSeleccionada.equals(binding.letra1.text.toString())) {

            binding.letra1.visibility = View.VISIBLE
        } else {
            errores++
        }

        if (letraSeleccionada.equals(binding.letra2.text.toString())) {

            binding.letra2.visibility = View.VISIBLE

        } else {

            errores++

        }

        if (letraSeleccionada.equals(binding.letra3.text.toString())) {

            binding.letra3.visibility = View.VISIBLE

        } else {

            errores++

        }

        if (letraSeleccionada.equals(binding.letra4.text.toString())) {

            binding.letra4.visibility = View.VISIBLE

        } else {

            errores++

        }

        if (letraSeleccionada.equals(binding.letra5.text.toString())) {

            binding.letra5.visibility = View.VISIBLE

        } else {

            errores++

        }

        if (errores > 4) {

            imagenID++

            binding.error.append("$letraSeleccionada - ")
            binding.horca.setImageResource(imagen[imagenID])
        }

    }



    fun asignacion() {

        imagenID = 0
        errores = 0
        binding.error.text = "Errores: "

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

        binding.horca.setImageResource(imagen[0])
        asignacion()
        binding.letra1.visibility = View.INVISIBLE
        binding.letra2.visibility = View.INVISIBLE
        binding.letra3.visibility = View.INVISIBLE
        binding.letra4.visibility = View.INVISIBLE
        binding.letra5.visibility = View.INVISIBLE



        binding.reset.setOnClickListener {

            asignacion()
            binding.horca.setImageResource(imagen[0])

        }


        binding.enviar.setOnClickListener {

            verificar()

            if (binding.letra1.isVisible && binding.letra2.isVisible
                && binding.letra3.isVisible && binding.letra4.isVisible && binding.letra5.isVisible) {

                Toast.makeText(this, "Has ganado !!", Toast.LENGTH_LONG).show()
                Handler(Looper.getMainLooper()).postDelayed({
                    // Este código se ejecutará después del retardo en el hilo principal
                    binding.horca.setImageResource(imagen[0])
                }, 1500)
            } else if (imagenID == 5) {

                Toast.makeText(this, "Has perdido jajaja", Toast.LENGTH_LONG).show()
                Handler(Looper.getMainLooper()).postDelayed({
                    // Este código se ejecutará después del retardo en el hilo principal
                    binding.horca.setImageResource(imagen[0])
                }, 1500)
                binding.horca.setImageResource(imagen[0])
        }

        }

    }
}