package com.example.application_flamingo.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class DbManager(val context: Context) {
    val dbHelper = DbHelper(context)
    var db: SQLiteDatabase? = null

    fun openDb(){
        db = dbHelper.writableDatabase
    }

    fun insertToDb( name: String, registr: Int, dive_logs: String, visite_site: Int){
        val values = ContentValues().apply {
            put(DbNameClass.COLUMN_NAME, name)
            put(DbNameClass.COLUMN_REGISTR, registr)
            put(DbNameClass.COLUMN_DIVE_LOGS, dive_logs)
            put(DbNameClass.COLUMN_VISITE, visite_site)
        }
        db?.insert(DbNameClass.TABLE_NAME, null, values)
    }

    @SuppressLint("Range")
    fun readDbData(): ArrayList<Any>{
        val dataList = ArrayList<Any>()
        val cursor = db?.query(DbNameClass.TABLE_NAME, null, null, null, null, null, null)
        while(cursor?.moveToNext()!!){
            val dataName = cursor.getString(cursor.getColumnIndex(DbNameClass.COLUMN_NAME))
            val dataRegistr = cursor.getInt(cursor.getColumnIndex(DbNameClass.COLUMN_REGISTR))
            val dataDiveLogs = cursor.getString(cursor.getColumnIndex(DbNameClass.COLUMN_DIVE_LOGS))
            val dataVisite = cursor.getInt(cursor.getColumnIndex(DbNameClass.COLUMN_VISITE))

            dataList.add(dataName)
            dataList.add(dataRegistr)
            dataList.add(dataDiveLogs)
            dataList.add(dataVisite)
        }
        cursor.close()
        return dataList
    }

    fun closeDb(){
        dbHelper.close()
    }
    fun allDelete(){
        db?.delete(DbNameClass.TABLE_NAME, null, null)
    }
}