package com.eds.univo.prograiv.parcial3.utils

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast


/** * Created by nhulox97 on 12,junio,2019 */
class PersonDBHelper: Keys {

    val DB_TABLE = "Persons"
    val COL_ID = "Id"
    val COL_NAME = "Name"
    val COL_PHONE_NUMBER = "PhoneNumber"
    val COL_DUI = "DUI"

    private val CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS $DB_TABLE ($COL_ID INTEGER PRIMARY KEY," +
            "$COL_NAME TEXT, $COL_PHONE_NUMBER INTEGER, $COL_DUI INTEGER);"

    private var db: SQLiteDatabase? = null
    constructor(context: Context){
        var dbHelper = DatabaseHelper(context)
        db = dbHelper.writableDatabase

    }

    fun insert(values: ContentValues): Long{
        //val ID = db!!.insert(DB_TABLE, "", values)
        return db!!.insert(DB_TABLE, "", values)
    }

    fun queryAll(): Cursor {
        return db!!.rawQuery("select * from $DB_TABLE", null)
    }

    fun delete(selection: String, selectionArgs: Array<String>): Int{
        //val count = db!!.delete(DB_TABLE, selection, selectionArgs)
        return db!!.delete(DB_TABLE, selection, selectionArgs)
    }

    fun update(values: ContentValues, selection: String, selectionArgs: Array<String>): Int{
        //val count = db!!.update(DB_TABLE, values, selection, selectionArgs)
        return db!!.update(DB_TABLE, values, selection, selectionArgs)
    }

    inner class DatabaseHelper: SQLiteOpenHelper {
        var context: Context? = null

        constructor(context: Context): super(context, DB_NAME, null, DB_VERSION){
            this.context = context
        }
        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL(CREATE_TABLE_SQL)
            Toast.makeText(this.context, "Database is created", Toast.LENGTH_LONG).show()
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("Drop table IF EXISTS $DB_TABLE")
        }

    }

}