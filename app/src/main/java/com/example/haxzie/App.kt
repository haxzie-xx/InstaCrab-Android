package com.example.haxzie

import android.app.Application
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

import com.example.haxzie.Database.DownloadDB

/**
 * Created by haxzie on 10/12/17.
 */

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        ctx = applicationContext
        //Initialize android Rooms db
        db = Room.databaseBuilder(ctx!!, DownloadDB::class.java, DATABASE_NAME).build()
        // Enabling database for resume support even after the application is killed:

    }

    companion object {

        private var ctx: Context? = null
        var db: RoomDatabase? = null
            private set
        private val DATABASE_NAME = "instaGrab"
    }

}
