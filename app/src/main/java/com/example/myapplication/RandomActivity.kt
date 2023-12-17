package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.databinding.ActivityRandomBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.coroutines.*
import org.jsoup.Jsoup
import java.io.IOException
import java.util.*

class RandomActivity : AppCompatActivity() {
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
    private val channelUrl = "https://www.youtube.com/watch?v=$videoId" // 여기에 자신의 채널 URL을 넣으세요

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRandomBinding.inflate(layoutInflater)
        setContentView((binding.root))

        GlobalScope.launch(Dispatchers.Main) {
            Log.d("tag", "Thread start")
            val result = withContext(Dispatchers.Default) {
                doBackgroundWork()
            }
            binding.titleText.text = result
            Log.d("tag", result)
            Log.d("tag", videoId)
            val youtubePlayerView: YouTubePlayerView = findViewById(R.id.youtube_player_view)

            lifecycle.addObserver(youtubePlayerView)
            youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadVideo(videoId, 0f)
                }
            })
        }


        binding.backBtn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            Log.d("tag", "intent Success")
            startActivity(intent)
        }

        binding.anotherBtn.setOnClickListener {
            finish() //인텐트 종료
            val intent = intent //인텐트
            Log.d("tag" , "refreshActivity")
            startActivity(intent) //액티비티 열기
        }


    }
    private fun doBackgroundWork(): String {
        // 백그라운드 스레드에서 수행되어야 하는 작업
        return crawlYoutubeVideoIds() //title
    }

    private fun crawlYoutubeVideoIds(): String {
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
