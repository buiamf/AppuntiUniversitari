package com.example.appuntiuniversitari.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appuntiuniversitari.R
import com.example.appuntiuniversitari.databinding.LayoutRawRecyclerBinding

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

class RecyAdapter(private val titles : Array<String> , private val context: Context?) : RecyclerView.Adapter<RecyAdapter.Courses>() {

    inner class Courses(val binding : LayoutRawRecyclerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Courses {
        return Courses(LayoutRawRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Courses, position: Int) {
        holder.binding.courseTitle.text = titles[position]
    }

    override fun getItemCount() = titles.size

}