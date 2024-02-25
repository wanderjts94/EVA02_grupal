package pe.edu.idat.eva02_grupal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import pe.edu.idat.eva02_grupal.databinding.ActivityEjercicio02Binding
import pe.edu.idat.eva02_grupal.otros.AppMensaje
import pe.edu.idat.eva02_grupal.otros.TipoMensaje

class Ejercicio02 : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityEjercicio02Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEjercicio02Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnResolver.setOnClickListener(this)
    }

    fun validarPlatillo(): Boolean {
        var respuesta = false
        if (binding.cbArrozPollo.isChecked || binding.cbLomoSaltado.isChecked ||
            binding.cbAjiGallina.isChecked || binding.cbTallarines.isChecked ||
            binding.cbArrozChaufa.isChecked || binding.cbOtros.isChecked){
            respuesta   = true
        }
        return respuesta
    }

    fun validarPais(): Boolean {
        var respuesta = true
        if (binding.rg02.checkedRadioButtonId == -1){
            respuesta = false
        }
        return respuesta
    }

    override fun onClick(vista: View) {
        when(vista.id){
            R.id.btnResolver -> btnResolver()
        }
    }

    fun btnResolver(){
        validarFormulario()
    }

    fun validarFormulario(): Boolean {
        var respuesta = false
        if(!validarPais()){
            AppMensaje.enviarmensaje(binding.root, "Seleccione una alternativa", TipoMensaje.ERROR)
        }else if (!validarPlatillo()){
            AppMensaje.enviarmensaje(binding.root, "Seleccione al menos un platillo", TipoMensaje.ERROR)
        }else{
            respuesta = true
        }
        return respuesta
    }

}