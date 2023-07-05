package mx.tecnm.misantla.newapiretrofit.tiempo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import mx.tecnm.misantla.newapiretrofit.R
import mx.tecnm.misantla.newapiretrofit.databinding.ActivityMainBinding
import mx.tecnm.misantla.newapiretrofit.databinding.ActivityTiempoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TiempoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTiempoBinding
    val URLAPI = "https://samples.openweathermap.org/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTiempoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tiempoActivityRV.layoutManager = LinearLayoutManager(this)
        binding.tiempoActivityRV.adapter = null

        var retrofitTiempo = Retrofit.Builder()
            .baseUrl(URLAPI)
            .addConverterFactory(GsonConverterFactory.create()) //permite convertir el json a clase meteorologia
            .build()


        var apiTiempo = retrofitTiempo.create(ApiTiempo::class.java)
        var callTiempo = apiTiempo.getTiempo()
        callTiempo.enqueue(object : Callback<Metereologia> {
            override fun onFailure(call: Call<Metereologia>, t: Throwable?) {
                Log.e("TAG Fallo: ", t.toString())
            }

            override fun onResponse(call: Call<Metereologia>, response: Response<Metereologia>) {
                for(res in response.body()?.list!!){
                    Log.d("TAG Respuesta: ", res.main.temp)
                }
                binding.tiempoActivityRV.adapter = TiempoAdapter(response.body()!!.list)
            }

        })
    }
}
