package pe.edu.idat.eva02_grupal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import pe.edu.idat.eva02_grupal.databinding.ActivityEjercicio02ListaBinding
import android.R

class Ejercicio02_Lista : AppCompatActivity() {

    private lateinit var binding: ActivityEjercicio02ListaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEjercicio02ListaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val listaCuestionarios = intent.getSerializableExtra("ListaCuestionario")
                as ArrayList<String>
        val adapter = ArrayAdapter(applicationContext,
            R.layout.simple_list_item_1,
            listaCuestionarios)
        binding.lvCuestionarios.adapter = adapter
    }
}