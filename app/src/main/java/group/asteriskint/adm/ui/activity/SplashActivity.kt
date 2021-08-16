package group.asteriskint.adm.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import group.asteriskint.adm.R
import group.asteriskint.adm.viewmodel.SplashActivityViewModel

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val viewModel : SplashActivityViewModel by viewModels()
        viewModel.checkConnection()
        viewModel.isConnected.observe(this, { connected ->
            if(connected) {
                Handler().postDelayed({
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                },4000)

            }
        })
    }
}