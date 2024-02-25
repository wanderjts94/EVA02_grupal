package pe.edu.idat.eva02_grupal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.edu.idat.eva02_grupal.databinding.ItemListatestBinding

class AdapterLista(private var lstTest:List<TestPsicologico>) :RecyclerView.Adapter<AdapterLista.ViewHolder>() {
    inner class ViewHolder(val binding: ItemListatestBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val binding= ItemListatestBinding.inflate(
           LayoutInflater.from(parent.context),
           parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount()= lstTest.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentTest = lstTest[position]
        holder.binding.tvTitulo.text = currentTest.titulo
        holder.binding.tvDescripcion.text = currentTest.descripcion
        holder.binding.tvFecha.text = currentTest.fechaPublicacion
    }
}