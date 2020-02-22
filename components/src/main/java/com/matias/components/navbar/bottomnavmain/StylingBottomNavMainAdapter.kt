package com.matias.components.navbar.bottomnavmain

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.matias.components.R
import com.matias.core.helpers.dpToPx
import kotlinx.android.synthetic.main.item_styling_bottom_nav_main.view.*


private const val ITEM_COUNT = 5

class StylingBottomNavMainAdapter(
	private val context: Context,
	private val listener: OnItemClickListener
) : RecyclerView.Adapter<StylingBottomNavMainAdapter.BottomNavItemViewHolder>() {

	companion object {

		private var isViewInitializaed: Boolean = false

	}

	private val labels: Array<String> = context.resources.getStringArray(R.array.styling_bottom_nav_labels)

	private val icons: IntArray =
		intArrayOf(
			R.drawable.ic_bottom_nav_home_32dp,
			R.drawable.ic_bottom_nav_products_32dp,
			R.drawable.ic_bottom_nav_shop_cart_32dp,
			R.drawable.ic_bottom_nav_contact_32dp,
			R.drawable.ic_bottom_nav_profile_32dp
		)

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottomNavItemViewHolder =
		BottomNavItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_styling_bottom_nav_main, parent, false))

	override fun onBindViewHolder(holder: BottomNavItemViewHolder, position: Int) {
		val params = holder.itemView.container.layoutParams
		val displayMetrics = context.resources.displayMetrics
		val dpWidth = displayMetrics.widthPixels / displayMetrics.density
		params.width = dpToPx(context, (dpWidth / 5).toInt())

		val icon: Drawable? = ContextCompat.getDrawable(context, icons[position])
		val label: String = labels[position]
		holder.bindItem(icon, label, listener, position)
		if (position == 0 && !isViewInitializaed) { holder.initialize() }
	}

	override fun getItemCount(): Int =
		ITEM_COUNT

	fun updateSelected(selectedView: View?, unselectedView: View?) {
		selectedView?.let { setSelected(it) }
		unselectedView?.let { setUnselected(it) }
	}

	private fun setSelected(selectedView: View) {
		selectedView.apply {
			tvLabel.isSelected = true
			ivIcon.isSelected = true
		}
	}

	private fun setUnselected(unselectedView: View) {
		unselectedView.apply {
			tvLabel.isSelected = false
			ivIcon.isSelected = false
		}
	}

	/**
	 * Holder class.
	 */
	class BottomNavItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		private lateinit var listener: OnItemClickListener

		fun bindItem(icon: Drawable?, label: String, listener: OnItemClickListener, position: Int) {
			this.listener = listener
			itemView.ivIcon.setImageDrawable(icon)
			itemView.tvLabel.text = label
			itemView.ivBadge.visibility = View.GONE
			itemView.container.setOnClickListener { handleItemClick(position) }
		}

		/**
		 * At init, the item selected is "HOME" item.
		 */
		fun initialize() {
			itemView.apply {
				performClick()
				tvLabel.isSelected = true
				ivIcon.isSelected = true
			}
			isViewInitializaed = true
		}

		private fun handleItemClick(position: Int) {
			listener.onItemClick(position)
		}

	}

	/**
	 *
	 */
	interface OnItemClickListener {
		fun onItemClick(position: Int)
	}

}