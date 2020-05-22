package com.matias.features.ui.main.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.matias.core.helpers.fromHtml
import com.matias.core.managers.GlideApp
import com.matias.domain.models.offer.ProductOfferModel
import com.matias.features.R
import java.text.DecimalFormat

private const val MAX_OFFERS_TO_DISPLAY = 3

class OffersRecyclerAdapter(private val activity: Activity) : RecyclerView.Adapter<OffersRecyclerAdapter.OfferViewHolder>() {

	private var data: ArrayList<ProductOfferModel> = arrayListOf()

	private var listener: OnOfferClickListener?

	init {
		if (activity is OnOfferClickListener) {
			listener = activity
		} else {
			throw IllegalAccessException("Calling activity must implement OffersRecyclerAdapter.OnOfferClickListener interface")
		}
	}

	fun setData(data: ArrayList<ProductOfferModel>) {
		this.data.clear()
		this.data = data
		notifyDataSetChanged()
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder =
		OfferViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product_offer, parent, false))

	override fun getItemCount(): Int =
		if (data.size >= MAX_OFFERS_TO_DISPLAY) MAX_OFFERS_TO_DISPLAY else data.size

	override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
		holder.bindItem(data[position], listener, activity)
	}

	class OfferViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		private var ivThumbnail: AppCompatImageView = itemView.findViewById(R.id.iv_thumbnail)
		private var tvTitle: AppCompatTextView = itemView.findViewById(R.id.tv_title)
		private var tvSubtitle: AppCompatTextView = itemView.findViewById(R.id.tv_subtitle)
		private var tvOriginalPrice: AppCompatTextView = itemView.findViewById(R.id.tv_original_price)
		private var tvPrice: AppCompatTextView = itemView.findViewById(R.id.tv_price)
		private var tvDiscountPercentaje: AppCompatTextView = itemView.findViewById(R.id.tv_discount_percentaje)

		fun bindItem(productOffer: ProductOfferModel, listener: OnOfferClickListener?, activity: Activity) {
			val discountPercentaje = getDiscount(productOffer.originalPrice, productOffer.price)

			if (discountPercentaje <= 0) {
				itemView.visibility = View.GONE
				return
			}

			GlideApp.with(activity).load(productOffer.thumbnailUrl).fitCenter().into(ivThumbnail)

			tvTitle.text = productOffer.title
			tvSubtitle.text = productOffer.subtitle

			val originalPrice = getFormattedPrice(productOffer.originalPrice)
			tvOriginalPrice.text =
				fromHtml(String.format(activity.getString(R.string.original_price_placeholder, originalPrice)))

			val price = getFormattedPrice(productOffer.price)
			tvPrice.text = String.format(
				activity.getString(R.string.price_placeholder, price))

			tvDiscountPercentaje.text =
				String.format(activity.getString(R.string.discount_percentaje_placeholder), discountPercentaje)

			itemView.setOnClickListener { listener?.onOfferClick(productOffer.id) }

		}

		private fun getDiscount(originalPrice: Float, price: Float): Int {
			return 100 - ((price * 100) / originalPrice).toInt()
		}

		private fun getFormattedPrice(value: Float): String =
			DecimalFormat("#,###").format(value).replace(',', '.')

	}

	interface OnOfferClickListener {
		fun onOfferClick(productId: Int)
	}

}