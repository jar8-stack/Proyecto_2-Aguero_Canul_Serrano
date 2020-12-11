package com.example.proyecto_1_aguero_castillo_canul_serrano

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(val data: ArrayList<String>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    // *** OK ***
    // El error radicó en pasarle al método RecyclerView.ViewHolder()
    // el TextView hijo del activity, le estoy pasando el item completo,
    // no solo el textview
    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyAdapter.MyViewHolder {

        // *** OK ***
        // Solo se "infla" el xml, no se obtiene el enlace al TextView
        val rvItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(rvItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // *** OK ***
        // El holder contiene el elemento completo, por eso buscamos el
        // TextView hijo para escribir el texto
        holder.view.findViewById<TextView>(R.id.rv_text).text= data[position]
    }

    override fun getItemCount() = data.size
}
