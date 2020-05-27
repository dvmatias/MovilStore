package com.matias.features.ui.main.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.matias.core.managers.GlideApp
import com.matias.domain.models.newproduct.NewProductModel
import com.matias.features.R
import kotlinx.android.synthetic.main.item_product_new.*
import kotlinx.android.synthetic.main.item_product_new.view.*

class NewProductRecyclerAdapter(private val activity: Activity) : RecyclerView.Adapter<NewProductRecyclerAdapter.NewProductViewHolder>() {

	private var data: ArrayList<NewProductModel> = arrayListOf()

	private var listener: NewProductClickListener?

	init {
		if (activity is NewProductClickListener) {
			listener = activity
		} else {
			throw IllegalAccessException("Calling activity must implement NewProductRecyclerAdapter.NewProductClickListener interface")
		}
	}

	fun setData(data: ArrayList<NewProductModel>) {
		this.data.clear()
		this.data = data
		notifyDataSetChanged()
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewProductViewHolder =
		NewProductViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product_new, parent, false))

	override fun onBindViewHolder(holder: NewProductViewHolder, position: Int) {
		holder.bintItem(data[position], listener, activity)
	}

	override fun getItemCount(): Int =
		data.size

	/**
	 * View Holder realization.
	 */
	class NewProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

		fun bintItem(product: NewProductModel, listener: NewProductClickListener?, activity: Activity) {
			GlideApp.with(activity).load(product.promotionImageUrl).fitCenter().into(itemView.ivNewProductImage)
			itemView.tvTitle.text = product.title
			itemView.tvSubtitle.text = product.subtitle

			itemView.setOnClickListener { listener?.onNewProductClick(productId = product.id) }
		}

	}

	/**
	 *
	 */
	interface NewProductClickListener {

		fun onNewProductClick(productId: Int)

	}

}