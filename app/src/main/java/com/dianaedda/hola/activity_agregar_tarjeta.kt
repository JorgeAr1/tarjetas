package com.dianaedda.hola

import android.annotation.SuppressLint
import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_agregar_tarjeta.*
import android.support.v7.app.AlertDialog
import java.io.IOException
import java.io.OutputStreamWriter


class activity_agregar_tarjeta : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_tarjeta)

        val bundle = intent.extras
        var tit = bundle.get("titulo").toString()
        Toast.makeText(this, ""+tit, Toast.LENGTH_SHORT).show()

        AgregarTarjeta.setOnClickListener {
            Agregar(tit)
        }

        Cancelar.setOnClickListener{
            Cancelar()
        }

    }

    @SuppressLint("ResourceAsColor")
    fun Agregar(tit:String) {
        if (Nombre.text.toString() == "" || Telefono.text.toString() == "") {
            Toast.makeText(this, "Faltan campos obligatorios", Toast.LENGTH_SHORT).show()
        } else {
            Crear(Nombre.text.toString(),Titulo.text.toString(),Telefono.text.toString(),spinner.selectedItem.toString(), Organizacion.text.toString(), Email.text.toString(),Web.text.toString(), tit)
            finish()
        }
    }

    fun Cancelar() {
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Cancelar")
        builder.setMessage("¿Está seguro que desea cancelar el registro?")

        builder.setPositiveButton("SI") { dialog, which ->
            dialog.dismiss()
            finish()
        }

        builder.setNegativeButton("NO") { dialog, which ->
            dialog.dismiss()
        }
        val alert = builder.create()
        alert.show()
    }

    fun Crear(nombre:String,titulo:String,telefono:String,tipo:String,org:String,correo:String,pagina:String, tit:String){
        val datos = "$nombre:$titulo:$org:$pagina:$tipo:$telefono:$correo"
        try {
            val archivo = OutputStreamWriter(openFileOutput(""+tit, Activity.MODE_APPEND))
            archivo.write(datos)
            archivo.flush()
            archivo.close()
            Toast.makeText(this, "Tarjeta agregada con éxito", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            Toast.makeText(this, "Error al generar la tarjeta", Toast.LENGTH_SHORT).show()
        }
    }
}