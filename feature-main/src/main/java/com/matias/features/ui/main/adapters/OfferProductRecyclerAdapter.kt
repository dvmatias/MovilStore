package com.matias.features.ui.main.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.matias.core.helpers.formatPrice
import com.matias.core.helpers.fromHtml
import com.matias.core.helpers.getDiscount
import com.matias.core.managers.GlideApp
import com.matias.domain.models.offer.OfferProductModel
import com.matias.features.R
import kotlinx.android.synthetic.main.item_product_offer.view.*

private const val MAX_OFFERS_TO_DISPLAY = 3

class OfferProductRecyclerAdapter(private val activity: Activity) : RecyclerView.Adapter<OfferProductRecyclerAdapter.OfferProductViewHolder>() {

	private var data: ArrayList<OfferProductModel> = arrayListOf()

	private var listener: OfferClickListener?

	init {
		if (activity is OfferClickListener) {
			listener = activity
		} else {
			throw IllegalAccessException("Calling activity must implement OfferProductRecyclerAdapter.OfferClickListener interface")
		}
	}

	fun setData(data: ArrayList<OfferProductModel>) {
		this.data.clear()
		this.data = data
		notifyDataSetChanged()
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferProductViewHolder =
		OfferProductViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product_offer, parent, false))

	override fun getItemCount(): Int =
		if (data.size >= MAX_OFFERS_TO_DISPLAY) MAX_OFFERS_TO_DISPLAY else data.size

	override fun onBindViewHolder(holder: OfferProductViewHolder, position: Int) {
		holder.bindItem(data[position], listener, activity)
	}

	class OfferProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

		fun bindItem(offerProduct: OfferProductModel, listener: OfferClickListener?, activity: Activity) {
			val discountPercentaje = getDiscount(offerProduct.originalPrice, offerProduct.price)

			if (discountPercentaje <= 0) {
				itemView.visibility = View.GONE
				return
			}

			GlideApp.with(activity).load(offerProduct.thumbnailUrl).fitCenter().into(itemView.ivThumbnail)

			itemView.tvTitle.text = offerProduct.title
			itemView.tvSubtitle.text = offerProduct.subtitle

			val originalPrice = formatPrice(offerProduct.originalPrice)
			itemView.tvOriginalPrice.text =
				fromHtml(String.format(activity.getString(R.string.original_price_placeholder, originalPrice)))

			val price = formatPrice(offerProduct.price)
			itemView.tvPrice.text = String.format(
				activity.getString(R.string.price_placeholder, price)
			)

			itemView.tvDiscountPercentaje.text =
				String.format(activity.getString(R.string.discount_percentaje_placeholder), discountPercentaje)

			itemView.setOnClickListener { listener?.onOfferClick(offerProduct.id) }

		}

	}

	interface OfferClickListener {
		fun onOfferClick(productId: Int)
	}

}