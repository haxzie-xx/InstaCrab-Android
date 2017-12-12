package com.example.haxzie.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by haxzie on 10/12/17.
 */

@Database(entities = {DownloadItem.class}, version = 1)
public abstract class DownloadDB extends RoomDatabase{
    public abstract DownloadItemDao downloadItemDao();
}
