package com.example.proyecto_1_aguero_castillo_canul_serrano

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyclerview_item.view.*
import kotlin.coroutines.coroutineContext

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
        var rvItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false)

        return MyViewHolder(rvItem)

    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // *** OK ***
        // El holder contiene el elemento completo, por eso buscamos el
        // TextView hijo para escribir el texto

        var olo= holder.itemView

        holder.itemView.setOnClickListener {
            Toast.makeText(olo.context, "Ahora si "+olo.rv_text.text, Toast.LENGTH_SHORT).show()

            val intent:Intent = Intent(olo.context, PuntajeUsuario::class.java).apply {
                putExtra("Usuario", olo.rv_text.text)
            }
            olo.context.startActivity(intent)
        }

        holder.view.findViewById<TextView>(R.id.rv_text).text= data[position]
    }



    override fun getItemCount() = data.size

}
