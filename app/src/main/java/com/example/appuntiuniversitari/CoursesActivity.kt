package com.example.appuntiuniversitari

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appuntiuniversitari.adapters.RecyAdapter
import com.example.appuntiuniversitari.database.AppDatabase
import com.example.appuntiuniversitari.database.Calendario
import com.example.appuntiuniversitari.database.Corso
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
//        Log.d("TEST GIORNO E MESE","giorno: $giorno, mese: $mese")
//        var corsi: ArrayList<String>? = null


        val binding = ActivityCoursesBinding.inflate(layoutInflater)
        binding.lezioniGiornaliere.text = "Lezioni giornaliere ($giornoMese)"
//        binding.lezioniGiornaliere.font
        binding.recyNotes.setHasFixedSize(true)
        binding.recyNotes.layoutManager = LinearLayoutManager(this)

        GlobalScope.launch {
            val db = AppDatabase.getDatabase(this@CoursesActivity)
            val calendario = db.calendarDao().getCalendar()
            val corsi = db.courseDao().getAllCourses()

//            Log.d("TEST CALENDARIO","${calendario?.size}")
//            Log.d("TEST CORSI","${corsi?.size}")

            /*
            val idCorsi : ArrayList<Int>? = null
            for (giorno in calendario) {
                if (giorno.data_semestre == "2021/$mese/$giorno")
                    idCorsi?.add(giorno.corso_id)
            }
            */

            val idCorsi = getCoursesIdFromDay(calendario,"2021/$mese/$giorno")

//            Log.d("TEST ID CORSI","${idCorsi?.size}")

            if (idCorsi.size != 0) {

                /*
                val corsiGiornalieri : ArrayList<String>? = null
                for (corsoId in idCorsi) {
                    for (corso in corsi) {
                        if (corso.corso_id == corsoId)
                            corsiGiornalieri?.add(corso.corso_name)
                    }
                }
                */

                val corsiGiornalieri = getCoursesNamesFromId(idCorsi,corsi)

//                Log.d("TEST CORSI GIORNAl","${corsiGiornalieri?.size}")

                if (corsiGiornalieri.size != 0)
                    binding.recyNotes.adapter = RecyAdapter(corsiGiornalieri,this@CoursesActivity)
            }
            else
                binding.recyNotes.adapter = RecyAdapter(arrayListOf("NESSUNA LEZIONE PER LA GIORNATA ODIERNA"),this@CoursesActivity)

        }


        setContentView(binding.root)
//        setContentView(R.layout.activity_courses)
    }

    private fun getCoursesIdFromDay(calendario: List<Calendario>, giornoCorrente : String) : ArrayList<Int> {
//        val idCorsi : ArrayList<Int>? = null
        val idCorsi = ArrayList<Int>()
        for (giorno in calendario) {
//            Log.d("TEST ID FUN","data semestre: ${giorno.data_semestre}, giorno corrente : $giornoCorrente")
            if (giorno.data_semestre == giornoCorrente) {
                idCorsi.add(giorno.corso_id)
//                Log.d("TEST MATCH FUN","ID match: ${giorno.corso_id}, size of idCorsi: ${idCorsi?.size}")
            }
        }
//        Log.d("TEST ID CORSI IN FUN","${idCorsi?.size}")
        return idCorsi
    }

    private fun getCoursesNamesFromId(idCorsi : ArrayList<Int>, corsi: List<Corso>) : ArrayList<String> {
//        val corsiGiornalieri : ArrayList<String>? = null
        val corsiGiornalieri = ArrayList<String>()
        for (corsoId in idCorsi) {
            for (corso in corsi) {
                if (corso.corso_id == corsoId)
                    corsiGiornalieri.add(corso.corso_name)
            }
        }
        return corsiGiornalieri
    }


}