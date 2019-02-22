package com.visitcardpro.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase

abstract class BasicDAO(pContext: Context) {

    protected val VERSION = 1
    protected val NOM = "visitcardpro.db"
    protected lateinit var mDb: SQLiteDatabase
    protected var mHandler: DatabaseHandler

    init {
        this.mHandler = DatabaseHandler(pContext, NOM, version = VERSION)
    }

    fun open(): SQLiteDatabase {
        mDb = mHandler.writableDatabase
        return mDb
    }

    fun close() {
        mDb.close()
    }
}