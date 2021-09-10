package group.asteriskint.adm.auth.repository

import android.content.Context
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import group.asteriskint.adm.auth.model.User
import group.asteriskint.adm.auth.utils.Result

interface UserRepository {
    suspend fun logInUserFromAuthWithEmailAndPassword(
        email: String,
        password: String
    ): Result<FirebaseUser?>

    suspend fun getUserFromFirestore(userId: String): Result<User>?

    suspend fun registerUserFromAuthWithEmailAndPassword(
        email: String,
        password: String,
        context: Context
    ): Result<FirebaseUser?>

    suspend fun createUserInFirestore(user: User): Result<Void?>

    suspend fun sendPasswordResetEmail(
        email: String
    ): Result<Void?>

    suspend fun checkUserLoggedIn(): FirebaseUser?
    suspend fun logOutUser()

    suspend fun signInWithCredential(
        authCredential: AuthCredential
    ): Result<AuthResult?>
}