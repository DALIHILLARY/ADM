package group.asteriskint.adm

import android.app.Application
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import group.asteriskint.adm.auth.module.firebaseViewModelModule
import group.asteriskint.adm.auth.module.firebaseViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            modules(listOf(firebaseViewModelModule))
        }
    }
}
