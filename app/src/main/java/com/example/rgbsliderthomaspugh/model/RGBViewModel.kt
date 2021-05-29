package com.example.rgbsliderthomaspugh.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/* ViewModel to hold store all of the RGB values and setter methods for these values*/
class RGBViewModel : ViewModel() {

    // Below are livedata variables that are used to store the RGB values based on their colour
    private val _redValue = MutableLiveData<String>()
    val redValue: LiveData<String> = _redValue

    private val _greenValue = MutableLiveData<String>()
    val greenValue: LiveData<String> = _greenValue

    private val _blueValue = MutableLiveData<String>()
    val blueValue: LiveData<String> = _blueValue

    private val _completeValue = MutableLiveData<String>()
    val completeValue: LiveData<String> = _completeValue

    // Init block that is used to set all of the values to zero to avoid any errors when the
    // completeValueTextView in activity_main.xml is displayed.
    init {
        setRedValue(0)
        setBlueValue(0)
        setGreenValue(0)
        setCompleteValue()
    }

    // Setters for the individual values. Each slider uses a scale of 0-255 and the current progress
    // of the slider is passed in and converted to a hex value. This hex value is what will be displayed
    // to the user.
    fun setRedValue(newRedValue: Int): String {
        _redValue.value = "%02x".format(newRedValue).uppercase()
        setCompleteValue()
        return completeValue.toString()
    }

    fun setGreenValue(newGreenValue: Int) {
        _greenValue.value = "%02x".format(newGreenValue).uppercase()
        setCompleteValue()
    }

    fun setBlueValue(newBlueValue: Int) {
        _blueValue.value = "%02x".format(newBlueValue).uppercase()
        setCompleteValue()
    }

    // Sets the completed RGB value by concatenating all the individual values.
    private fun setCompleteValue() {
        _completeValue.value = "#${redValue.value}${greenValue.value}${blueValue.value}"
    }
}