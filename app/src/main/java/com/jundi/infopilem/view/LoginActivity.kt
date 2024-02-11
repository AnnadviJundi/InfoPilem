package com.jundi.infopilem.view

import android.app.Activity
import com.jundi.infopilem.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.jundi.infopilem.databinding.ActivityLoginBinding
import com.jundi.infopilem.lokal.UserPreferences
import com.jundi.infopilem.movieList.data.remote.respond.login.ResponseLogin

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    // google login
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Configure Google Sign In
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        // Initialize Firebase Auth
        auth = Firebase.auth


        binding.btnLogin.setOnClickListener{
            submitLogin()
        }

        binding.btnGoogle.setOnClickListener{
            signInGoogle()
        }
    }

    private fun signInGoogle() {
        val signIntent = googleSignInClient.signInIntent
        resultLauncher.launch(signIntent)
    }

    private var resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Success", "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Error", "signInWithCredential:failure", task.exception)
                    updateUI(null)
                }
            }
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null){
            val user = ResponseLogin()
            user.username = currentUser.displayName
            user.password = currentUser.email

            UserPreferences(this).setUser(user)

            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }
    }



    private fun submitLogin() {
        val username = binding.valueUsername
        val password = binding.valuePassword

        if (username != null && password != null) {
            val database = UserPreferences (this)
            val user = ResponseLogin()
            user.username = username.text.toString()
            user.password = password.text.toString()

            database.setUser(user)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}