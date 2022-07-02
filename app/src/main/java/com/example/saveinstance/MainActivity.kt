package com.example.saveinstance

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import androidx.core.content.ContextCompat
import com.example.saveinstance.databinding.ActivityMainBinding
import java.io.Serializable
import kotlin.properties.Delegates
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var state: State

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btIncrement.setOnClickListener { increment() }
        binding.btRandomColor.setOnClickListener { setRandomColor() }

        state = if (savedInstanceState == null){
            State(
                counterValue = 0,
                texttColor = ContextCompat.getColor(this, R.color.purple_700)
            )
        } else {
            savedInstanceState.getSerializable(KEY_STATE) as State
        }
        renderState()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY_STATE, state)
    }

    private fun increment(){
        state.counterValue++
        renderState()
    }

    private fun setRandomColor(){
        state.texttColor = Color.rgb(
            Random.nextInt(256),
            Random.nextInt(256),
            Random.nextInt(256)
        )
        renderState()
    }

    private fun renderState(){
        binding.tvCounter.text = state.counterValue.toString()
        binding.tvCounter.setTextColor(state.texttColor)
    }


    class State(
        var counterValue: Int,
        var texttColor: Int
    ) : Serializable

    companion object{
        const val KEY_STATE = "STATE"
    }
}