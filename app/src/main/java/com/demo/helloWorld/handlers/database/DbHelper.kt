package com.demo.helloWorld.handlers.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(ctx: Context): SQLiteOpenHelper(ctx, DBNAME, null, VERSION) {
    companion object{
        const val DBNAME = "DemoIceCreamDB"
        const val VERSION = 1
        const val TABLENAME = "ice_cream_fact"
        const val COL_1 = "id"
        const val COL_2 = "title"
        const val COL_3 = "fact"
        const val COL_4 = "image"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query: String = """
                CREATE TABLE $TABLENAME (
                    $COL_1 INTEGER PRIMARY KEY AUTOINCREMENT,
                    $COL_2 CHAR(50) NOT NULL,
                    $COL_3 TEXT NOT NULL,
                    $COL_4 BLOB
                )
            """.trimIndent()
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // skip this as it is not required at the time
    }


}