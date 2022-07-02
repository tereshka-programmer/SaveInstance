package com.example.saveinstance

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.versionedparcelable.VersionedParcelize
import com.example.saveinstance.databinding.ActivityMainBinding
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
import kotlin.properties.Delegates
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btIncrement.setOnClickListener { viewModel.increment() }
        binding.btRandomColor.setOnClickListener { viewModel.setRandomColor() }

        viewModel.initState(savedInstanceState?.getParcelable("KEY") ?: MainViewModel.State(
            counterValue = 0,
            textColor = ContextCompat.getColor(this, R.color.purple_700)
        ))

        viewModel.state.observe(this, Observer {
            renderState(it)
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("KEY", viewModel.state.value)
    }

    private fun renderState(state: MainViewModel.State) = with(binding){
        tvCounter.text = state.counterValue.toString()
        tvCounter.setTextColor(state.textColor)
    }
}