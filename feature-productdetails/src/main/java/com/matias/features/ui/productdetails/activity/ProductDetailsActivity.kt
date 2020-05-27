package com.matias.features.ui.productdetails.activity

import android.os.Bundle
import android.view.View
import com.matias.core.Constants
import com.matias.core.base.mvp.BasePresenterActivity
import com.matias.core.helpers.formatPrice
import com.matias.core.helpers.fromHtml
import com.matias.core.helpers.getDiscount
import com.matias.domain.models.product.ProductModel
import com.matias.domain.models.product.StatusEnum
import com.matias.features.R
import com.matias.features.di.activity.ProductDetailsActivityModule
import com.matias.features.di.activity.ProductDetailsActivitySubcomponent
import com.matias.features.ui.productdetails.ProductDetailsUiComponent
import kotlinx.android.synthetic.main.activity_product_details.*
import kotlinx.android.synthetic.main.content_product_details.*
import kotlinx.android.synthetic.main.product_detail_section_1.*
import kotlinx.android.synthetic.main.product_detail_section_2.*
import kotlinx.android.synthetic.main.product_detail_section_3.*
import kotlinx.android.synthetic.main.product_detail_section_4.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.text.DecimalFormat

class ProductDetailsActivity :
	BasePresenterActivity<ProductDetailsActivity, ProductDetailsActivityPresenter, ProductDetailsActivitySubcomponent>(),
	ProductDetailsActivityContract.View {

	override fun bindComponent(): ProductDetailsActivitySubcomponent =
		ProductDetailsUiComponent.component.plus(ProductDetailsActivityModule())

	override fun bindLayout(): Int =
		R.layout.activity_product_details

	@ExperimentalCoroutinesApi
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_product_details)
		setSupportActionBar(toolbar)

		val productId = intent.extras?.getInt(Constants.EXTRA_PRODUCT_ID_KEY) ?: throw IllegalAccessException("Product ID can´t be null.")
		presenter.getProduct(productId)
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
	}

	override fun showErrorScreen() {

	}

	override fun showInfoScreen() {
		viewLoading.visibility = View.GONE
		contentProductDetails.visibility = View.VISIBLE
		appBar.visibility = View.VISIBLE
	}

	override fun setProductInfo(product: ProductModel, isNewProduct: Boolean, isOfferProduct: Boolean, isFeaturedProduct: Boolean) {
		// Section 1
		if (StatusEnum.ACTIVE != product.status) {
			tvStatus.text = String.format(getString(R.string.product_details_status_placeholder), getStatusString(product.status))
			tvStatus.visibility = View.VISIBLE
		}

		if (isNewProduct || isOfferProduct || isFeaturedProduct) {
			cvTagNew.visibility = if (isNewProduct) View.VISIBLE else View.GONE
			cvTagOffer.visibility = if (isOfferProduct) View.VISIBLE else View.GONE
			cvTagFeatured.visibility = if (isFeaturedProduct) View.VISIBLE else View.GONE
		} else {
			llNewOfferFeatured.visibility = View.GONE
		}

		tvTitle.text = product.title
		tvSubtitle.text = product.subtitle
		when (isOfferProduct) {
			true -> {
				tvOriginalPrice.text = fromHtml(String.format(getString(R.string.product_details_original_price_placeholder), formatPrice(product.originalPrice)))
				tTagvSave.text = String.format(getString(R.string.product_details_save_placeholder), getDiscount(product.originalPrice, product.price))
			}
			false -> {
				tvOriginalPrice.visibility = View.GONE
				cvTagSave.visibility = View.GONE
			}
		}

		tvPrice.text = String.format(getString(R.string.product_details_price_placeholder), formatPrice(product.price))

		// Section 2
		tvTagAvailableQuantity.text = String.format(getString(R.string.product_details_available_quantity_placeholder), product.quantity.available)
		tvSoldQuantity.text = String.format(getString(R.string.product_details_sold_quantity_placeholder), product.quantity.sold)
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

		if (product.status != StatusEnum.ACTIVE) {
			cvAddToCartBtn.visibility = View.GONE
			cvBuyBtn.visibility = View.GONE
		} else {
			cvAddToCartBtn.setOnClickListener { onUserClickAddToCartButton() }
			cvBuyBtn.setOnClickListener { onUserClickBuyButton() }
		}

		// Section 3
		tvDescription.text = fromHtml(product.description)

		// Section 4
		tvWarranty.text = product.warranty
	}

	override fun onUserClickViewAllCommentsBtn() {
		super.showToast("Click on View All Comments")
	}

	override fun onUserClickAddToCartButton() {
		super.showToast("Click on Add To Cart")
	}

	override fun onUserClickBuyButton() {
		super.showToast("Click on Buy")
	}

}