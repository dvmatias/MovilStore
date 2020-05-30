package com.matias.features.ui.productdetails.activity

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.matias.core.Constants
import com.matias.core.base.mvp.BasePresenterActivity
import com.matias.core.helpers.formatPrice
import com.matias.core.helpers.formatPriceWithCurrency
import com.matias.core.helpers.fromHtml
import com.matias.core.helpers.getDiscount
import com.matias.core.managers.GlideApp
import com.matias.domain.models.product.ProductModel
import com.matias.domain.models.product.StatusEnum
import com.matias.features.R
import com.matias.features.di.activity.ProductDetailsActivityModule
import com.matias.features.di.activity.ProductDetailsActivitySubcomponent
import com.matias.features.ui.productdetails.ProductDetailsUiComponent
import com.matias.features.ui.productdetails.adapters.RecyclerMultimediaAdapter
import kotlinx.android.synthetic.main.activity_product_details.*
import kotlinx.android.synthetic.main.content_product_details.*
import kotlinx.android.synthetic.main.product_detail_section_description.*
import kotlinx.android.synthetic.main.product_detail_section_price.*
import kotlinx.android.synthetic.main.product_detail_section_shipping.*
import kotlinx.android.synthetic.main.product_detail_section_warranty.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.text.DecimalFormat
import javax.inject.Inject

class ProductDetailsActivity :
	BasePresenterActivity<ProductDetailsActivity, ProductDetailsActivityPresenter, ProductDetailsActivitySubcomponent>(),
	ProductDetailsActivityContract.View {

	private lateinit var product: ProductModel
	private var isNew: Boolean = false
	private var isOffer: Boolean = false
	private var isFeatured: Boolean = false

	@Inject
	lateinit var multimediaAdapter: RecyclerMultimediaAdapter

	override fun bindComponent(): ProductDetailsActivitySubcomponent =
		ProductDetailsUiComponent.component.plus(ProductDetailsActivityModule(this))

	override fun bindLayout(): Int =
		R.layout.activity_product_details

	@ExperimentalCoroutinesApi
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_product_details)

		setupToolbar()

		val productId = intent.extras?.getInt(Constants.EXTRA_PRODUCT_ID_KEY) ?: throw IllegalAccessException("Product ID can´t be null.")
		presenter.getProduct(productId)
	}

	private fun setupToolbar() {
		setSupportActionBar(toolbar)
		collapsingToolbarLayout.isTitleEnabled = false
		supportActionBar?.title = ""
		supportActionBar?.setHomeButtonEnabled(true)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)
	}

	private fun getStatusString(status: StatusEnum): String =
		when (status) {
			StatusEnum.INACTIVE -> getString(R.string.product_details_status_inactive_text)
			StatusEnum.PAUSED -> getString(R.string.product_details_status_paused_text)
			else -> ""
		}

	/**************************************************************************************************************************************************************************************
	 * [ProductDetailsActivityContract.View] implementation.
	 */

	override fun showLoadingScreen() {
		viewLoading.visibility = View.VISIBLE
		contentProductDetails.visibility = View.GONE
		appBar.visibility = View.GONE
		containerFloatingSection.visibility = View.GONE
	}

	override fun showErrorScreen() {

	}

	override fun showInfoScreen() {
		viewLoading.visibility = View.GONE
		contentProductDetails.visibility = View.VISIBLE
		appBar.visibility = View.VISIBLE
		containerFloatingSection.visibility = View.VISIBLE
	}

	override fun setProduct(product: ProductModel) {
		this.product = product
	}

	override fun setIsNew(isNew: Boolean) {
		this.isNew = isNew
	}

	override fun setIsOffer(isOffer: Boolean) {
		this.isOffer = isOffer
	}

	override fun setIsFeatured(isFeatured: Boolean) {
		this.isFeatured = isFeatured
	}

	override fun setProductInfo() {
		GlideApp.with(this).load(product.thumbnailUrl).into(ivFloatingThumnail)
		tvFloatingTitle.text = product.title
		tvFloatingPrice.text = formatPriceWithCurrency(product.price)
		cvFloatingBuyBtn.setOnClickListener { onUserClickBuyButton() }

		setupRecyclerMultimedia()
		setupSectionPrice()

		tvTagAvailableQuantity.text = String.format(getString(R.string.product_details_available_quantity_placeholder), product.quantity.available)
		tvSoldQuantity.text = String.format(getString(R.string.product_details_sold_quantity_placeholder), product.quantity.sold)

		tvDescription.text = fromHtml(product.description)
		tvWarranty.text = product.warranty
	}

	private fun setupRecyclerMultimedia() {
		recyclerMultimedia.layoutManager =
			LinearLayoutManager(this@ProductDetailsActivity, LinearLayoutManager.HORIZONTAL, false)
		recyclerMultimedia.adapter = multimediaAdapter
		PagerSnapHelper().attachToRecyclerView(recyclerMultimedia)

		multimediaAdapter.setData(product.images, product.imageUrl, product.promotionImageUrl)
	}

	private fun setupSectionPrice() {
		if (StatusEnum.ACTIVE != product.status) {
			tvStatus.text = String.format(getString(R.string.product_details_status_placeholder), getStatusString(product.status))
			tvStatus.visibility = View.VISIBLE
		}

		if (isNew || isOffer || isFeatured) {
			cvTagNew.visibility = if (isNew) View.VISIBLE else View.GONE
			cvTagOffer.visibility = if (isOffer) View.VISIBLE else View.GONE
			cvTagFeatured.visibility = if (isFeatured) View.VISIBLE else View.GONE
		} else {
			llNewOfferFeatured.visibility = View.GONE
		}

		tvTitle.text = product.title
		tvSubtitle.text = product.subtitle
		when (isOffer) {
			true -> {
				tvOriginalPrice.text = fromHtml(String.format(getString(R.string.product_details_original_price_placeholder), formatPrice(product.originalPrice)))
				tTagvSave.text = String.format(getString(R.string.product_details_save_placeholder), getDiscount(product.originalPrice, product.price))
			}
			false -> {
				tvOriginalPrice.visibility = View.GONE
				cvTagSave.visibility = View.GONE
			}
		}

		product.rating.let { rating: ProductModel.RatingModel ->
			ratingBar.rating = rating.value
			tvRatingScore.text = DecimalFormat("#.#").format(rating.value).replace(',', '.')
			tvRatingQuantity.text = resources.getQuantityString(
				R.plurals.product_details_rating_quantity_plurals,
				rating.quantity,
				rating.quantity
			)
		}

		tvViewAllComments.setOnClickListener { onUserClickViewAllCommentsBtn() }

		tvPrice.text = String.format(getString(R.string.product_details_price_placeholder), formatPrice(product.price))

		// TODO Manage favorite icon status.
		tvFavoriteBtn.setOnClickListener { onUserClickFavoriteBtn() }
		tvShareBtn.setOnClickListener { onUserClickShareBtn() }
	}

	override fun onUserClickShareBtn() {
		// TODO
		super.showToast("Click on Share")
	}

	override fun onUserClickFavoriteBtn() {
		// TODO
		super.showToast("Click on Favorite")
	}

	override fun onUserClickViewAllCommentsBtn() {
		// TODO
		super.showToast("Click on View All Comments")
	}


	override fun onUserClickBuyButton() {
		// TODO
		super.showToast("Click on Buy")
	}

}