package com.example.roomtest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.roomtest.adapter.ContatoAdapter
import com.example.roomtest.dao.AppDataBase
import com.example.roomtest.dao.Database
import com.example.roomtest.model.Contato
class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var buttonNovoContato: ImageButton
    private lateinit var editTelefone: EditText
    private lateinit var editNome: EditText
    private lateinit var buttonDeletar: Button
    private  lateinit var buttonSalvar: Button
    private lateinit var dialog : AlertDialog
    private lateinit var  recyclerContatos: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonNovoContato = findViewById(R.id.button_novo_contato)
        buttonNovoContato.setOnClickListener(this)
        recyclerContatos = findViewById(R.id.recycler_contatos)
        exibirContatos()


    }
    override fun onClick(v: View) {
        if(v.id == R.id.button_novo_contato) {
            abrirCadastroContato()
        } else if (v.id == R.id.button_cancelar) {
            dialog.dismiss()
        } else  if (v.id == R.id.button_salvar){
            salvarContato()
            dialog.dismiss()
        } else {
            exibirContatos()
        }

    }
    private fun exibirContatos() {
//        val db = Room.databaseBuilder(this, AppDataBase::class.java,
//            "db_contato"
//        ).allowMainThreadQueries().build()
        val contadoDao = Database.getDatabase(this).contatoDao()
        val contatos = contadoDao.listarTodos()
        recyclerContatos.layoutManager = LinearLayoutManager(this)
        val adapter = ContatoAdapter()
        recyclerContatos.adapter = adapter
        adapter.carregarLista(contatos)
    }

    private fun salvarContato() {
        var contato = Contato(nomeContato = editNome.text.toString(), telefoneContato = editTelefone.text.toString())
//        val db = Room.databaseBuilder(this, AppDataBase::class.java,
//            "db_contato"
//        ).allowMainThreadQueries().build()
        val contadoDao = Database.getDatabase(this).contatoDao()
        contadoDao.salvar(contato)

        exibirContatos()
    }
    private fun abrirCadastroContato() {
        val alertDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.cadastro_contato_dialog, null)
        alertDialog.setView(view)
        editNome = view.findViewById(R.id.edit_nome)
        editTelefone = view.findViewById(R.id.edit_telefone)
        buttonSalvar = view.findViewById(R.id.button_salvar)
        buttonDeletar = view.findViewById(R.id.button_cancelar)

        //alertDialog.setCancelable(false).create().show()
        dialog = alertDialog.create()
        dialog.setCancelable(false)
        dialog.show()
        buttonDeletar.setOnClickListener(this)
        buttonSalvar.setOnClickListener(this)
    }
}