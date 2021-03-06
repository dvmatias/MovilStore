package com.matias.data.mappers.product

import com.matias.data.entities.product.ProductEntity
import com.matias.domain.mapper.Mapper
import com.matias.domain.models.product.ProductModel
import com.matias.domain.models.product.StatusEnum

class ProductMapper : Mapper<ProductEntity, ProductModel>() {

	override fun transformEntityToModel(e: ProductEntity): ProductModel {
		val id: Int = e.id?.toInt() ?: -1
		val price: Float = e.price ?: 0F
		val originalPrice: Float = e.originalPrice ?: 0F
		val title: String = e.title ?: ""
		val subtitle: String = e.subtitle ?: ""
		val description: String = e.description ?: ""
		val status: StatusEnum = transformStatus(e.status)
		val warranty: String = e.warranty ?: ""
		val imageUrl: String = e.imageUrl ?: ""
		val thumbnailUrl: String = e.thumbnailUrl ?: ""
		val secureThumbnailUrl: String = e.secureThumbnailUrl ?: ""
		val promotionImageUrl: String = e.promotionImageUrl ?: ""
		val tags: ArrayList<String> = arrayListOf()
		val quantity: ProductModel.QuantityModel = transformQuantity(e.quantity)
		val images: List<ProductModel.ImageModel> = transformImages(e.images)
		val rating: ProductModel.RatingModel = transformRating(e.rating)

		return ProductModel(
			id,
			price,
			originalPrice,
			title,
			subtitle,
			description,
			status,
			warranty,
			imageUrl,
			thumbnailUrl,
			secureThumbnailUrl,
			promotionImageUrl,
			tags,
			quantity,
			images,
			rating
		)
	}

	override fun transformModelToEntity(m: ProductModel): ProductEntity {
		return super.transformModelToEntity(m)
	}

	private fun transformQuantity(e: ProductEntity.QuantityEntity?): ProductModel.QuantityModel =
		ProductModel.QuantityModel(
			available = e?.available ?: 0,
			sold = e?.sold ?: 0
		)

	private fun transformStatus(e: String?): StatusEnum =
		when (e) {
			StatusEnum.ACTIVE.value -> StatusEnum.ACTIVE
			StatusEnum.INACTIVE.value -> StatusEnum.INACTIVE
			StatusEnum.PAUSED.value -> StatusEnum.PAUSED
			else -> StatusEnum.INACTIVE
		}

	private fun transformImages(e: List<ProductEntity.ImageEntity>?): List<ProductModel.ImageModel> =
		e?.map {
			ProductModel.ImageModel(
				it.url ?: ""
			)
		} ?: arrayListOf()


	private fun transformRating(e: ProductEntity.RatingEntity?): ProductModel.RatingModel =
		ProductModel.RatingModel(
			value = e?.value ?: 0F,
			quantity = e?.quantity ?: 0
		)

}