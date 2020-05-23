package com.matias.features.ui.main.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.matias.features.R

class CategoryRecyclerAdapter(private val activity: Activity) : RecyclerView.Adapter<CategoryRecyclerAdapter.CategoryViewHolder>() {

	private var data: ArrayList<CategoryModel> = arrayListOf()

	private var listener: CategoryClickListener?

	init {
		if (activity is CategoryClickListener) {
			listener = activity
		} else {
			throw IllegalAccessException("Calling activity must implement OffersRecyclerAdapter.OnOfferClickListener interface")
		}
	}

	fun setData(data: ArrayList<CategoryModel>) {
		this.data.clear()
		this.data = data
		notifyDataSetChanged()
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
		CategoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false))

	override fun getItemCount(): Int =
		data.size

	override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
		holder.bindItem(data[position], listener, activity)
	}

	class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		private val tvName: AppCompatTextView = itemView.findViewById(R.id.tv_name)

		fun bindItem(category: CategoryModel, listener: CategoryClickListener?, activity: Activity) {
			tvName.text = category.categoryName.value
			itemView.setOnClickListener { listener?.onCategoryClick(category.categoryName) }
		}

	}

	interface CategoryClickListener {
		fun onCategoryClick(categoryName: CategoryName)
	}

	data class CategoryModel(
		val categoryName: CategoryName
	)

	enum class CategoryName(val value: String) {
		A("Fundas"),
		B("Cargadores"),
		C("Cables"),
		D("Pelotudeces")
	}

}