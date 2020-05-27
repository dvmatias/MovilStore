package com.matias.core.helpers

import java.text.DecimalFormat

/**
 *
 */
fun formatPrice(value: Float): String =
	DecimalFormat("#,###").format(value).replace(',', '.')