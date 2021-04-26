package com.example.appuntiuniversitari.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appuntiuniversitari.R
import com.example.appuntiuniversitari.adapters.RecyAdapter
import com.example.appuntiuniversitari.databinding.FragmentCalendarBinding
import com.example.appuntiuniversitari.databinding.FragmentNotesBinding

class NotesFragment : Fragment() {

//    private lateinit var dashboardViewModel: NotesViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
//        dashboardViewModel =
//                ViewModelProvider(this).get(NotesViewModel::class.java)
//        val root = inflater.inflate(R.layout.fragment_notes, container, false)
//        val textView: TextView = root.findViewById(R.id.text_dashboard)
//        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })


        val binding = FragmentNotesBinding.inflate(layoutInflater)

        binding.recyCourses.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = RecyAdapter(arrayOf("Materia 1","Materia 2","Materia 3"), requireContext())
        }

//        binding.recyCourses.setHasFixedSize(true)
//        binding.recyCourses.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//        binding.recyCourses.adapter = RecyAdapter(arrayOf("Materia 1","Materia 2","Materia 3"), requireContext())



//        var viewAdapter: RecyclerView.Adapter<*>
//        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
//
//
//        viewAdapter = RecyAdapter(arrayProva, activity)
//
//        homeRecyclerView.setHasFixedSize(true)
//        homeRecyclerView.layoutManager = layoutManager
//        homeRecyclerView.adapter = viewAdapter

//        val recyclerView = binding.recyCourses.apply {
//            adapter = RecyAdapter(arrayOf("Materia 1","Materia 2","Materia 3"))
//            layoutManager = LinearLayoutManager(context)
//        }

//        val recyclerAdapter = RecyAdapter(arrayOf("Materia 1","Materia 2","Materia 3"))
//        recyclerView.adapter = recyclerAdapter
//        recyclerView.layoutManager

//        val recyclerView = findViewById<View>(R.id.recy_courses) as RecyclerView
//        val recyclerView = RecyAdapter(arrayOf("Materia 1","Materia 2","Materia 3"))
//        val layoutManager = LinearLayoutManager




        return binding.root
    }
}