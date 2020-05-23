package com.matias.features.ui.main.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.matias.features.R

class ServiceRecyclerAdapter(private val activity: Activity) : RecyclerView.Adapter<ServiceRecyclerAdapter.ServiceViewHolder>() {

	private var data: List<ServiceModel> = listOf(
		ServiceModel(
			R.drawable.ic_service_gift_card_128dp,
			R.string.service_gift_card_title,
			R.string.service_gift_card_description
		),
		ServiceModel(
			R.drawable.ic_service_virtual_charging_128dp,
			R.string.service_virtual_charging_title,
			R.string.service_virtual_charging_description
		),
		ServiceModel(
			R.drawable.ic_service_tech_support_128dp,
			R.string.service_tech_support_title,
			R.string.service_tech_support_description
		),
		ServiceModel(
			R.drawable.ic_service_custom_case_128dp,
			R.string.service_custom_case_title,
			R.string.service_custom_case_description
		)
	)

	private var listener: OnServiceClickListener?

	init {
		if (activity is OnServiceClickListener) {
			listener = activity
		} else {
			throw IllegalAccessException("Calling activity must implement ServiceRecyclerAdapter.OnServiceClickListener interface")
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder =
		ServiceViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_service, parent, false))

	override fun getItemCount(): Int =
		data.size

	override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
		holder.bindItem(data[position], listener, activity)
	}

	class ServiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		private var ivIcon: AppCompatImageView = itemView.findViewById(R.id.ic_icon)
		private var tvTitle: AppCompatTextView = itemView.findViewById(R.id.tv_title)
		private var tvDescriptinon: AppCompatTextView = itemView.findViewById(R.id.tv_description)

		fun bindItem(service: ServiceModel, listener: OnServiceClickListener?, activity: Activity) {
			ivIcon.setImageDrawable(activity.getDrawable(service.imageDrawableRes))
			tvTitle.text = activity.getString(service.titleTextRes)
			tvDescriptinon.text = activity.getString(service.descriptionTextRes)
		}

	}

	interface OnServiceClickListener {
		fun onServiceClick()
	}

	data class ServiceModel(
		val imageDrawableRes: Int,
		val titleTextRes: Int,
		val descriptionTextRes: Int
	)

}