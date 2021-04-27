package com.example.appuntiuniversitari

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appuntiuniversitari.adapters.RecyAdapter
import com.example.appuntiuniversitari.database.AppDatabase
import com.example.appuntiuniversitari.databinding.ActivityCoursesBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CoursesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle: Bundle? = intent.extras
        val giornoMese: String? = bundle?.getString("giorno_della_settimana")
        var mese = giornoMese?.substringAfter('/')
        var giorno = giornoMese?.substringBefore('/')

        if (mese?.length!! < 2)
            mese = "0$mese"
        if (giorno?.length!! < 2)
            giorno = "0$giorno"
        Log.d("TEST GIORNO E MESE","giorno: $giorno, mese: $mese")
        var corsi: ArrayList<String>? = null


        val binding = ActivityCoursesBinding.inflate(layoutInflater)
        binding.lezioniGiornaliere.text = "Lezioni giornaliere ($giornoMese)"

        binding.recyNotes.setHasFixedSize(true)
        binding.recyNotes.layoutManager = LinearLayoutManager(this)

        GlobalScope.launch {
            val db = AppDatabase.getDatabase(this@CoursesActivity)

            val idCorsi = db.calendarDao().getDailyCoursesIds("2021/$mese/$giorno")
            Log.d("TEST RECYCLER VIEW","${idCorsi.size}")

            if (idCorsi.size != 0) {
                for (corsoId in idCorsi) {
                    corsi?.add(db.courseDao().getDailyCourses(corsoId.toString()))
                }

                Log.d("TEST RECYCLER VIEW","${corsi?.get(0)}")
                binding.recyNotes.adapter = RecyAdapter(corsi!!,this@CoursesActivity)
            }
            else
                binding.recyNotes.adapter = RecyAdapter(arrayListOf("NESSUNA LEZIONE PER LA GIORNATA ODIERNA"),this@CoursesActivity)

        }


        setContentView(binding.root)
//        setContentView(R.layout.activity_courses)
    }
}