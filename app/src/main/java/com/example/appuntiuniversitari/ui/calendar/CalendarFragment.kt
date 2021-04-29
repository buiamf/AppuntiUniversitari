package com.example.appuntiuniversitari.ui.calendar

import android.R
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.applandeo.materialcalendarview.EventDay
import com.applandeo.materialcalendarview.listeners.OnDayClickListener
import com.example.appuntiuniversitari.CoursesActivity
import com.example.appuntiuniversitari.database.AppDatabase
import com.example.appuntiuniversitari.databinding.FragmentCalendarBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


class CalendarFragment : Fragment() {

//    private lateinit var homeViewModel: CalendarViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /*
//        homeViewModel =
//                ViewModelProvider(this).get(CalendarViewModel::class.java)
//        val root = inflater.inflate(R.layout.fragment_calendar, container, false)
//        val textView: TextView = root.findViewById(R.id.text_home)
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
*/


        val binding = FragmentCalendarBinding.inflate(layoutInflater)

        //        var calendar: List<Calendario>
        GlobalScope.launch {
            val db = AppDatabase.getDatabase(requireContext())
//            calendar = db.calendarDao().getCalendar()
            val calendario = db.calendarDao().getCalendar()

            val calendar = Calendar.getInstance()
            val highlightedDays = ArrayList<Calendar>()
//            val events = ArrayList<EventDay>()
            for (giorno in calendario) {
                calendar.set(
                    (giorno.data_semestre.substring(0, 3)).toInt(),
                    (giorno.data_semestre.substring(5, 6)).toInt(),
                    (giorno.data_semestre.substring(8, 9)).toInt()
                )
                highlightedDays.add(calendar)
//                events.add(EventDay(calendar))
            }
            binding.semesterCalendar.setHighlightedDays(highlightedDays)
//            binding.semesterCalendar.setEvents(events)


        }

        /*binding.semesterCalendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val intent = Intent(requireContext(),CoursesActivity::class.java)
            intent.putExtra("giorno_della_settimana","$dayOfMonth/${month+1}")
            startActivity(intent)
//            Toast.makeText(requireContext(), "year: $year, month: $month, day: $dayOfMonth", Toast.LENGTH_SHORT).show()
        }*/

        binding.semesterCalendar.setOnDayClickListener(OnDayClickListener { eventDay ->
            val clickedDayCalendar = eventDay.calendar
            val dayOfMonth = clickedDayCalendar.get(Calendar.DAY_OF_MONTH)
            val month = clickedDayCalendar.get(Calendar.MONTH)
            val intent = Intent(requireContext(), CoursesActivity::class.java)
            intent.putExtra("giorno_della_settimana", "$dayOfMonth/${month + 1}")
            startActivity(intent)

//            Log.d("TEST CALENDAR", clickedDayCalendar.get(Calendar.MONTH).toString() )
//            Log.d("TEST CALENDAR", clickedDayCalendar.get(Calendar.DAY_OF_MONTH).toString() )
        })

        return binding.root
    }
}