package com.ubaya.a160419062_todoapp.util

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ubaya.a160419062_todoapp.model.TodoDatabase

val DB_NAME = "newtododb"

fun buildDB(context: Context):TodoDatabase{
    val db = Room.databaseBuilder(context, TodoDatabase::class.java, DB_NAME).addMigrations(
        MIGRATION_1_2).build()
    return db

}
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE todo ADD COLUMN priority INTEGER DEFAULT 3 not null")
    }
}