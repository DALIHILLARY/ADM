package group.asteriskint.adm.auth.module

import group.asteriskint.adm.auth.repository.FirebaseViewModel
import group.asteriskint.adm.auth.repository.implementation.UserRepositoryImpl
import org.koin.dsl.module

val firebaseViewModelModule = module {
    single { FirebaseViewModel(UserRepositoryImpl()) }
}
