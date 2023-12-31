package mx.tecnm.misantla.newapiretrofit.tiempo

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.*
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import mx.tecnm.misantla.newapiretrofit.R
import mx.tecnm.misantla.newapiretrofit.databinding.ListItemTiempoBinding


class TiempoAdapter (val metereologia: ArrayList<Tiempo>): RecyclerView.Adapter<TiempoAdapter.TiempoViewHolder>() {
    override fun onBindViewHolder(holder: TiempoViewHolder, position: Int) {
        var itemTiempo = metereologia[position]
        holder.bindTiempo(itemTiempo)
    }

    override fun getItemCount(): Int {
        return metereologia.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TiempoViewHolder {
        var layoutInflate = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_tiempo, parent, false)

        return TiempoAdapter.TiempoViewHolder(layoutInflate)
    }


    class TiempoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){


        fun bindTiempo(tiempo: Tiempo){
            var date = Date(tiempo.dt.toLong()*1000)
            var sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            var tiempo1 = sdf.format(date)
            Log.d ("TAG TiempoAdapter: ", tiempo1)

            val fecha = itemView.findViewById<TextView>(R.id.litFecha)
            val humedad = itemView.findViewById<TextView>(R.id.litHumedad)
            val temperatura = itemView.findViewById<TextView>(R.id.litTemperatura)

            fecha.text = tiempo1
            humedad.text = tiempo.main.humidity
            temperatura.text = tiempo.main.temp



        }
    }
}