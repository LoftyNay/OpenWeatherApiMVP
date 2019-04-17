package com.ltn.openweather.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.ltn.openweather.R
import com.ltn.openweather.model.Weather
import com.squareup.picasso.Picasso

class WeatherAdapter() :
    RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    lateinit var onCardItemClickListener: OnCardItemClickListener
    lateinit var onCardItemLongClickListener: OnCardItemLongClickListener

    constructor(onCardItemClickListener: OnCardItemClickListener) : this() {
        this.onCardItemClickListener = onCardItemClickListener
    }

    constructor(
        onCardItemClickListener: OnCardItemClickListener,
        onCardItemLongClickListener: OnCardItemLongClickListener
    ) : this() {
        this.onCardItemClickListener = onCardItemClickListener
        this.onCardItemLongClickListener = onCardItemLongClickListener
    }

    private var citiesWeatherList: MutableList<Weather> = ArrayList()

    fun addAll(weatherList: MutableList<Weather>) {
        citiesWeatherList.clear()
        citiesWeatherList.addAll(weatherList)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        citiesWeatherList.removeAt(position)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return citiesWeatherList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.cardItem.setOnClickListener { onCardItemClickListener.onItemClick(citiesWeatherList[position]) }
        holder.cardItem.setOnLongClickListener { onCardItemLongClickListener.onLongItemClick(position, citiesWeatherList[position].id) }

        holder.textCity.text = citiesWeatherList[position].name

        Picasso.get()
            .load(citiesWeatherList[position].weatherIcon)
            .fit()
            .centerCrop()
            .into(holder.imageItem);

        holder.textCity.text = "${citiesWeatherList[position].name} (${citiesWeatherList[position].country})"
        holder.textTemp.text = citiesWeatherList[position].temp.toString() + " ℃"
        holder.textTempDesc.text = citiesWeatherList[position].weatherDesc
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardItem = itemView.findViewById<MaterialCardView>(R.id.recyclerCardItem)
        val imageItem = itemView.findViewById<AppCompatImageView>(R.id.recyclerItemImage)
        val textCity = itemView.findViewById<TextView>(R.id.recyclerItemTextCity)
        val textTemp = itemView.findViewById<TextView>(R.id.recyclerItemTemp)
        val textTempDesc = itemView.findViewById<TextView>(R.id.recyclerItemTempDesc)
    }

    interface OnCardItemClickListener {
        fun onItemClick(weather: Weather)
    }

    interface OnCardItemLongClickListener {
        fun onLongItemClick(position: Int, id: Long): Boolean
    }
}