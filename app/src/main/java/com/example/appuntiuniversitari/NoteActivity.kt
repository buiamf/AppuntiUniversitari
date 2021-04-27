package com.example.appuntiuniversitari

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.example.appuntiuniversitari.adapters.RecyAdapter
import com.example.appuntiuniversitari.database.AppDatabase
import com.example.appuntiuniversitari.database.Corso
import com.example.appuntiuniversitari.database.Nota
import com.example.appuntiuniversitari.databinding.ActivityNoteBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle: Bundle? = intent.extras
        val materia: String? = bundle?.getString("materia")

        val binding = ActivityNoteBinding.inflate(layoutInflater)

        binding.titleCourse.text = "Note di $materia"
//        Log.d("TEST MATERIA: ", materia!!)
        if (materia != null) {
            GlobalScope.launch {
                val db = AppDatabase.getDatabase(this@NoteActivity)

                val corsi = db.courseDao().getAllCourses()
                val idCorso = getCorsoId(corsi, materia)

//                for (corso in corsi) {
//                    if (corso.corso_name == materia)
//                        idCorso = corso.corso_id
//                }
//                Log.d("TEST ID CORSO: ", idCorso.toString())

//                var materiaId: String? = null/*db.courseDao().getCourseIdFromName(materia)*/

//                if (idCorso != null) {
////                    Log.d("TEST MATERIA ID: ", materiaId)
//                    binding.noteCourse.setText(idCorso)
//                }
//                else{
////                    Log.d("TEST MATERIA ID: ", "materiaid nullo")
//                    binding.noteCourse.setText("materiaid nullo")
//                }

                val note = db.notesDao().getAllNotes()
                binding.noteCourse.setText(getNote(note,idCorso))
//                binding.noteCourse.setText(db.notesDao().getCourseNotes(idCorso.toString()))
            }
        }
        else {
            binding.noteCourse.setText("note non trovate")
        }

        binding.buttonClose.setOnClickListener { startActivity(Intent(this,MainActivity::class.java)) }
        binding.buttonEditSave.setOnClickListener { modifyNote(binding.buttonEditSave) }



        setContentView(binding.root)
//        setContentView(R.layout.activity_note)

    }

    private fun modifyNote(button: Button) {
        if (button.text == "Modifica") {
            button.text = "Salva"

        }
        else {
            button.text = "Modifica"

        }

    }

    private fun getCorsoId(corsi : List<Corso>, materia : String) : Int {
        for (corso in corsi) {
            if (corso.corso_name == materia)
                return corso.corso_id
        }
        return 0
    }
    private fun getNote(note : List<Nota>, idCorso: Int) : String {
        for (nota in note) {
            if (nota.corso_id == idCorso)
                return nota.corpo
        }
        return "nessun appunto trovato"
    }
}
