package com.matias.components.bottomnavigation.bottomnavmain

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.matias.components.R
import com.matias.components.bottomnavigation.bottomnavmain.StylingBottomNavMainAdapter.OnItemClickListener
import com.matias.domain.models.item.ItemMainPageModel
import kotlinx.android.synthetic.main.bottom_nav_main.view.*

class StylingBottomNavMain : ConstraintLayout, OnItemClickListener {

	private var currentPosition: Int = -1

	private var oldPosition: Int = -1

	private var listener: OnBottomNavMainItemSelectedListener? = null

	private lateinit var adapter: StylingBottomNavMainAdapter

	constructor(context: Context) : super(context) {
		init(context)
	}

	constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
		init(context)
	}

	constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr) {
		init(context)
	}

	/**
	 * Init view.
	 */
	private fun init(context: Context) {
		View.inflate(context, R.layout.bottom_nav_main, this)
	}

	/**
	 * Function that allows Activity to set the item selected listener.
	 */
	fun setup(itemMainPageList: MutableList<ItemMainPageModel>, listener: OnBottomNavMainItemSelectedListener?) {
		this.listener = listener

		adapter = StylingBottomNavMainAdapter(context, this, itemMainPageList)
		recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
		recycler.adapter = adapter
	}

	/**
	 * [StylingBottomNavMainAdapter.OnItemClickListener] implementation.
	 */
	override fun onItemClick(position: Int) {
		oldPosition = currentPosition
		currentPosition = position
		val selectedView = recycler.findViewHolderForLayoutPosition(currentPosition)?.itemView
		val unselectedView = recycler.findViewHolderForLayoutPosition(oldPosition)?.itemView
		when (position) {
			oldPosition -> listener?.onItemReselected(selectedView)
			else -> {
				listener?.onItemUnselected(unselectedView)
				listener?.onItemSelected(selectedView)
				adapter.updateSelected(selectedView, unselectedView)
			}
		}
	}

	/**
	 * Interface to be implemented by calling Activity.
	 */
	interface OnBottomNavMainItemSelectedListener {

		fun onItemSelected(view: View?)

		fun onItemReselected(view: View?)

		fun onItemUnselected(view: View?)

	}

	open class SimpleOnBottomNavMainItemSelectedListener : OnBottomNavMainItemSelectedListener {
		override fun onItemSelected(view: View?) {}

		override fun onItemReselected(view: View?) {}

		override fun onItemUnselected(view: View?) {}
	}

}