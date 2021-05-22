package com.akshatsahijpal.covidone.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "covid_table")
data class CovidData(
    var Resource: String = "",
    var Store: String = "",
    var Number: String = "",
    var Address: String = "",
    var Details: String = "",
    var StateCity: String = ""
){
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}