package group.asteriskint.adm.ui.activity

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.fragment.NavHostFragment
import group.asteriskint.adm.R
import group.asteriskint.adm.viewmodel.HomeActivityViewModel
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), ViewModelStoreOwner {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val viewModel : HomeActivityViewModel by viewModels()
        viewModel.checkConnection()
        viewModel.isConnected.observe(this,{ connected ->
            connection_status.visibility = View.VISIBLE
            if (connected) {
                connection_status.setBackgroundResource(R.color.colorGreen)
                connection_status.setTextColor(resources.getColor(R.color.colorWhite,resources.newTheme()))
                connection_status.text = "Connected"
                Handler().postDelayed({
                    connection_status.visibility = View.GONE
                },3000)
            }else{
                connection_status.setBackgroundResource(R.color.colorRed)
                connection_status.setTextColor(resources.getColor(R.color.colorWhite,resources.newTheme()))
                connection_status.text = "Disconnected"
            }
        })
        imageMenu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            text_title.text = destination.label
        }
    }
}