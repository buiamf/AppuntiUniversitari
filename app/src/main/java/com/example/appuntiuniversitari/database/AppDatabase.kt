package com.example.appuntiuniversitari.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Calendario::class, Corso::class, Nota::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun calendarDao(): CalendarDao
    abstract fun courseDao(): CourseDao
    abstract fun notesDao(): NotesDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "App_database"
                ).addCallback(AppDatabaseCallback(/*scope*/CoroutineScope(Job()))).build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class AppDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.calendarDao(),database.courseDao(),database.notesDao())
                }
            }
        }

        fun populateDatabase(calendarDao: CalendarDao, courseDao: CourseDao, notesDao: NotesDao) {
            calendarDao.deleteAll()
            courseDao.deleteAll()
            notesDao.deleteAll()

            calendarDao.insert(
                Calendario(1,1,"2021/05/26"),
                Calendario(2,2,"2021/05/26"),
                Calendario(3,3,"2021/05/26"),
                Calendario(5,4,"2021/05/27"),
                Calendario(6,2,"2021/05/28"),
                Calendario(7,4,"2021/05/28"),
                Calendario(8,3,"2021/05/29"),
                Calendario(9,2,"2021/05/29"),
                Calendario(10,1,"2021/05/30")
            )

            courseDao.insert(
                Corso(1,"Analisi","Martella"),
                Corso(2,"Fisica","Quantino"),
                Corso(3,"Informatica","Andrea"),
                Corso(4,"Inglese","Campino")
            )

            notesDao.insert(
                Nota(1,1,"Note di analisi:"),
                Nota(2,2,"Note di fisica:"),
                Nota(3,3,"Note di informatica:"),
                Nota(4,4,"Note di inglese:")
            )
        }

    }

}