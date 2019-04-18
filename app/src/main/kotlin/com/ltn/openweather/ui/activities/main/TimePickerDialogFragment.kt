package com.ltn.openweather.ui.activities.main

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.DialogFragment
import com.ltn.openweather.R
import java.util.*

class TimePickerDialogFragment : DialogFragment() {

    companion object {
        const val TAG = "TimePicker"

        fun getInstance(): TimePickerDialogFragment {
            return TimePickerDialogFragment()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()

        return TimePickerDialog(
            context,
            R.style.TimePicker,
            activity as TimePickerDialog.OnTimeSetListener,
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            DateFormat.is24HourFormat(activity)
        )
    }
}


