package com.dianaedda.hola

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v7.app.AlertDialog
import android.widget.AdapterView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_lista_tarjetas.*
import android.widget.GridView
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


class ListaTarjetas : AppCompatActivity() {

    val titulos: Array<String> = arrayOf("1.vcf", "2.vcf", "3.vcf","4.vcf","5.vcf","6.vcf","7.vcf","8.vcf","9.vcf","10.vcf")
    val str = arrayOfNulls<String>(10)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_tarjetas)

        actualizar(titulos)

        AgregarTarjeta.setOnClickListener {
            var i = Intent(this, activity_agregar_tarjeta::class.java)
            i.putExtra("titulo",titulos[leer(titulos)])
            startActivity(i)
        }

        BorrarTarjetas.setOnClickListener {
            Borrar(titulos)
        }
        CompartirApp.setOnClickListener {
            leer(titulos)
        }
    }

    override fun onStart() {
        super.onStart()
        actualizar(titulos)
    }

    fun Borrar(titulos:Array<String>){
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Eliminar Tarjetas")
        builder.setMessage("¿Está seguro que desea eliminar de forma permanente todas las tarjetas?")

        builder.setPositiveButton("SI") { dialog, which ->
            var i=0
            while (i<titulos.size){
                try {
                    var n=titulos[i].toString()
                    deleteFile("$n")
                } catch (e: IOException) {
                    Toast.makeText(this, "Error al cargar tajetas", Toast.LENGTH_SHORT).show()
                }
                i += 1
            }
            Toast.makeText(this, "Tarjetas eliminadas con éxito", Toast.LENGTH_SHORT).show()
            actualizar(titulos)
            dialog.dismiss()
        }

        builder.setNegativeButton("NO") { dialog, which ->
            dialog.dismiss()
        }
        val alert = builder.create()
        alert.show()
    }

    fun leer(titulos:Array<String>):Int{
        var i=0
        var todo : String
        while (i<titulos.size){
            todo = ""
            try {
                var n=titulos[i].toString()
                var archivo = InputStreamReader(openFileInput("$n"))
                var br = BufferedReader(archivo)
                var linea = br.readLine()
                while (linea != null) {
                    todo += (linea + "\n")
                    linea = br.readLine()
                }
                br.close()
                archivo.close()
                str.set(i,todo)
            } catch (e: IOException) {
                //Toast.makeText(this, "Error al cargar tajetas", Toast.LENGTH_SHORT).show()
                return i
            }
            i += 1
        }
        return i
    }

    fun actualizar(titulos: Array<String>){
        var i=0
        while (i<str.size){
            str[i]=null
            i++
        }
        leer(titulos)
        i=0
        var t=0
        while(i<str.size){
            if(str[i]!=null){
                t++
            }
            i++
        }

        val gridview: GridView = findViewById(R.id.grid)
        gridview.adapter = ImageAdapter(this, titulos, t)

        gridview.onItemClickListener = AdapterView.OnItemClickListener { parent, v, position, id ->
            //Toast.makeText(this, "$position", Toast.LENGTH_SHORT).show()
            if (str[position]!=null){
                var i = Intent(this,activity_mostrar_tarjeta::class.java)
                i.putExtra("contenido",""+str[position].toString())
                i.putExtra("titulo",""+titulos[position])
                startActivity(i)
            }
        }
    }
}

