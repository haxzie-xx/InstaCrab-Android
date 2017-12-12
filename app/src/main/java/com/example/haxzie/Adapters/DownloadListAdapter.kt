package com.example.haxzie.Adapters

import android.content.Context
import android.os.Environment
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast

import com.example.haxzie.DataStore
import com.example.haxzie.FileOpen
import com.example.haxzie.Models.Downloadable
import com.example.haxzie.test.R
import com.koushikdutta.ion.Ion
import java.io.File
import java.lang.Exception
import java.net.URL

/**
 * Created by haxzie on 9/12/17.
 */
 class DownloadListAdapter(val downloadList: ArrayList<Downloadable>) : RecyclerView.Adapter<DownloadListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent!!.context).inflate(R.layout.download_item, parent, false)
        return ViewHolder(v, parent!!.context)
    }

    override fun getItemCount(): Int {
        return downloadList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(downloadList[position])
    }

    public fun addDownload(downloadable: Downloadable){
        downloadList.add(0,downloadable)
        this.notifyItemInserted(0)
    }
    class ViewHolder(itemView: View, val ctx: Context) : RecyclerView.ViewHolder(itemView){

        fun bindItems(downloadItem: Downloadable){
            val txt_item_title = itemView.findViewById<TextView>(R.id.file_name)
            val txt_file_type = itemView.findViewById<TextView>(R.id.file_type)
            val txt_file_size = itemView.findViewById<TextView>(R.id.file_size)
            val download_status = itemView.findViewById<ImageButton>(R.id.pause_resume)
            val download_progress = itemView.findViewById<ProgressBar>(R.id.progress)
            val main_view = itemView.findViewById<CardView>(R.id.main_view)
            main_view.setOnClickListener({
                if (!downloadItem.downloading){
                    val myFile = File("/sdcard/instaCrab"+downloadItem.title.replace(" ","_")+".mp4")
                    FileOpen.openFile(ctx, myFile)
                }
            })

            txt_item_title.text = downloadItem.title
            txt_file_size.text = ""
            txt_file_type.text = downloadItem.file_type

            if (downloadItem.downloadId == -1){
                downloadItem.downloading = true
                var downloadURL = URL(downloadItem.url)
                Log.i(DataStore.TAG,downloadItem.url)
                Ion.with(ctx)
                        .load(downloadItem.url)
                        .progressBar(download_progress)
                        .write(File("/sdcard/instaCrab"+downloadItem.title.replace(" ","_")+".mp4"))
                        .setCallback({
                            e: Exception?, result: File? ->
                            if (e != null)
                                e.printStackTrace()
                            else if (result!=null && result.exists()){
                                Toast.makeText(ctx,"Completed",Toast.LENGTH_LONG).show()
                                downloadItem.downloading = false
                            }
                        })
            }

        }
    }
}

