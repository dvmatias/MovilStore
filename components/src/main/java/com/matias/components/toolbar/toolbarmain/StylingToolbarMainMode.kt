package com.matias.components.toolbar.toolbarmain

import com.matias.components.R

enum class StylingToolbarMainMode(val footerLayoutId: Int?) {
	INIT(null),
	NO_FOOTER(null),
	TO_SEARCH(R.layout.styling_toolbar_main_footer_to_search),
	FOR_SEARCH(null),
	CONTACT_US(null),
	PROFILE(null)
}