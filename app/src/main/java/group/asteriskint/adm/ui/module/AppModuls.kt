package com.example.firebaseauthmvp.module

import com.example.firebaseauthmvp.repository.FirebaseViewModel
import com.example.firebaseauthmvp.repository.implementation.UserRepositoryImpl
import org.koin.dsl.module

val firebaseViewModelModule = module {
    single { FirebaseViewModel(UserRepositoryImpl()) }
}
