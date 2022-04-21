package com.example.lyrical
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class h : AppCompatActivity() {
    private lateinit var hist: ListView
    // creating variables for our recycler view,
    // array list, adapter, firebase firestore
    // and our progress bar.
    //private var courseRV: RecyclerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(com.example.lyrical.R.layout.history)
        super.onCreate(savedInstanceState)

        val user = Firebase.auth.currentUser
        val db = Firebase.firestore
        var uc: String
        hist = findViewById<ListView>(com.example.lyrical.R.id.LVHistory)
        if (user != null) {
            uc = user.uid


            val docRef = db.collection("users").document(uc)
            docRef.get()
                .addOnSuccessListener { document ->
                    if (document != null) {

                        var song = document.get("Song Info")

                        var a=song.toString()
                       var  aa = a.substringAfter("[" ).substringBefore("]")

                        var arr = aa.split(",")


                        var array:Array<String> =arr.toTypedArray()



                        var adapt=ArrayAdapter<String>(this,R.layout.historytext,array)
                        hist.setAdapter(adapt)








                        //val result: StringBuffer = StringBuffer()
                        //result.append(document.data)








                        //Log.d(TAG, "DocumentSnapshot data: ${document.data}")

                    } else {
                        Log.d(TAG, "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "get failed with ", exception)
                }



            // print("hi" + currentdoc)




                }

        }
    }



