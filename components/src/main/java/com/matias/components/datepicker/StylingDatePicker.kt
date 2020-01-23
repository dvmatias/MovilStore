package com.matias.components.datepicker

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class StylingDatePicker : DialogFragment(), DatePickerDialog.OnDateSetListener {

	private var listener: DatePickerDialog.OnDateSetListener? = null

	override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
		// Use the current date as the default date in the picker
		val c = Calendar.getInstance()
		val year = c.get(Calendar.YEAR)
		val month = c.get(Calendar.MONTH)
		val day = c.get(Calendar.DAY_OF_MONTH)

		// Create a new instance of DatePickerDialog and return it
		return DatePickerDialog(activity!!, this, year, month, day)
	}

	override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
		listener?.onDateSet(view, year, month, day)
	}

	companion object {
		fun newInstance(listener: DatePickerDialog.OnDateSetListener): StylingDatePicker {
			val fragment = StylingDatePicker()
			fragment.listener = listener
			return fragment
		}
	}

}