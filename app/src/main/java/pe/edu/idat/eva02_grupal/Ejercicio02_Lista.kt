package pe.edu.idat.eva02_grupal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import pe.edu.idat.eva02_grupal.databinding.ActivityEjercicio02ListaBinding
import android.R
import android.content.Intent
import android.view.View

class Ejercicio02_Lista : AppCompatActivity(), View.OnClickListener {

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
        binding.btnRegresar3.setOnClickListener(this)
    }

    override fun onClick(vista: View) {
        when(vista.id){
            pe.edu.idat.eva02_grupal.R.id.btnRegresar3 -> {
                val intent = Intent(this,Ejercicio02::class.java)
                startActivity(intent)
            }
        }
    }
}