package com.example.myapplication


import android.util.Log
import org.jsoup.Jsoup
import java.io.IOException
import java.util.*

class YoutubeCrawler {

    private val random = Random()
    private val indexNum = random.nextInt(7)

    var videoIds = mutableListOf(
    "dmEU6-UQSgU",
    "lmOUj3xqGcM",
    "lGvHIB2VfWc",
    "ITsE1bO1qHk",
    "uHUpuON1y08",
    "r6HWlvMYboQ",
    "qjlPUNPt2uw"
    )
    var videoTitle = ""
    private val videoId = videoIds[indexNum]
    private val channelUrl = "https://www.youtube.com/watch?v=$videoId"
    fun crawlYoutubeVideoTitle(): String {
        val videoUrl = channelUrl
        try {
            val doc = Jsoup.connect(videoUrl).get()
            val titleElement = doc.select("meta[property=og:title]").first()

            if (titleElement != null) {
                videoTitle = titleElement.attr("content")
                Log.d("tag", videoTitle)
            } else {
                Log.d("tag", "noTitle")
            }
        } catch (e: IOException) {
            Log.d("tag", "Exception")
        }
    return videoTitle
    }
}



