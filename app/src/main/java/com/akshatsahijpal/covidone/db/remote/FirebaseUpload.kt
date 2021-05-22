package com.akshatsahijpal.covidone.db.remote

import android.util.Log
import com.akshatsahijpal.covidone.data.CovidData
import com.akshatsahijpal.covidone.util.Constants
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class FirebaseUpload @Inject constructor(private var db: FirebaseFirestore) {
    private var num: Int? = null
    fun upload(user: CovidData, dropDownList: Array<String>) {
        for (i in 0..2) {
            if (user.Resource.contentEquals(dropDownList[i])) {
                num = i
            }
        }

        GlobalScope.launch {
            db.collection(
                when (num) {
                    0 -> Constants.covidMedicinePath
                    1 -> Constants.plasmaDonorPath
                    else -> Constants.oxygenPath
                }
            )
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