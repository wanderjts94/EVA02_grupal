package pe.edu.idat.eva02_grupal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import pe.edu.idat.eva02_grupal.databinding.ActivityListadoBinding
import android.R
import android.content.Intent

class ListadoActivity : AppCompatActivity(), View.OnClickListener  {
    private lateinit var binding: ActivityListadoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListadoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val Listaregistar = intent.getSerializableExtra("listaregistros")
                as ArrayList<String>
        val adapter = ArrayAdapter(applicationContext,
            R.layout.simple_list_item_1,
            Listaregistar)
        binding.lstregist.adapter = adapter
        binding.btnvol.setOnClickListener(this)
    }

    override fun onClick(vista: View) {
        when (vista.id) {
            pe.edu.idat.eva02_grupal.R.id.btnvol -> {
                // Redirect to Ejercicio01Activity
                val intent = Intent(this, Ejercicio01::class.java)
                startActivity(intent)
            }
        }
    }
}