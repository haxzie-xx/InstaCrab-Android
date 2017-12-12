package com.example.haxzie.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by haxzie on 10/12/17.
 */

@Dao
public interface DownloadItemDao {

    @Query("SELECT * FROM downloaditem")
    List<DownloadItem> getAll();

    @Query("SELECT * FROM downloaditem WHERE downloadId = :did LIMIT 1")
    DownloadItem getById(int did);

    @Insert
    void insertDownloadable(DownloadItem downloadItem);

    @Insert
    void insertAll(List<DownloadItem> downloadItems);

    @Update
    void update(DownloadItem downloadItem);

    @Delete
    void delete(DownloadItem downloadItem);
}
