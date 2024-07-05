package com.example.application_flamingo.db

import android.provider.BaseColumns

object DbNameClass: BaseColumns {
    const val TABLE_NAME = "my_table2"
    const val ID_DB = "_id"
    const val COLUMN_NAME = "name"
    const val COLUMN_REGISTR = "registr"
    const val COLUMN_DIVE_LOGS = "dive_logs"
    const val COLUMN_VISITE = "visite_site"


    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "AppDb.db"

    const val CREAT_TABLE = "CREATE TABLE $TABLE_NAME ($COLUMN_NAME TEXT," +
                                                                     "$COLUMN_REGISTR INTEGER, " +
                                                                     "$COLUMN_DIVE_LOGS TEXT, " +
                                                                     "$COLUMN_VISITE INTEGER);"
    const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}