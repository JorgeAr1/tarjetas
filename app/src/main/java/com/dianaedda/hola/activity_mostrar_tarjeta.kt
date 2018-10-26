package com.dianaedda.hola

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_mostrar_tarjeta.*

class activity_mostrar_tarjeta : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_tarjeta)

        val bundle = intent.extras
        var cont = bundle.get("contenido").toString()
        var tit = bundle.get("titulo").toString()

        NombreTarjeta.title=tit

        mostrar(cont)

         EditarTarjeta.setOnClickListener {
             var i = Intent(this,activity_modificar_tarjeta::class.java)
             i.putExtra("contenido",""+cont)
             i.putExtra("titulo",""+tit)
             startActivity(i)
             finish()
         }

        BorrarTarjeta.setOnClickListener {
            Borrar(tit)
        }
    }

    fun Borrar(tit:String){
        val builder = AlertDialog.Builder(this)

            builder.setTitle("Eliminar Tarjeta")
            builder.setMessage("¿Está seguro que desea eliminar de forma permanente esta tarjeta?")

            builder.setPositiveButton("SI") { dialog, which ->
            deleteFile("$tit")
            Toast.makeText(this, "Tarjeta eliminada con éxito", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
            finish()
        }

        builder.setNegativeButton("NO") { dialog, which ->
            dialog.dismiss()
        }
        val alert = builder.create()
        alert.show()
    }

    fun mostrar(cont:String){
        var partes = cont.split(":")
        Nombre.text = partes[0]
        Titulo.text = partes[1]
        Organizacion.text = partes[2]
        Web.text = partes[3]
        Telefono.text = partes[5]
        Tipo.text = partes[4]
        Email.text = partes[6]
    }
}

