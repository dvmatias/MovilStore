package com.matias.features.ui.main.activity

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.matias.core.managers.GlideApp
import com.matias.domain.models.offer.ProductOfferModel
import com.matias.features.R

class OffersRecyclerAdapter(private val activity: Activity) : RecyclerView.Adapter<OffersRecyclerAdapter.OfferViewHolder>() {

	private var data: ArrayList<ProductOfferModel> = arrayListOf()

	fun setData(data: ArrayList<ProductOfferModel>) {
		this.data.clear()
		this.data = data
		notifyDataSetChanged()
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder =
		OfferViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product_offer, parent, false))

	override fun getItemCount(): Int =
		data.size

	override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
		holder.bindItem(activity, data[position])
	}

	class OfferViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		private var ivThumbnail: AppCompatImageView = itemView.findViewById(R.id.iv_thumbnail)
		private var tvTitle: AppCompatTextView = itemView.findViewById(R.id.tv_title)
		private var tvSubtitle: AppCompatTextView = itemView.findViewById(R.id.tv_subtitle)

		fun bindItem(activity: Activity, productOffer: ProductOfferModel) {
			GlideApp.with(activity).load(productOffer.thumbnailUrl).fitCenter().into(ivThumbnail)
			tvTitle.text = productOffer.title
			tvSubtitle.text = productOffer.subtitle
		}

	}

}