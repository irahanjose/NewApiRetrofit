package mx.tecnm.misantla.newapiretrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.tecnm.misantla.newapiretrofit.databinding.ActivityMainBinding
import mx.tecnm.misantla.newapiretrofit.tiempo.TiempoActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainActivityTiempo.setOnClickListener {
            val intent = Intent(this, TiempoActivity::class.java)
            startActivity(intent)
        }
    }
}

/*

Array []
"list"
     "dt"
     "main"
        "temp"
        "humedity"
 */
