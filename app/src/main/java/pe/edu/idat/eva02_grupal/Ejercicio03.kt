package pe.edu.idat.eva02_grupal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import pe.edu.idat.eva02_grupal.databinding.ActivityEjercicio03Binding


class Ejercicio03 : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityEjercicio03Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityEjercicio03Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val listaTests=listado()
        binding.rvlistas.layoutManager = LinearLayoutManager(applicationContext)
        binding.rvlistas.adapter= AdapterLista(listaTests)
        binding.btnRegresar4.setOnClickListener(this)

    }
    private fun listado(): List<TestPsicologico> {
        val lista = ArrayList<TestPsicologico>()
        lista.add(TestPsicologico("Test de Personalidad", "Evalúa diferentes aspectos de la personalidad.", "01/01/2024"))
        lista.add(TestPsicologico("Test de Ansiedad", "Mide el nivel de ansiedad .", "02/01/2024"))
        lista.add(TestPsicologico("Test de Depresión", "Evalúa los síntomas de la depresión.", "03/01/2024"))
        lista.add(TestPsicologico("Test de Estrés", "Mide el nivel de estrés percibido.", "04/01/2024"))
        lista.add(TestPsicologico("Test de Autoestima", "Evalúa la percepción de uno mismo.", "05/01/2024"))
        lista.add(TestPsicologico("Test de Inteligencia Emocional", "Mide la capacidad de comprender y manejar las emociones.", "06/01/2024"))
        lista.add(TestPsicologico("Test de Habilidades Sociales", "Evalúa la habilidad para interactuar con otras personas.", "07/01/2024"))
        lista.add(TestPsicologico("Test de Resiliencia", "Mide la capacidad para superar adversidades.", "08/01/2024"))
        lista.add(TestPsicologico("Test de Motivación", "Evalúa los niveles de motivación.", "09/01/2024"))
        lista.add(TestPsicologico("Test de Empatía", "Mide la capacidad para comprender los sentimientos de los demás.", "10/01/2024"))
        return lista
    }

    override fun onClick(vista: View) {
        when(vista.id){
            R.id.btnRegresar4 -> {
                val intent = Intent(this, PaginaPrincipal::class.java)
                startActivity(intent)
            }
        }
    }
}