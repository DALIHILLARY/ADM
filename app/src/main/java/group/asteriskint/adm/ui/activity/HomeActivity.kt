package group.asteriskint.adm.ui.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
//import com.google.firebase.ktx.Firebase
import group.asteriskint.adm.R
import group.asteriskint.adm.auth.LoginActivity
import group.asteriskint.adm.viewmodel.HomeActivityViewModel
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), ViewModelStoreOwner {
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // implement logout
//        fun logout(){
//            FirebaseAuth.getInstance().signOut()
//            Intent intent = new Intent(getApplicationContext(), LoginActivity.class)
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            startActivity(intent);
////        }
//
//        private fun logout() {
//            val intent = Intent(getApplicationContext(), LoginActivity.class)
//            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
//            startActivity(intent)
//        }


        val viewModel: HomeActivityViewModel by viewModels()
        viewModel.checkConnection()

        viewModel.isConnected.observe(this) { connected ->
            connection_status.visibility = View.VISIBLE

            if (connected) {
                connection_status.setBackgroundResource(R.color.colorGreen)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    connection_status.setTextColor(
                        resources.getColor(
                            R.color.colorWhite,
                            resources.newTheme()
                        )
                    )
                else
                    connection_status.setTextColor(resources.getColor(R.color.colorWhite))
                connection_status.text = "Connected"
                Handler().postDelayed({
                    connection_status.visibility = View.GONE
                }, 3000)
            } else {
                connection_status.setBackgroundResource(R.color.colorRed)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    connection_status.setTextColor(
                        resources.getColor(
                            R.color.colorWhite,
                            resources.newTheme()
                        )
                    )
                else
                    connection_status.setTextColor(resources.getColor(R.color.colorWhite))
                connection_status.text = "Disconnected"
            }
        }
        imageMenu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {

                R.id.drawer_cart -> {
                    navController.navigate(R.id.cartFragment)
                    false
                }
                R.id.drawer_category -> {
                    navController.navigate(R.id.homeFragment)
                    false
                }
//                R.id.drawer_logout -> {
////                    logout()
//                    false
//                }
                else -> {
                    //DO NOTHING
                    false
                }
            }
        }
//        search.setOnClickListener {
//            search.visibility = View.GONE
//            search_view.visibility = View.VISIBLE
//            text_title.visibility = View.GONE
//        }
//        search_view.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                viewModel.searchQuery.value = newText
//                return false
//            }
//
//        })

        navController.addOnDestinationChangedListener { _, destination, _ ->
//            text_title.text = destination.label
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            search.visibility = View.GONE
            search_view.visibility = View.GONE
            when (destination.label.toString()) {
                "home_fragment" -> {
//                    search.visibility = View.VISIBLE
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR

                }
                "product_list_fragment" -> {
//                    search.visibility = View.VISIBLE
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR
                }
                "item_show_fragment" -> {

                }
                "cart_fragment" -> {

                }
                "payment_fragment" -> {

                }
                "fragment_after_payment" -> {

                }
                "login_fragment" -> {

                }
                "register_fragment" -> {

                }
                "payment_fail_fragment" -> {

                }
                else -> {

                }
            }
        }

    }
}
