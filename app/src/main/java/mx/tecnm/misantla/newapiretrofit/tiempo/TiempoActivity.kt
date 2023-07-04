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


        var ApiTiempo = retrofitTiempo.create(ApiTiempo::class.java)
        var callTiempo = ApiTiempo.getTiempo()

        // empieza a bajarse los datos
        callTiempo.enqueue(object :Callback<Metereologia>{
                  // repuesta de toda la cadena JSON
            override fun onResponse(call: Call<Metereologia>, response: Response<Metereologia>) {
                for (res in response.body()?.list!!){
                    Log.e("TAG Respuesta Temp: ", res.main.temp)
                    Log.e("TAG Respuesta Humedad",res.main.humidity)
                }
            }
// en caso de que haya ocurrido un error
            override fun onFailure(call: Call<Metereologia>, t: Throwable) {
                Log.e("TAG Fallo: ", t.toString())
            }


        })

    }
}