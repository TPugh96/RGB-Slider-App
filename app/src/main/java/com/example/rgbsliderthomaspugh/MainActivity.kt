package com.example.rgbsliderthomaspugh

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.rgbsliderthomaspugh.databinding.ActivityMainBinding
import com.example.rgbsliderthomaspugh.model.RGBViewModel
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    // Declaring the viewModel and binding for later use.
    private lateinit var viewModel: RGBViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setting up the viewModel using RGBViewModel, giving access to all of its attributes and
        // setters.
        viewModel = ViewModelProvider(this).get(RGBViewModel::class.java)

        // Setting up the binding between this activity and the activity_main.xml file and also
        // allowing the xml file to use the viewModel. There are several textViews that access the
        // viewModel directly to set their texts rather than using the binding in MainActivity.
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        // This section gets the sliders via the data binding to access the current slider value and
        // to set the slider label to display int instead of float. This is also where the RGB values
        // in the viewModel are set, based on the current slider value.
        val redSlider = binding.redSlider
        redSlider.addOnChangeListener { _, value, _ ->
            viewModel.setRedValue(value.toInt())
            changeBackGroundColour()
        }
        redSlider.setLabelFormatter { value: Float ->
            val format = NumberFormat.getIntegerInstance()
            format.maximumFractionDigits = 0
            format.format(value.toInt())
        }


        val greenSlider = binding.greenSlider
        greenSlider.addOnChangeListener { _, value, _ ->
            viewModel.setGreenValue(value.toInt())
            changeBackGroundColour()
        }
        greenSlider.setLabelFormatter { value: Float ->
            val format = NumberFormat.getIntegerInstance()
            format.maximumFractionDigits = 0
            format.format(value.toInt())
        }

        val blueSlider = binding.blueSlider
        blueSlider.addOnChangeListener { _, value, _ ->
            viewModel.setBlueValue(value.toInt())
            changeBackGroundColour()
        }
        blueSlider.setLabelFormatter { value: Float ->
            val format = NumberFormat.getIntegerInstance()
            format.maximumFractionDigits = 0
            format.format(value.toInt())
        }

    }

    // This function uses the complete RGB value from the viewModel to set the background colour of
    // the activity. The value needs to be parsed as is in a hex String format for display purposes.
    private fun changeBackGroundColour() {
        binding.mainConstraintLayout.setBackgroundColor(Color.parseColor(viewModel.completeValue.value.toString()))
    }
}