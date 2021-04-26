package com.example.appuntiuniversitari.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CalendarDao {
    @Query("SELECT * FROM calendario ORDER BY data_semestre ASC")
    fun getCalendar() : List<Calendario>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(calendario : Calendario)
}

@Dao
interface  CourseDao {
    @Query("SELECT DISTINCT corso_name FROM corso ORDER BY corso_name")
    fun getAllCourses() : List<String?>

    
}