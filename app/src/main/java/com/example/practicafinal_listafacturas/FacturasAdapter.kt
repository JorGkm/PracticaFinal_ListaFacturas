package com.example.practicafinal_listafacturas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FacturasAdapter : RecyclerView.Adapter<FacturasAdapter.FacturaViewHolder>() {

    private var listaFacturas: List<Facturas> = emptyList()

    fun actualizarLista(nuevaLista: List<Facturas>) {
        listaFacturas = nuevaLista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacturaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_factura, parent, false)
        return FacturaViewHolder(view)
    }

    override fun onBindViewHolder(holder: FacturaViewHolder, position: Int) {
        val factura = listaFacturas[position]
        holder.bind(factura)
    }

    override fun getItemCount(): Int = listaFacturas.size

    class FacturaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvFecha: TextView = itemView.findViewById(R.id.tvFecha)
        private val tvImporte: TextView = itemView.findViewById(R.id.tvImporte)
        private val tvEstado: TextView = itemView.findViewById(R.id.tvEstado)

        fun bind(factura: Facturas) {
            tvFecha.text = "Fecha: ${factura.fecha}"
            tvImporte.text = "Importe: ${factura.importeOrdenacion} €"
            tvEstado.text = "Estado: ${factura.descEstado}"
        }
    }
}
