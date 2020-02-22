package com.matias.components.navbar.bottomnavmain

import android.view.View

/**
 * Interface to be implemented by calling Activity.
 */
interface OnBottomNavMainItemSelectedListener {

	fun onItemSelected(view: View?)

	fun onItemReselected(view: View?)

	fun onItemUnselected(view: View?)

}