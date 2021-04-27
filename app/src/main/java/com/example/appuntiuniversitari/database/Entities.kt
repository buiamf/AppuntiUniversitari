package com.example.appuntiuniversitari.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import org.w3c.dom.Text

@Entity/*(
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Corso::class,
            parentColumns = arrayOf("corso_id"),
            childColumns = arrayOf("corso_id"),
            onDelete = CASCADE
        )
    )
)*/
data class Calendario(
    @PrimaryKey/*(autoGenerate = true)*/ val calendario_id: Int,
    val corso_id: Int,
    val data_semestre: String
)

@Entity
data class Corso(
    @PrimaryKey/*(autoGenerate = true)*/ val corso_id: Int,
    val corso_name: String,
    val docente: String
)

@Entity
data class Nota(
    @PrimaryKey/*(autoGenerate = true)*/ val nota_id: Int,
    val corso_id: Int,
    val corpo: String
)