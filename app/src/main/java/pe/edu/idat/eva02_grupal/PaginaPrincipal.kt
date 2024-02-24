package pe.edu.idat.eva02_grupal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import pe.edu.idat.eva02_grupal.databinding.ActivityPaginaPrincipalBinding

class PaginaPrincipal : AppCompatActivity(), View.OnClickListener  {
        private lateinit var  binding: ActivityPaginaPrincipalBinding

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding= ActivityPaginaPrincipalBinding.inflate(layoutInflater)
            setContentView(binding.root)
            binding.btnRegistrar.setOnClickListener(this)
            binding.btnFormulario.setOnClickListener(this)
            binding.btnListar.setOnClickListener(this)
    }

    override fun onClick(vista: View) {
        when (vista.id) {
            R.id.btnRegistrar -> startActivity(
                Intent(applicationContext, Ejercicio01::class.java)
            )

            R.id.btnFormulario -> startActivity(
                Intent(applicationContext, Ejercicio02::class.java)
            )

            R.id.btnListar -> startActivity(
                Intent(applicationContext, Ejercicio03::class.java)
            )

        }
    }
}