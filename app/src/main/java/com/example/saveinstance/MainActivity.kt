package com.example.saveinstance

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.saveinstance.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btIncrement.setOnClickListener { increment() }
        binding.btRandomColor.setOnClickListener { setRandomColor() }

    }

    private fun increment(){
        var counter = binding.tvCounter.text.toString().toInt()
        counter++
        binding.tvCounter.text = counter.toString()
    }

    private fun setRandomColor(){
        val randomColor = Color.rgb(
            Random.nextInt(256),
            Random.nextInt(256),
            Random.nextInt(256)
        )
        binding.tvCounter.setTextColor(randomColor)
    }
}