package com.example.saveinstance

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import androidx.core.content.ContextCompat
import androidx.versionedparcelable.VersionedParcelize
import com.example.saveinstance.databinding.ActivityMainBinding
import kotlinx.android.parcel.Parcelize
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
            savedInstanceState.getParcelable(KEY_STATE)!!
        }
        renderState()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_STATE, state)
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

    @Parcelize
    class State(
        var counterValue: Int,
        var texttColor: Int
    ) : Parcelable

    companion object{
        const val KEY_STATE = "STATE"
    }
}