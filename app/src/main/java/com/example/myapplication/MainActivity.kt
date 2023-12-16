package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView((binding.root))

        binding.sadBtn.setOnClickListener{
            val intent = Intent(this, SadActivity::class.java)
            Log.d("tag", "intent Sad Success")
            startActivity(intent)
        }

        binding.upsetBtn.setOnClickListener{
            val intent = Intent(this, UpsetActivity::class.java)
            Log.d("tag", "intent Upset Success")
            startActivity(intent)
        }

        binding.excitingBtn.setOnClickListener{
            val intent = Intent(this, ExcitingActivity::class.java)
            Log.d("tag", "intent exciting Success")
            startActivity(intent)
        }


    }
}
