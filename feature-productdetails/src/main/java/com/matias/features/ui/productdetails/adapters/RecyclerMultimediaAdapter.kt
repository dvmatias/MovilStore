package com.matias.features.ui.productdetails.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.matias.core.managers.GlideApp
import com.matias.domain.models.product.ProductModel
import com.matias.features.R
import kotlinx.android.synthetic.main.item_multimedia.view.*

class RecyclerMultimediaAdapter(private val activity: Activity) : RecyclerView.Adapter<RecyclerMultimediaAdapter.MultimediViewHolder>() {

	private var data: ArrayList<ProductModel.ImageModel> = arrayListOf()

	fun setData(data: List<ProductModel.ImageModel>, imageUrl: String, promotionImageUrl: String) {
		this.data.clear()

		data.toCollection(this.data)
		if (imageUrl.isNotEmpty()) this.data.add(ProductModel.ImageModel(imageUrl))
		if (promotionImageUrl.isNotEmpty()) this.data.add(ProductModel.ImageModel(promotionImageUrl))

		notifyDataSetChanged()
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultimediViewHolder =
		MultimediViewHolder(
			LayoutInflater.from(parent.context).inflate(
				R.layout.item_multimedia,
				parent,
				false
			)
		)

	override fun onBindViewHolder(holder: MultimediViewHolder, position: Int) {
		val item = data[position]
		holder.bindItem(item, activity)
	}

	override fun getItemCount(): Int =
		data.size

	class MultimediViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

		fun bindItem(item: ProductModel.ImageModel, activity: Activity) {
			GlideApp
				.with(activity)
				.load(item.url)
				.centerCrop()
				.into(itemView.findViewById<AppCompatImageView>(R.id.ivMultimedia))
		}

	}

}