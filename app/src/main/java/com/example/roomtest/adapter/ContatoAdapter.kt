package com.example.roomtest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomtest.R
import com.example.roomtest.holder.ContatoHolder
import com.example.roomtest.model.Contato

class ContatoAdapter : RecyclerView.Adapter<ContatoHolder>() {

    private var listaContatos = listOf<Contato>()

    fun carregarLista(novaLista: List<Contato>){
        listaContatos = novaLista
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContatoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contato_recycler_layout, parent, false)

        return ContatoHolder(view)
    }

    override fun getItemCount(): Int {
        return listaContatos.size
    }

    override fun onBindViewHolder(holder: ContatoHolder, position: Int) {
        holder.bind(listaContatos[position])
    }
}