package com.example.saveinstance

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.saveinstance.databinding.ActivityMainBinding
import kotlin.properties.Delegates
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private var counterValue by Delegates.notNull<Int>()
    private var texttColor by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btIncrement.setOnClickListener { increment() }
        binding.btRandomColor.setOnClickListener { setRandomColor() }

        if (savedInstanceState == null){
            counterValue = 5
            texttColor = ContextCompat.getColor(this, R.color.purple_700)
        } else {
            counterValue = savedInstanceState.getInt(KEY_COUNTER)
            texttColor = savedInstanceState.getInt(KEY_COLOR)
        }
        renderState()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_COUNTER, counterValue)
        outState.putInt(KEY_COLOR, texttColor)
    }

    private fun increment(){
        counterValue++
        renderState()
    }

    private fun setRandomColor(){
        texttColor = Color.rgb(
            Random.nextInt(256),
            Random.nextInt(256),
            Random.nextInt(256)
        )
        renderState()
    }

    private fun renderState(){
        binding.tvCounter.text = counterValue.toString()
        binding.tvCounter.setTextColor(texttColor)
    }

    companion object{
        const val KEY_COUNTER = "KEY_COUNTER"
        const val KEY_COLOR = "KEY_COLOR"
    }
}