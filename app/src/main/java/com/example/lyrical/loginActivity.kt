    package com.example.lyrical

    import android.content.Intent
    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import com.google.firebase.auth.FirebaseAuth
    import com.google.firebase.auth.ktx.auth
    import com.google.firebase.ktx.Firebase
    import android.widget.Toast
    import android.widget.Button
    import android.widget.EditText
    import android.widget.TextView
    import androidx.appcompat.app.AlertDialog
    import java.security.AccessController.getContext


    //A toast is a view containing a quick little message for the user



    lateinit var email: EditText
    lateinit var passwd: EditText
    private lateinit var forgotb: Button
    private lateinit var loginbutton: Button


    lateinit var auth: FirebaseAuth
    class loginActivity: AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.loginpage)
            // reads in data from login xml by its id
            email = findViewById(R.id.email)
            passwd = findViewById(R.id.password)
            // connection to firebase
            auth = Firebase.auth


            forgotb = findViewById(R.id.forgotp)
            forgotb.setOnClickListener {
                val i = Intent(this, reset::class.java)
                startActivity(i)
            }

            // on click goes to login function
            loginbutton = findViewById(R.id.submitbutton)
            loginbutton.setOnClickListener {
                login()
            }



        }


        private fun login() {


            // var sampleContext=loginActivity.getContext()
            // converts user input to string
            val e = email.text.toString()
            val password = passwd.text.toString()

            if (e.isEmpty()) {
                email.setError("Email can't be empty")
                return


            } else if (password.isBlank()) {
                passwd.setError("Password can't be empty")
                return
            }

            auth.signInWithEmailAndPassword(e, password).addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(applicationContext, "Login Successful", Toast.LENGTH_SHORT)
                        .show()
                    // if login is successful, continues on to app
                    val i = Intent(this, guestActivity::class.java)
                    i.putExtra("Email",e)
                    startActivity(i)
                } else {

                    email.setError("Email or Password incorrect")
                    passwd.setError("Email or Password incorrect")
                }
            }
        }
    }







