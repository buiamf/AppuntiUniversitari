package com.example.appuntiuniversitari

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appuntiuniversitari.databinding.ActivityNoteBinding

class NoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityNoteBinding.inflate(layoutInflater)









        setContentView(binding.root)
//        setContentView(R.layout.activity_note)
    }
}