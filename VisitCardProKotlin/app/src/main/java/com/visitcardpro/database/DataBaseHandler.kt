package com.visitcardpro.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context, name: String, factory: SQLiteDatabase.CursorFactory? = null, version: Int):
    SQLiteOpenHelper(context, name, factory, version) {


    override fun onCreate(db: SQLiteDatabase) {
 //       db.execSQL(UserDAO.TABLE_CREATE)
 //       db.execSQL(MediaDAO.TABLE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase, i: Int, i1: Int) {
//        db.execSQL(UserDAO.TABLE_DROP)
//        db.execSQL(MediaDAO.TABLE_DROP)
//        onCreate(db)
    }

}