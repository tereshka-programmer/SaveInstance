package com.example.saveinstance

import android.graphics.Color
import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.android.parcel.Parcelize
import kotlin.random.Random

class MainViewModel : ViewModel() {

    val state: LiveData<State> get() = stateLiveData
    private val stateLiveData = MutableLiveData<State>()

    fun initState(state: State){
        stateLiveData.value = state
    }

    fun increment() {
        val oldState = stateLiveData.value
        stateLiveData.value = oldState?.copy(
            counterValue = oldState.counterValue + 1
        )
    }

    fun setRandomColor() {
        val oldState = stateLiveData.value
        stateLiveData.value = oldState?.copy(
            textColor = Color.rgb(
                Random.nextInt(256),
                Random.nextInt(256),
                Random.nextInt(256)
            )
        )
    }

    @Parcelize
    data class State(
        var counterValue: Int,
        var textColor: Int
    ) : Parcelable
}