package com.akshatsahijpal.covidone.db.remote

import android.util.Log
import com.akshatsahijpal.covidone.data.CovidData
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class FirebaseUpload @Inject constructor(private var db: FirebaseFirestore) {
    fun upload(user: CovidData) {
        GlobalScope.launch{
            db.collection("covid-store")
                .add(user)
                .addOnSuccessListener { documentReference ->
                    Log.d(
                        "result from db",
                        "DocumentSnapshot added with ID: + ${documentReference.id}"
                    )
                }
                .addOnFailureListener { e ->
                    Log.d("result from db", "Error adding document, $e")
                }
        }
    }
}