package com.example.roomtest.holder

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.roomtest.model.Contato
import kotlinx.android.synthetic.main.contato_recycler_layout.view.*

class ContatoHolder(view: View) : RecyclerView.ViewHolder(view) {

    private var textId = view.text_id
    private var textNome = view.text_nome
    private var textTelefone = view.text_telefone

    fun bind(contato: Contato){
        textId.text = contato.id.toString()
        textNome.text = contato.nomeContato
        textTelefone.text = contato.telefoneContato
        

        textNome.setOnClickListener{
            Toast.makeText(itemView.context, textNome.text, Toast.LENGTH_SHORT).show()
        }
    }

}


