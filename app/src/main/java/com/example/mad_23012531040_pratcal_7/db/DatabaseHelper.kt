package com.example.mad_23012531040_pratcal_7.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.mad_23012531040_pratcal_7.Person

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "PersonDatabase"
        private const val TABLE_PERSONS = "Persons"

        private const val KEY_ID = "id"
        private const val KEY_NAME = "name"
        private const val KEY_EMAIL = "email"
        private const val KEY_PHONE = "phone"
        private const val KEY_ADDRESS = "address"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = ("CREATE TABLE " + TABLE_PERSONS + "("
                + KEY_ID + " TEXT PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_EMAIL + " TEXT," + KEY_PHONE + " TEXT,"
                + KEY_ADDRESS + " TEXT" + ")")
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_PERSONS")
        onCreate(db)
    }

    fun insertPerson(person: Person): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, person.id)
        contentValues.put(KEY_NAME, person.name)
        contentValues.put(KEY_EMAIL, person.email)
        contentValues.put(KEY_PHONE, person.phone)
        contentValues.put(KEY_ADDRESS, person.address)
        val success = db.insert(TABLE_PERSONS, null, contentValues)
        db.close()
        return success
    }

    fun getPerson(id: String): Person? {
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_PERSONS,
            arrayOf(KEY_ID, KEY_NAME, KEY_EMAIL, KEY_PHONE, KEY_ADDRESS),
            "$KEY_ID=?",
            arrayOf(id),
            null,
            null,
            null,
            null
        )
        if (cursor != null && cursor.moveToFirst()) {
            val person = Person(
                cursor.getString(cursor.getColumnIndexOrThrow(KEY_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(KEY_EMAIL)),
                cursor.getString(cursor.getColumnIndexOrThrow(KEY_PHONE)),
                cursor.getString(cursor.getColumnIndexOrThrow(KEY_ADDRESS))
            )
            cursor.close()
            db.close()
            return person
        }
        db.close()
        return null
    }

    val allPersons: ArrayList<Person>
        get() {
            val personList = ArrayList<Person>()
            val selectQuery = "SELECT  * FROM $TABLE_PERSONS"
            val db = this.readableDatabase
            val cursor = db.rawQuery(selectQuery, null)
            if (cursor.moveToFirst()) {
                do {
                    val person = Person(
                        cursor.getString(cursor.getColumnIndexOrThrow(KEY_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(KEY_EMAIL)),
                        cursor.getString(cursor.getColumnIndexOrThrow(KEY_PHONE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(KEY_ADDRESS))
                    )
                    personList.add(person)
                } while (cursor.moveToNext())
            }
            cursor.close()
            db.close()
            return personList
        }

    fun updatePerson(person: Person): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, person.name)
        contentValues.put(KEY_EMAIL, person.email)
        contentValues.put(KEY_PHONE, person.phone)
        contentValues.put(KEY_ADDRESS, person.address)
        val success = db.update(TABLE_PERSONS, contentValues, "$KEY_ID = ?", arrayOf(person.id))
        db.close()
        return success
    }

    fun deletePerson(person: Person) {
        val db = this.writableDatabase
        db.delete(TABLE_PERSONS, "$KEY_ID = ?", arrayOf(person.id))
        db.close()
    }
}
