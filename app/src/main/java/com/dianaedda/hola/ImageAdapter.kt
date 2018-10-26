package com.dianaedda.hola

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

/*private val mThumbIds = arrayOf<Int>(
    R.drawable.tarjeta, R.drawable.tarjeta,
    R.drawable.tarjeta, R.drawable.tarjeta,
    R.drawable.tarjeta, R.drawable.tarjeta,
    R.drawable.tarjeta, R.drawable.tarjeta,
    R.drawable.tarjeta, R.drawable.tarjeta)*/

class ImageAdapter(private val mContext: Context, val tarjetas: Array<String>, val t:Int) : BaseAdapter() {

    override fun getCount(): Int = t

    override fun getItem(position: Int): Any? = null

    override fun getItemId(position: Int): Long = 0L

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        /*val imageView: ImageView
        if (convertView == null) {
            imageView = ImageView(mContext)
            imageView.layoutParams = ViewGroup.LayoutParams(480, 240)
            imageView.scaleType = ImageView.ScaleType.CENTER
            imageView.setPadding(8, 8, 8, 8)
        } else {
            imageView = convertView as ImageView
        }
        imageView.setImageResource(mThumbIds[position])
        return imageView*/

        val grid: View
        val inflater:LayoutInflater = LayoutInflater.from(mContext)
        if (convertView == null) {
            grid = inflater.inflate(R.layout.content_lista_tarjetas, null)
            val imageView:ImageView = grid.findViewById(R.id.grid_image)
            val textView:TextView = grid.findViewById(R.id.grid_text)
            imageView.setImageResource( R.drawable.tarjeta2)
            textView.text = tarjetas[position]
        } else {
            grid = convertView
        }
        return grid
    }
}