package com.example.haxzie.Models

/**
 * Created by haxzie on 9/12/17.
 */


data class Downloadable(val title:String,
                        val url:String,
                        var downloading:Boolean = true,
                        val file_type:String,
                        val file_size: Double,
                        var downloaded:Int,
                        var downloadId: Int? = -1)