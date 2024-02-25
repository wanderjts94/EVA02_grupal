package pe.edu.idat.eva02_grupal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import pe.edu.idat.eva02_grupal.databinding.ActivityEjercicio01Binding
import android.content.Intent
import android.os.Handler
import android.util.Log
import pe.edu.idat.eva02_grupal.otros.AppMensaje
import pe.edu.idat.eva02_grupal.otros.TipoMensaje
import android.util.Patterns


import java.util.ArrayList

class Ejercicio01 : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityEjercicio01Binding
    private val listacualidades = ArrayList<String>()
    private val listaregistros = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEjercicio01Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnacceder.setOnClickListener(this)
        binding.btnregresar1.setOnClickListener(this)
        binding.chbPruntual.setOnClickListener(this)
        binding.chbresponsable.setOnClickListener(this)
        binding.chbRespetuoso.setOnClickListener(this)
        binding.chbOTros.setOnClickListener(this)
    }

    override fun onClick(vista: View) {
        if (vista is CheckBox) {
            agregarQuitarcualidades(vista)
        } else {
            when (vista.id) {
                R.id.btnacceder -> {
                    if (validarregistro()) {
                        registrarregistros()
                    }
                }

                R.id.btnregresar1 -> {
                    // Redirect
                    val intent = Intent(this, PaginaPrincipal::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    private fun agregarQuitarcualidades(vista: CheckBox) {
        if (vista.isChecked) {
            when (vista.id) {
                R.id.chbPruntual -> listacualidades.add(vista.text.toString())
                R.id.chbresponsable -> listacualidades.add(vista.text.toString())
                R.id.chbRespetuoso -> listacualidades.add(vista.text.toString())
                R.id.chbOTros -> listacualidades.add(vista.text.toString())
            }
        } else {
            when (vista.id) {
                R.id.chbPruntual -> listacualidades.remove(vista.text.toString())
                R.id.chbresponsable -> listacualidades.remove(vista.text.toString())
                R.id.chbRespetuoso -> listacualidades.remove(vista.text.toString())
                R.id.chbOTros -> listacualidades.remove(vista.text.toString())
            }
        }
    }

    fun registrarregistros() {
        if (validarregistro()) {
            val infopersona = binding.tvnombre.text.toString() + " " +
                    binding.tvapellido.text.toString() + " " +
                    binding.tvdni.text.toString() + " " +
                    binding.tvcelular.text.toString() + " " +
                    binding.tvemail.text.toString() + " " +
                    obtenercarreraseleccionada() + " " +
                    obtenercualidades()
            listaregistros.add(infopersona)
            Log.i("Valores de lista Registros", infopersona)
            AppMensaje.enviarmensaje(binding.root, "Registro Exitoso", TipoMensaje.SUCCESSFULL)
            limpiarControles()
            Handler().postDelayed({
                startActivity(Intent(applicationContext, ListadoActivity::class.java).apply {
                    putExtra("listaregistros", listaregistros)
                })
            }, 5000)
        }
    }

    fun obtenercarreraseleccionada(): String {
        var carrera = ""
        val radioButtonId = binding.rgcarreras.checkedRadioButtonId
        if (radioButtonId != -1) {
            carrera = when (radioButtonId) {
                R.id.rbIng -> binding.rbIng.text.toString()
                R.id.rbAdmin -> binding.rbAdmin.text.toString()
                R.id.rbCienMet -> binding.rbCienMet.text.toString()
                R.id.rbOtros -> binding.rbOtros.text.toString()
                else -> ""
            }
        }
        return carrera
    }

    private fun obtenercualidades(): String {
        if (listacualidades.isEmpty()) {
            return "Cualidades: []"
        } else {
            var cualidades = "Cualidades: ["
            for ((index, cual) in listacualidades.withIndex()) {
                cualidades += cual
                if (index < listacualidades.size - 1) {
                    cualidades += " - "
                }
            }
            cualidades += "]"
            return cualidades
        }
    }


    private fun limpiarControles() {
        listacualidades.clear()
        binding.tvnombre.setText("")
        binding.tvapellido.setText("")
        binding.tvdni.setText("")
        binding.tvcelular.setText("")
        binding.tvemail.setText("")
        binding.chbPruntual.isChecked = false
        binding.chbresponsable.isChecked = false
        binding.chbRespetuoso.isChecked = false
        binding.chbOTros.isChecked = false
        binding.rgcarreras.clearCheck()
        binding.tvnombre.isFocusableInTouchMode = true
        binding.tvnombre.requestFocus()
    }

    fun validarcualidades(): Boolean {
        var respuesta = false
        if (binding.chbPruntual.isChecked || binding.chbresponsable.isChecked ||
            binding.chbRespetuoso.isChecked || binding.chbOTros.isChecked
        ) {
            respuesta = true
        }
        return respuesta
    }


    fun validarDNI(dni: String): Boolean {
        return dni.length == 8 && dni.matches("\\d+".toRegex())
    }

    fun validarCelular(celular: String): Boolean {
        return celular.length == 9
    }

    fun validarEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun validarCarrera(): Boolean {
        var respuesta = false
        if (binding.rbAdmin.isChecked ||
            binding.rbIng.isChecked ||
            binding.rbCienMet.isChecked ||
            binding.rbOtros.isChecked
        ) {
            respuesta = true
        }
        return respuesta
    }

    fun validarnombreapp(): Boolean {
        var respuesta = true
        if (binding.tvnombre.text.toString().trim().isEmpty()) {
            binding.tvnombre.isFocusableInTouchMode = true
            binding.tvnombre.requestFocus()
            respuesta = false
        } else if (binding.tvapellido.text.toString().trim().isEmpty()) {
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
        } else if (!validarDNI(binding.tvdni.text.toString())) {
            AppMensaje.enviarmensaje(binding.root, "DNI inválido", TipoMensaje.ERROR)
        } else if (!validarCelular(binding.tvcelular.text.toString())) {
            AppMensaje.enviarmensaje(binding.root, "Número de celular inválido", TipoMensaje.ERROR)
        } else if (!validarEmail(binding.tvemail.text.toString())) {
            AppMensaje.enviarmensaje(binding.root, "Email inválido", TipoMensaje.ERROR)
        } else if (!validarcualidades()) {
            AppMensaje.enviarmensaje(binding.root, "seleccione cualidades", TipoMensaje.ERROR)
        } else if (!validarCarrera()) {
            AppMensaje.enviarmensaje(binding.root, "seleccione  carrera", TipoMensaje.ERROR)
        } else {
            respuesta = true
        }
        return respuesta
    }

}





