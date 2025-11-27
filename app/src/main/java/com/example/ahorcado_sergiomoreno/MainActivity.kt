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

// ------------------------------------------------------
// ACTIVIDAD PRINCIPAL DEL JUEGO DEL AHORCADO
// ------------------------------------------------------
class MainActivity : AppCompatActivity() {

    // --------------------------------------------------
    // IMÁGENES DEL AHORCADO (SEGÚN LOS FALLOS)
    // Cada posición representa un estado del dibujo
    // --------------------------------------------------
    val imagen = arrayOf(
        R.mipmap.tori_cabeza_foreground,
        R.mipmap.tori_torso_foreground,
        R.mipmap.tori_brazos_foreground,
        R.mipmap.tori_piernas_foreground,
        R.mipmap.tori_enemigo_foreground,
        R.mipmap.tori_muerte_foreground
    )

    // --------------------------------------------------
    // VARIABLES DE CONTROL DEL JUEGO
    // --------------------------------------------------

    // Controla qué imagen del ahorcado se muestra
    var imagenID = 0

    // Cuenta los errores al comprobar una letra
    var errores = 0

    // --------------------------------------------------
    // LISTA DE PALABRAS DISPONIBLES
    // Letras separadas por comas para usar split(",")
    // --------------------------------------------------
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

    // --------------------------------------------------
    // PALABRA ACTUAL DEL JUEGO CONVERTIDA EN LISTA
    // --------------------------------------------------
    var palabraLetras = palabras[Random.nextInt(palabras.size)].split(",")

    // --------------------------------------------------
    // FUNCIÓN QUE COMPRUEBA LA LETRA INTRODUCIDA
    // --------------------------------------------------
    fun verificar() {

        // Reinicia el contador de errores para esta letra
        errores = 0

        // Obtiene la letra escrita por el usuario
        val letraSeleccionada = binding.letra.text.toString().uppercase()

        // Comprueba la letra con cada posición de la palabra

        if (letraSeleccionada == binding.letra1.text.toString()) {
            binding.letra1.visibility = View.VISIBLE
        } else errores++

        if (letraSeleccionada == binding.letra2.text.toString()) {
            binding.letra2.visibility = View.VISIBLE
        } else errores++

        if (letraSeleccionada == binding.letra3.text.toString()) {
            binding.letra3.visibility = View.VISIBLE
        } else errores++

        if (letraSeleccionada == binding.letra4.text.toString()) {
            binding.letra4.visibility = View.VISIBLE
        } else errores++

        if (letraSeleccionada == binding.letra5.text.toString()) {
            binding.letra5.visibility = View.VISIBLE
        } else errores++

        // Si la letra no coincide con ninguna posición
        if (errores > 4) {
            imagenID++                        // Avanza la imagen del ahorcado
            binding.error.append("$letraSeleccionada - ") // Guarda el error
            binding.horca.setImageResource(imagen[imagenID]) // Cambia imagen
        }
    }

    // --------------------------------------------------
    // FUNCIÓN QUE REINICIA EL JUEGO
    // --------------------------------------------------
    fun asignacion() {

        // Selecciona una palabra nueva aleatoria
        palabraLetras = palabras[Random.nextInt(palabras.size)].split(",")

        // Reinicia variables de estado
        imagenID = 0
        errores = 0
        binding.error.text = "Errores: "

        // Oculta todas las letras
        binding.letra1.visibility = View.INVISIBLE
        binding.letra2.visibility = View.INVISIBLE
        binding.letra3.visibility = View.INVISIBLE
        binding.letra4.visibility = View.INVISIBLE
        binding.letra5.visibility = View.INVISIBLE

        // Asigna las letras nuevas a los TextView
        binding.letra1.text = palabraLetras[0]
        binding.letra2.text = palabraLetras[1]
        binding.letra3.text = palabraLetras[2]
        binding.letra4.text = palabraLetras[3]
        binding.letra5.text = palabraLetras[4]
    }

    // --------------------------------------------------
    // VIEW BINDING PARA ACCEDER A LOS ELEMENTOS DE LA UI
    // --------------------------------------------------
    lateinit var binding: ActivityMainBinding

    // --------------------------------------------------
    // MÉTODO PRINCIPAL CUANDO SE CREA LA APP
    // --------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {

        // Inicializa el binding
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        // Ajusta márgenes según la barra del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Imagen inicial del ahorcado
        binding.horca.setImageResource(imagen[0])

        // Inicia una partida
        asignacion()

        // --------------------------------------------------
        // BOTÓN RESET: REINICIA EL JUEGO
        // --------------------------------------------------
        binding.reset.setOnClickListener {
            asignacion()
            binding.horca.setImageResource(imagen[0])
        }

        // --------------------------------------------------
        // BOTÓN ENVIAR: COMPRUEBA LA LETRA
        // --------------------------------------------------
        binding.enviar.setOnClickListener {

            verificar()

            // Si todas las letras son visibles → GANAS
            if (binding.letra1.isVisible && binding.letra2.isVisible &&
                binding.letra3.isVisible && binding.letra4.isVisible &&
                binding.letra5.isVisible) {

                Toast.makeText(this, "Has ganado !!", Toast.LENGTH_LONG).show()

                // Espera 1,5 segundos antes de reiniciar
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.horca.setImageResource(imagen[0])
                }, 1500)

            }
            // Si llega a la última imagen → PIERDES
            else if (imagenID == 5) {

                Toast.makeText(this, "Has perdido jajaja", Toast.LENGTH_LONG).show()

                Handler(Looper.getMainLooper()).postDelayed({
                    binding.horca.setImageResource(imagen[0])
                }, 1500)

                binding.horca.setImageResource(imagen[0])
            }
        }
    }
}
