package group.asteriskint.adm.auth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseUser
import group.asteriskint.adm.R
import group.asteriskint.adm.auth.repository.FirebaseViewModel
import group.asteriskint.adm.ui.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class SplashActivity : AppCompatActivity() {
    private val TAG = "SplashScreen"
    private val firebaseViewModel: FirebaseViewModel by inject()
    private var currentFirebaseUser: FirebaseUser? = null
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // coroutine function to check if the user is logged in...
        coroutineScope.launch {
            delay(4000)
            currentFirebaseUser = firebaseViewModel.checkUserLoggedIn()

            if (currentFirebaseUser == null) {
                // startActivity(RegisterActivity())  // Show Register Screen after Splash screen
                startActivity(LoginActivity())   // Display Login Screen after Splash screen
            } else {
                currentFirebaseUser?.let { firebaseUser ->
                    Log.i(TAG, firebaseUser.uid)

                    // GEt id of the user now since the user is logged in...
                    firebaseViewModel.getUserFromFirestore(
                        firebaseUser.uid,
                        this@SplashActivity
                    )
                    startActivity(MainActivity())  // If user is logged in, Display Main Activity
                }
            }
        }
    }

    private fun startActivity(activity: Activity) {
        val intent = Intent(this@SplashActivity, activity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}
