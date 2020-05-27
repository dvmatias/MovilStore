package com.matias.core.helpers

fun getDiscount(originalPrice: Float, price: Float): Int =
	100 - ((price * 100) / originalPrice).toInt()