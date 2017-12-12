package com.example.haxzie.Database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by haxzie on 10/12/17.
 */

@Entity
class DownloadItem {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "downloadId")
    var downloadId: Int = 0

    @ColumnInfo(name = "url")
    var url: String? = null

    @ColumnInfo(name = "title")
    var title: String? = null

    @ColumnInfo(name = "fileType")
    var fileType: String? = null

    @ColumnInfo(name = "fileSize")
    var fileSize: Double = 0.toDouble()

    @ColumnInfo(name = "completed")
    var isCompleted = false

    @ColumnInfo(name = "downloading")
    var isDownloading = false

    @ColumnInfo(name = "downloaded")
    var downloaded: Int = 0
}
