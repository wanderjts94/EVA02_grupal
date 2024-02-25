package pe.edu.idat.eva02_grupal

import android.content.Intent
import android.content.SyncRequest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.CheckBox
import pe.edu.idat.eva02_grupal.databinding.ActivityEjercicio02Binding
import pe.edu.idat.eva02_grupal.otros.AppMensaje
import pe.edu.idat.eva02_grupal.otros.TipoMensaje

class Ejercicio02 : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityEjercicio02Binding

    private var ListaCuestionario = ArrayList<String>()
    private var ListaPlatillos = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEjercicio02Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnResolver.setOnClickListener(this)

        binding.cbArrozPollo.setOnClickListener(this)
        binding.cbLomoSaltado.setOnClickListener(this)
        binding.cbAjiGallina.setOnClickListener(this)
        binding.cbTallarines.setOnClickListener(this)
        binding.cbArrozChaufa.setOnClickListener(this)
        binding.cbOtros.setOnClickListener(this)
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

    fun validarIngles(): Boolean {
        var respuesta = true
        if (binding.rg03.checkedRadioButtonId == -1){
            respuesta = false
        }
        return respuesta
    }

    fun validarTecnologia(): Boolean {
        var respuesta = true
        if (binding.rg04.checkedRadioButtonId == -1){
            respuesta = false
        }
        return respuesta
    }

    fun validarRemoto(): Boolean {
        var respuesta = true
        if (binding.rg05.checkedRadioButtonId == -1){
            respuesta = false
        }
        return respuesta
    }

    override fun onClick(vista: View) {
        if(vista is CheckBox){
            agregarQuitarPlatilloSeleccionado(vista)
        } else {
            when (vista.id) {
                R.id.btnResolver -> {
                    if (validarFormulario()) {
                        registrarCuestionario()
                        // Agregar un retraso de 5 segundos antes de iniciar la siguiente actividad
                        Handler().postDelayed({
                            startActivity(Intent(applicationContext, Ejercicio02_Lista::class.java).apply {
                                putExtra("ListaCuestionario", ListaCuestionario)
                            })
                        }, 5000) // 5000 milisegundos = 5 segundos
                    }
                }
            }
        }
    }



    fun registrarCuestionario() {
        if (validarFormulario()) {
            val infoCuestionario = obtenerPlatillo() + " " +
                    obtenerPaisSeleccionado() + " " +
                    obtenerInglesSeleccionado() + " " +
                    obtenerTecnologiaSeleccionado() + " " +
                    obtenerRemotoSeleccionado()
            ListaCuestionario.add(infoCuestionario.toString())
            val mensaje = getString(R.string.mensajeRegistroCorrecto)
            AppMensaje.enviarmensaje(binding.root, mensaje, TipoMensaje.SUCCESSFULL)
            setearControles()
        }
    }

    private fun obtenerPlatillo(): String{
        var platillos = ""
        for (plat in ListaPlatillos){
            platillos += "$plat -"
        }
        return platillos
    }

    private fun setearControles() {
        ListaPlatillos.clear()
        binding.cbArrozPollo.isChecked = false
        binding.cbLomoSaltado.isChecked = false
        binding.cbAjiGallina.isChecked = false
        binding.cbTallarines.isChecked = false
        binding.cbArrozChaufa.isChecked = false
        binding.cbOtros.isChecked = false
        binding.rg02.clearCheck()
        binding.rg03.clearCheck()
        binding.rg04.clearCheck()
        binding.rg05.clearCheck()
    }

    private fun agregarQuitarPlatilloSeleccionado(vista: CheckBox) {

        if (vista.isChecked){
            when(vista.id){
                R.id.cbArrozPollo -> ListaPlatillos.add(vista.text.toString())
                R.id.cbLomoSaltado -> ListaPlatillos.add(vista.text.toString())
                R.id.cbAjiGallina -> ListaPlatillos.add(vista.text.toString())
                R.id.cbTallarines -> ListaPlatillos.add(vista.text.toString())
                R.id.cbArrozChaufa -> ListaPlatillos.add(vista.text.toString())
                R.id.cbOtros -> ListaPlatillos.add(vista.text.toString())
            }
        }else{
            when(vista.id){
                R.id.cbArrozPollo -> ListaPlatillos.remove(vista.text.toString())
                R.id.cbLomoSaltado -> ListaPlatillos.remove(vista.text.toString())
                R.id.cbAjiGallina -> ListaPlatillos.remove(vista.text.toString())
                R.id.cbTallarines -> ListaPlatillos.remove(vista.text.toString())
                R.id.cbArrozChaufa -> ListaPlatillos.remove(vista.text.toString())
                R.id.cbOtros -> ListaPlatillos.remove(vista.text.toString())
            }
        }
    }

    fun btnResolver(){
        validarFormulario()
    }

    fun validarFormulario(): Boolean {
        var respuesta = false
        if(!validarPlatillo()){
            AppMensaje.enviarmensaje(binding.root, "Seleccione al menos un platillo", TipoMensaje.ERROR)
        }else if (!validarPais()){
            AppMensaje.enviarmensaje(binding.root, "Seleccione una alternativa", TipoMensaje.ERROR)
        }else if (!validarIngles()){
            AppMensaje.enviarmensaje(binding.root, "Seleccione una alternativa", TipoMensaje.ERROR)
        }else if (!validarTecnologia()){
            AppMensaje.enviarmensaje(binding.root, "Seleccione una alternativa", TipoMensaje.ERROR)
        }else if (!validarRemoto()){
            AppMensaje.enviarmensaje(binding.root, "Seleccione una alternativa", TipoMensaje.ERROR)
        }else{
            respuesta = true
        }
        return respuesta
    }

    fun obtenerPaisSeleccionado():String{
        var pais = ""
        when(binding.rg02.checkedRadioButtonId){
            R.id.rbsi1 -> {
                pais = binding.rbsi1.text.toString()
            }
            R.id.rbno1 -> {
                pais = binding.rbno1.text.toString()
            }
        }
        return pais
    }

    fun obtenerInglesSeleccionado():String{
        var ingles = ""
        when(binding.rg03.checkedRadioButtonId){
            R.id.rbsi2 -> {
                ingles = binding.rbsi2.text.toString()
            }
            R.id.rbno2 -> {
                ingles = binding.rbno2.text.toString()
            }
        }
        return ingles
    }

    fun obtenerTecnologiaSeleccionado():String{
        var tecnologia = ""
        when(binding.rg04.checkedRadioButtonId){
            R.id.rbsi3 -> {
                tecnologia = binding.rbsi3.text.toString()
            }
            R.id.rbno3 -> {
                tecnologia = binding.rbno3.text.toString()
            }
        }
        return tecnologia
    }

    fun obtenerRemotoSeleccionado():String{
        var remoto = ""
        when(binding.rg05.checkedRadioButtonId){
            R.id.rbsi4 -> {
                remoto = binding.rbsi4.text.toString()
            }
            R.id.rbno4 -> {
                remoto = binding.rbno4.text.toString()
            }
        }
        return remoto
    }



}