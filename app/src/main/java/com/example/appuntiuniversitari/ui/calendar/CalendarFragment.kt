package com.example.appuntiuniversitari.ui.calendar

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.appuntiuniversitari.CoursesActivity
import com.example.appuntiuniversitari.R
import com.example.appuntiuniversitari.database.AppDatabase
import com.example.appuntiuniversitari.database.Calendario
import com.example.appuntiuniversitari.databinding.FragmentCalendarBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CalendarFragment : Fragment() {

//    private lateinit var homeViewModel: CalendarViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        homeViewModel =
//                ViewModelProvider(this).get(CalendarViewModel::class.java)
//        val root = inflater.inflate(R.layout.fragment_calendar, container, false)
//        val textView: TextView = root.findViewById(R.id.text_home)
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })


        var calendar: List<Calendario>
        GlobalScope.launch {
            val db = AppDatabase.getDatabase(requireContext())
            calendar = db.calendarDao().getCalendar()
        }
        val binding = FragmentCalendarBinding.inflate(layoutInflater)
        binding.semesterCalendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val intent = Intent(requireContext(),CoursesActivity::class.java)
            intent.putExtra("giorno_della_settimana","$dayOfMonth/$month")
            startActivity(intent)
//            Toast.makeText(requireContext(), "year: $year, month: $month, day: $dayOfMonth", Toast.LENGTH_SHORT).show()
        }



        return binding.root
    }
}