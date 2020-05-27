package com.matias.features.ui.productdetails.activity

import android.os.Bundle
import android.view.View
import com.matias.core.Constants
import com.matias.core.base.mvp.BasePresenterActivity
import com.matias.core.helpers.fromHtml
import com.matias.domain.models.product.ProductModel
import com.matias.features.R
import com.matias.features.di.activity.ProductDetailsActivityModule
import com.matias.features.di.activity.ProductDetailsActivitySubcomponent
import com.matias.features.ui.productdetails.ProductDetailsUiComponent
import kotlinx.android.synthetic.main.activity_product_details.*
import kotlinx.android.synthetic.main.product_detail_section_1.*
import kotlinx.android.synthetic.main.product_detail_section_2.*
import kotlinx.android.synthetic.main.product_detail_section_3.*
import kotlinx.android.synthetic.main.product_detail_section_4.*
import java.text.DecimalFormat

class ProductDetailsActivity :
	BasePresenterActivity<ProductDetailsActivity, ProductDetailsActivityPresenter, ProductDetailsActivitySubcomponent>(),
	ProductDetailsActivityContract.View {

	override fun bindComponent(): ProductDetailsActivitySubcomponent =
		ProductDetailsUiComponent.component.plus(ProductDetailsActivityModule())

	override fun bindLayout(): Int =
		R.layout.activity_product_details

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_product_details)
		setSupportActionBar(toolbar)

		val productId = intent.extras?.getInt(Constants.EXTRA_PRODUCT_ID_KEY) ?: throw IllegalAccessException("Product ID can´t be null.")
		presenter.getProduct(productId)
	}

	/**
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
		product.let {p: ProductModel ->
			// Section 1
			if (isNewProduct || isOfferProduct || isFeaturedProduct) {
				cvTagNew.visibility = if (isNewProduct) View.VISIBLE else View.GONE
				cvTagOffer.visibility = if (isOfferProduct) View.VISIBLE else View.GONE
				cvTagFeatured.visibility = if (isFeaturedProduct) View.VISIBLE else View.GONE
			} else {
				llNewOfferFeatured.visibility = View.GONE
			}

			tvTitle.text = p.title
			tvSubtitle.text = p.subtitle
			when (isOfferProduct) {
				true -> {
					tvOriginalPrice.text = fromHtml(String.format(getString(R.string.product_details_original_price_placeholder), getFormattedPrice(p.originalPrice)))
					tTagvSave.text = String.format(getString(R.string.product_details_save_placeholder), getDiscount(p.originalPrice, p.price))
				}
				false -> {
					tvOriginalPrice.visibility = View.GONE
					cvTagSave.visibility = View.GONE
				}
			}

			tvPrice.text = String.format(getString(R.string.product_details_price_placeholder), getFormattedPrice(p.price))

			// Section 2
			tvTagAvailableQuantity.text = String.format(getString(R.string.product_details_available_quantity_placeholder), p.quantity.available)
			tvSoldQuantity.text = String.format(getString(R.string.product_details_sold_quantity_placeholder), p.quantity.sold)
			ratingBar.rating = p.rating
			tvRatingScore.text = DecimalFormat("#.#").format(p.rating).replace('.', ',')

			// TODO mannage tvViewAllComments click event

			// TODO mannage cvAddToCartBtn & cvBuyBtn enabled/disable status
			// TODO mannage cvAddToCartBtn & cvBuyBtn click event

			// Section 3
			tvDescription.text = fromHtml(p.description)

			// Section 4
			tvWarranty.text = p.warranty

		}
	}

	private fun getDiscount(originalPrice: Float, price: Float): Int {
		return 100 - ((price * 100) / originalPrice).toInt()
	}

	private fun getFormattedPrice(value: Float): String =
		DecimalFormat("#,###").format(value).replace(',', '.')
	
}
