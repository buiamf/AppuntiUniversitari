package com.example.appuntiuniversitari

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
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

//        binding.titleCourse.text = "Note di $materia"
        setTitle("Note di $materia")
//        Log.d("TEST MATERIA: ", materia!!)
        if (materia != null) {

            GlobalScope.launch {
                val db = AppDatabase.getDatabase(this@NoteActivity)



                /*
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
*/

                val corsi = db.courseDao().getAllCourses()
                val idCorso = getCorsoId(corsi, materia)
                val note = db.notesDao().getAllNotes()
                val notaMateria = getNote(note,idCorso)

//                binding.noteCourse.setText(db.notesDao().getCourseNotes(idCorso.toString()))

                if (notaMateria != null) {
                    binding.buttonEditSave.setOnClickListener {
                        modifyNote(
                            binding.buttonEditSave,
                            binding.noteCourse.text.toString(),
                            notaMateria
                        )
                    }
                    binding.noteCourse.setText(notaMateria.corpo)
                }

                // editText listener
                binding.noteCourse.addTextChangedListener(object: TextWatcher {
                    override fun afterTextChanged(s: Editable?) {
                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        binding.buttonEditSave.text = "Salva Modifiche!"
                    }
                })


            }
        }
        else {
            binding.noteCourse.setText("note non trovate")
        }

        binding.buttonClose.setOnClickListener { startActivity(Intent(this,MainActivity::class.java)) }
//        binding.buttonEditSave.setOnClickListener { modifyNote(binding.buttonEditSave, binding.noteCourse, notaMateria) }
        binding.minimizeText.setOnClickListener {
//            Log.d("TEST TEXT SIZE before",binding.noteCourse.textSize.toString() )
            val px = binding.noteCourse.textSize
            if (px > 42) {
                val sp = px / this.resources.displayMetrics.scaledDensity
//            Log.d("TEST TEXT SIZE px",px.toString() )
//            Log.d("TEST TEXT SIZE sp",sp.toString() )
                binding.noteCourse.setTextSize(TypedValue.COMPLEX_UNIT_SP, (sp - 1.00F))
            }
//            Log.d("TEST TEXT SIZE after",binding.noteCourse.textSize.toString() )
            }

        binding.maximizeText.setOnClickListener {
            val px = binding.noteCourse.textSize
            if (px < 132) {
                val sp = px / this.resources.displayMetrics.scaledDensity
                binding.noteCourse.setTextSize(TypedValue.COMPLEX_UNIT_SP, (sp + 1.00F))
            }
//            Log.d("TEST TEXT SIZE after",binding.noteCourse.textSize.toString() )
        }



        setContentView(binding.root)
//        setContentView(R.layout.activity_note)

    }

    private fun modifyNote(button: Button, corpo : String, nota: Nota) {

        /*
//        val db = AppDatabase.getDatabase(this@NoteActivity)
//        val notaModificata = nota
//        notaModificata.corpo = corpo.toString()
//        db.notesDao().updateNotes(notaModificata)
*/

        if (button.text != "Salva") {
            button.text = "Salva"

            GlobalScope.launch {
                val db = AppDatabase.getDatabase(this@NoteActivity)
                val notaModificata = nota
                notaModificata.corpo = corpo
                db.notesDao().updateNotes(notaModificata)
            }
            Toast.makeText(this, "Note Salvate!", Toast.LENGTH_SHORT).show()
        }


    }

    private fun getCorsoId(corsi : List<Corso>, materia : String) : Int {
        for (corso in corsi) {
            if (corso.corso_name == materia)
                return corso.corso_id
        }
        return 0
    }

    private fun getNote(note : List<Nota>, idCorso: Int) : Nota? {
        for (nota in note) {
            if (nota.corso_id == idCorso)
                return nota
        }
        return null
    }

    /*
//    private fun getNoteBody(note : List<Nota>, idCorso: Int) : String {
//        for (nota in note) {
//            if (nota.corso_id == idCorso)
//                return nota.corpo
//        }
//        return "nessun appunto trovato"
//    }
*/
}
