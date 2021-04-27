package com.example.appuntiuniversitari.database

import androidx.room.*

@Dao
interface CalendarDao {
    @Query("SELECT * FROM calendario ORDER BY data_semestre ASC")
    fun getCalendar() : List<Calendario>

    @Query("SELECT corso_id FROM calendario WHERE data_semestre = :giorno")
    fun getDailyCoursesIds(giorno : String) : Array<Int>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg calendario : Calendario)

    @Query("DELETE FROM calendario")
    fun deleteAll()
}

@Dao
interface  CourseDao {
    @Query("SELECT * FROM corso ORDER BY corso_name")
    fun getAllCourses() : List<Corso>

    @Query("SELECT corso_name FROM corso ORDER BY corso_name")
    fun getAllCoursesNames() : Array<String>

    @Query("SELECT corso_name FROM corso WHERE corso_id = :id")
    fun getDailyCourses(id : String) : String

    @Query("SELECT corso_name FROM corso WHERE corso_id = :id")
    fun getCourseNameFromId(id : String) : String

//    @Query("SELECT corso_id FROM corso WHERE corso_name = (:nome) ")
//    fun getCourseIdFromName(nome : String) : String

    @Query("SELECT corso_name FROM corso")
    fun getTest() : Array<String>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg corso: Corso)

    @Query("DELETE FROM corso")
    fun deleteAll()
}

@Dao
interface NotesDao {
    @Query("SELECT * FROM nota")
    fun getAllNotes() : List<Nota>

    @Query("SELECT corpo FROM nota WHERE corso_id = :materia")
    fun getCourseNotes(materia : String) : String

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg nota: Nota)

    @Query("DELETE FROM nota")
    fun deleteAll()

    @Update
    fun updateNotes(vararg nota: Nota)
}