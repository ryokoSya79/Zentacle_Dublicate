package com.example.application_flamingo.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context): SQLiteOpenHelper(context, DbNameClass.DATABASE_NAME, null, DbNameClass.DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(DbNameClass.CREAT_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL(DbNameClass.SQL_DELETE_TABLE)
        onCreate(db)
    }
}