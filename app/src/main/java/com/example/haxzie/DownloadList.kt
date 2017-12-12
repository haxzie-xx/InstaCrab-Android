package com.example.haxzie


import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.haxzie.Adapters.DownloadListAdapter
import com.example.haxzie.Models.Downloadable

import com.example.haxzie.test.R
import kotlinx.android.synthetic.main.fragment_download_list.*
import kotlinx.android.synthetic.main.new_download_dialog.*
import org.json.JSONObject


/**
 * A simple [Fragment] subclass.
 */
class DownloadList : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_download_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        val downloads = ArrayList<Downloadable>()
        val recyclerAdapter = DownloadListAdapter(downloads)
        recycler.adapter = recyclerAdapter
        var queue = Volley.newRequestQueue(context)

        var linkRetriveDialog = Dialog(context)
        linkRetriveDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        linkRetriveDialog.setContentView(R.layout.progress_dialog)
        linkRetriveDialog.setCancelable(false)
        linkRetriveDialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


        var dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.new_download_dialog)
        dialog.setCancelable(false)
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.cancel.setOnClickListener({
            dialog.dismiss()
        })
        dialog.download.setOnClickListener({
            dialog.dismiss()
            linkRetriveDialog.show()

            var url: String = dialog.post_url.text.toString()
            if(url.length > 15 && url.contains("instagram.com/p/")){
                url = DataStore.API_URl+url
                Log.i(DataStore.TAG, url)
                val request = JsonObjectRequest(Request.Method.GET, url, null,
                        Response.Listener<JSONObject> { response ->
                            val video_link = response.getString("video_link")
                            val title = response.getString("title")
                            val file = response.getString("file")
                            val file_url = response.getString("url")
                            recyclerAdapter.addDownload(Downloadable(title,video_link,false,file,0.0,0))
                            linkRetriveDialog.dismiss()

                        },
                        Response.ErrorListener {
                            Toast.makeText(context, "That didn't work!", Toast.LENGTH_SHORT).show()
                            linkRetriveDialog.dismiss()
                        })
                queue.add(request)
                queue.start()
            }
        })


        fab.setOnClickListener({
            dialog.show()
        })
    }


}// Required empty public constructor
