package com.example.appuntiuniversitari.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.appuntiuniversitari.NoteActivity
import com.example.appuntiuniversitari.R
import com.example.appuntiuniversitari.databinding.LayoutRawRecyclerBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// , private val context: Context?
//class RecyAdapter(private val titles : Array<String> , private val context: Context?) : RecyclerView.Adapter<RecyAdapter.ViewHolder>() {
//
//    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
//        val titolo = view.findViewById<TextView>(R.id.course_title)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_calendar, parent, false))
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.titolo.text = titles[position]
//    }
//
//    override fun getItemCount() = titles.size
//
//}

class RecyAdapter(private val titles : ArrayList<String> , private val context: Context?) : RecyclerView.Adapter<RecyAdapter.Courses>() {

    inner class Courses(val binding : LayoutRawRecyclerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Courses {
        return Courses(LayoutRawRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Courses, position: Int) {
        holder.binding.courseTitle.text = titles[position]

        if (holder.binding.courseTitle.text != "NESSUNA LEZIONE PER LA GIORNATA ODIERNA") {
            holder.binding.courseTitle.setOnClickListener {
//            holder.binding.courseTitle.text = "mi hai premuto"
                val intent = Intent(context, NoteActivity::class.java)
                intent.putExtra("materia", holder.binding.courseTitle.text.toString())
                context?.startActivity(intent)
            }
        }
    }

    override fun getItemCount() = titles.size

}