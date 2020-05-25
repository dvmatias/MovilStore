package com.matias.data.cache.products

import com.matias.domain.models.product.ProductModel
import java.util.concurrent.ArrayBlockingQueue

class ProductCacheImpl : ProductCache {

	private val productQueue: ProductQueue<ProductModel> = ProductQueue(2)

	override fun insertProduct(product: ProductModel) {
		productQueue.add(product)
	}

	override fun getProduct(productId: Int): ProductModel? =
		productQueue.getProduct(productId)

	internal class ProductQueue<E>(private val fixedSize: Int) : ArrayBlockingQueue<E>(fixedSize) {

		override fun add(element: E): Boolean {
			if (super.size == fixedSize) {
				remove()
			}

			return super.add(element)
		}

		fun getProduct(productId: Int): ProductModel? {
			this.forEach {
				val id = (it as ProductModel).id
				if (id == productId) {
					return it
				}
			}
			return null
		}
	}
}