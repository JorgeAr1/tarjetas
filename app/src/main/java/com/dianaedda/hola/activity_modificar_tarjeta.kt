package com.dianaedda.hola

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.text.Editable
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_modificar_tarjeta.*
import java.io.IOException
import java.io.OutputStreamWriter

class activity_modificar_tarjeta : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_tarjeta)

        val bundle = intent.extras
        var cont = bundle.get("contenido").toString()
        var tit = bundle.get("titulo").toString()

        llenar(cont)

        ModificarTarjeta.setOnClickListener {
            Moficar(tit)
        }

        CancelarM.setOnClickListener {
            Cancelar()
        }
    }

    @SuppressLint("ResourceAsColor")
    fun Moficar(tit:String) {
        if (NombreM.text.toString() == "" || TelefonoM.text.toString() == "") {
            Toast.makeText(this, "Faltan campos obligatorios", Toast.LENGTH_SHORT).show()
        } else {
            Crear(NombreM.text.toString(),TituloM.text.toString(),TelefonoM.text.toString(),spinner.selectedItem.toString(), OrganizacionM.text.toString(), EmailM.text.toString(),WebM.text.toString(), tit)
            finish()
        }
    }

    fun Cancelar() {
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Cancelar")
        builder.setMessage("¿Está seguro que desea cancelar las modificaciones?")

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

    fun llenar(cont : String){
        var partes = cont.split(":")
        NombreM.hint = NombreM.hint.toString()+" : "+partes[0]
        TituloM.hint = TituloM.hint.toString()+" : "+partes[1]
        OrganizacionM.hint = OrganizacionM.hint.toString()+" : "+partes[2]
        WebM.hint = WebM.hint.toString()+" : "+partes[3]
        TelefonoM.hint = TelefonoM.hint.toString()+" : "+partes[5]
        EmailM.hint = EmailM.hint.toString()+" : "+partes[0]
        /*Toast.makeText(this, ""+partes.size, Toast.LENGTH_SHORT).show()
        NombreM.text = partes[0] as Editable
        TituloM.text = partes[1] as Editable
        OrganizacionM.text = partes[2] as Editable
        WebM.text = partes[3] as Editable
        TelefonoM.text = partes[5] as Editable
        EmailM.text = partes[6] as Editable*/
    }

    fun Crear(nombre:String,titulo:String,telefono:String,tipo:String,org:String,correo:String,pagina:String, tit:String){
        val datos = "$nombre:$titulo:$org:$pagina:$tipo:$telefono:$correo"
        try {
            deleteFile("$tit")
            val archivo = OutputStreamWriter(openFileOutput(""+tit, Activity.MODE_APPEND))
            archivo.write(datos)
            archivo.flush()
            archivo.close()
            Toast.makeText(this, "Tarjeta modificada con éxito", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            Toast.makeText(this, "Error al modificar la tarjeta", Toast.LENGTH_SHORT).show()
        }
    }
}
