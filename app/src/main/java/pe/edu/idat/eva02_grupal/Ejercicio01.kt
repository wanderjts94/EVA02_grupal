package pe.edu.idat.eva02_grupal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import pe.edu.idat.eva02_grupal.databinding.ActivityEjercicio01Binding
import android.content.Intent
import pe.edu.idat.eva02_grupal.otros.AppMensaje
import pe.edu.idat.eva02_grupal.otros.TipoMensaje

import java.util.ArrayList

class Ejercicio01 : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityEjercicio01Binding
    private val listacualidades = ArrayList<String>()
    private val listaregistros= ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEjercicio01Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnacceder.setOnClickListener(this)
        binding.rbIng.setOnClickListener(this)
        binding.rbAdmin.setOnClickListener(this)
        binding.rbCienMet.setOnClickListener(this)
        binding.rbOtros.setOnClickListener(this)
    }

    override fun onClick(vista: View) {
        if (vista is CheckBox) {
            agregarQuitarcualidades(vista)
        } else {
            when (vista.id) {
                R.id.btnacceder -> {
                    registrarregistros()
                    startActivity(Intent(applicationContext, ListadoActivity::class.java).apply {
                        putExtra("listaregistros", listaregistros)
                    })
                }
            }
        }
    }



    private fun agregarQuitarcualidades(vista: CheckBox) {

        if(vista.isChecked) {
            when (vista.id) {
                R.id.ckPuntual -> listacualidades.add(vista.text.toString())
                R.id.ckResp -> listacualidades.add(vista.text.toString())
                R.id.ckResponsab -> listacualidades.add(vista.text.toString())
                R.id.ckotro -> listacualidades.add(vista.text.toString())
            }
        }else{
            when(vista.id){
                R.id.ckResponsab -> listacualidades.remove(vista.text.toString())
                R.id.ckResp -> listacualidades.remove(vista.text.toString())
                R.id.ckPuntual -> listacualidades.remove(vista.text.toString())
                R.id.ckotro -> listacualidades.remove(vista.text.toString())
            }
        }
    }
    fun registrarregistros() {
        if (validarregistro()) {
            val inforegistro = "${binding.tvnombre.text.toString()} ${binding.tvapellido.text.toString()} ${binding.tvdni.text.toString()} ${binding.tvcelular.text.toString()}" +
                    " ${binding.tvemail.text} ${obtenercarreraseleccionada()} ${obtenercualidades()}"
            listaregistros.add(inforegistro)
            limpiarControles()
        }
    }



    private fun obtenercualidades():String{
        var cualidades=""
        for(cual in listacualidades){
            cualidades+= "$cual -"
        }
        return cualidades

    }
    private fun limpiarControles() {
        listaregistros.clear()
        binding.tvnombre.setText("")
        binding.tvapellido.setText("")
        binding.tvdni.setText("")
        binding.tvcelular.setText("")
        binding.tvemail.setText("")
        binding.ckPuntual.isChecked=false
        binding.ckResp.isChecked=false
        binding.ckResponsab.isChecked=false
        binding.ckotro.isChecked=false
        binding.rgcarreras.clearCheck()
        binding.tvnombre.isFocusableInTouchMode=true
        binding.tvnombre.requestFocus()
    }

    fun validarcualidades():Boolean{
        var repuesta=true
        if(binding.ckPuntual.isChecked || binding.ckResp.isChecked || binding.ckResponsab.isChecked|| binding.ckotro.isChecked){
            repuesta=true
        }
        return repuesta
    }



    fun validarCarrera(): Boolean {
        var respuesta = false
        if (binding.rbAdmin.isChecked ||
            binding.rbIng.isChecked ||
            binding.rbCienMet.isChecked ||
            binding.rbOtros.isChecked) {
            respuesta = true
        }
        return respuesta
    }
    fun validarnombreapp():Boolean{
        var respuesta=true
        if(binding.tvnombre.text.toString().trim().isEmpty()){

            binding.tvnombre.isFocusableInTouchMode = true
            binding.tvnombre.requestFocus()
            respuesta=false
        }else if(binding.tvapellido.text.toString().trim().isEmpty()) {
            binding.tvapellido.isFocusableInTouchMode = true
            binding.tvapellido.requestFocus()
            respuesta = false
        }
        return respuesta
    }


    fun validarregistro(): Boolean {
        var respuesta = false
        if (!validarnombreapp()) {
            AppMensaje.enviarmensaje(binding.root, "Ingrese Nombre y Apellido", TipoMensaje.ERROR)
        }
        else if (!validarCarrera()) {
            AppMensaje.enviarmensaje(binding.root, "Ingrese genero", TipoMensaje.ERROR)
        }
        else if (!validarcualidades()) {
            AppMensaje.enviarmensaje(binding.root, "Ingrese preferencia", TipoMensaje.ERROR)
        }
        else {
            respuesta = true
        }
        return respuesta
    }

    fun obtenercarreraseleccionada():String {
        var carrera = ""
        when (binding.rgcarreras.checkedRadioButtonId) {
            R.id.rbIng -> {
                carrera = binding.rbIng.text.toString()
            }

            R.id.rbAdmin -> {
                carrera = binding.rbAdmin.text.toString()
            }
            R.id.rbCienMet -> {
                carrera = binding.rbCienMet.text.toString()
            }
            R.id.rbOtros -> {
                carrera = binding.rbOtros.text.toString()
            }
        }
        return carrera
    }
}