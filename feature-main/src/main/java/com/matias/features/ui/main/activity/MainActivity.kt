package com.matias.features.ui.main.activityimport android.animation.ObjectAnimatorimport android.graphics.drawable.AnimatedVectorDrawableimport android.os.Bundleimport android.os.Handlerimport android.view.Menuimport android.view.Viewimport androidx.core.graphics.drawable.DrawableCompatimport androidx.core.view.GravityCompatimport androidx.drawerlayout.widget.DrawerLayoutimport androidx.fragment.app.Fragmentimport androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompatimport androidx.vectordrawable.graphics.drawable.ArgbEvaluatorimport com.matias.components.bottomnavigation.bottomnavmain.StylingBottomNavMainimport com.matias.core.base.mvp.BasePresenterActivityimport com.matias.domain.models.featureflag.Featuresimport com.matias.domain.models.item.ItemMainPageModelimport com.matias.features.Rimport com.matias.features.di.main.MainActivityModuleimport com.matias.features.di.main.MainActivitySubcomponentimport com.matias.features.ui.MainUiComponentimport com.matias.features.ui.main.fragments.contactus.MainContactUsFragmentimport com.matias.features.ui.main.fragments.home.MainHomeFragmentimport com.matias.features.ui.main.fragments.products.MainProductsFragmentimport com.matias.features.ui.main.fragments.profile.MainProfileFragmentimport com.matias.features.ui.main.fragments.shopcart.MainShopCartFragmentimport kotlinx.android.synthetic.main.activity_main.*import kotlinx.android.synthetic.main.app_bar_main.*import kotlinx.android.synthetic.main.content_main.*import kotlinx.android.synthetic.main.toolbar_main.*class MainActivity : BasePresenterActivity<MainActivity, MainActivityPresenter, MainActivitySubcomponent>(),	MainActivityContract.View {	private var itemMainPageList: MutableList<ItemMainPageModel> = mutableListOf()	private val tagList: List<Features> =		listOf(Features.TAB_HOME, Features.TAB_PRODUCTS, Features.TAB_SHOP_CART, Features.TAB_CONTACT_US, Features.TAB_PROFILE)	private lateinit var labelList: Array<String>	private val fragmentList: List<Fragment> =		listOf(			MainHomeFragment.newInstance(),			MainProductsFragment.newInstance(),			MainShopCartFragment.newInstance(),			MainContactUsFragment.newInstance(),			MainProfileFragment.newInstance()		)	private val iconList: IntArray =		intArrayOf(			R.drawable.ic_bottom_nav_home_32dp,			R.drawable.ic_bottom_nav_products_32dp,			R.drawable.ic_bottom_nav_shop_cart_32dp,			R.drawable.ic_bottom_nav_contact_32dp,			R.drawable.ic_bottom_nav_profile_32dp		)	override fun bindComponent(): MainActivitySubcomponent =		MainUiComponent.component.plus(MainActivityModule())	override fun bindLayout(): Int =		R.layout.activity_main	override fun onCreate(savedInstanceState: Bundle?) {		super.onCreate(savedInstanceState)		setContentView(R.layout.activity_main)		labelList = resources.getStringArray(R.array.styling_bottom_nav_labels)		for (i in 0 until 5) {			itemMainPageList.add(				ItemMainPageModel(					tagList[i],					labelList[i],					iconList[i],					fragmentList[i],					presenter.getTabEnable(tagList[i])				)			)		}		setupToolbar()		setupBottomNavigationView()		setupPager()	}	private fun setupToolbar() {		setSupportActionBar(toolbar)		toolbar.navigationIcon = null		supportActionBar?.setDisplayHomeAsUpEnabled(false)		buttonNavigation.setOnClickListener {			if (!drawerLayout.isDrawerOpen(GravityCompat.START)) drawerLayout.openDrawer(GravityCompat.START)			else drawerLayout.closeDrawer(GravityCompat.END)		}		buttonSearch.setOnClickListener { onToolbarOptionButtonClick(it.id) }		buttonNotification.setOnClickListener { onToolbarOptionButtonClick(it.id) }		iconFilterSearchButton.setOnClickListener { onToolbarOptionButtonClick(it.id) }		labelFilterSearchButton.setOnClickListener { onToolbarOptionButtonClick(it.id) }		// TODO handle propperly.		Handler().postDelayed({			val wrappedDrawableA = DrawableCompat.wrap(buttonNotification.drawable)			buttonNotification.setImageDrawable(wrappedDrawableA)			val dA = buttonNotification.drawable			if (dA is AnimatedVectorDrawableCompat) dA.start()			else if (dA is AnimatedVectorDrawable) dA.start()			val wrappedDrawableB = DrawableCompat.wrap(buttonNavigation.drawable)			buttonNavigation.setImageDrawable(wrappedDrawableB)			val dB = buttonNavigation.drawable			if (dB is AnimatedVectorDrawableCompat) dB.start()			else if (dB is AnimatedVectorDrawable) dB.start()		}, 300)	}	/**	 *	 */	private fun setupBottomNavigationView() {		// TODO Mannage botton nav icon badges.		bottomNav.setup(itemMainPageList, onBottomNavMainItemSelectedListener)	}	/**	 * Setup the pager that will contain all the main fragments.	 */	private fun setupPager() {		pager.adapter = MainPagerAdapter(itemMainPageList, supportFragmentManager, 0)		pager.setSwipePagingEnabled(false)	}	override fun onCreateOptionsMenu(menu: Menu): Boolean =		false	override fun onBackPressed() {		val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)		if (drawerLayout.isDrawerOpen(GravityCompat.START)) {			drawerLayout.closeDrawer(GravityCompat.START)		} else {			super.onBackPressed()		}	}	/**	 *	 */	private val onBottomNavMainItemSelectedListener: StylingBottomNavMain.SimpleOnBottomNavMainItemSelectedListener =		object : StylingBottomNavMain.SimpleOnBottomNavMainItemSelectedListener() {			override fun onItemSelected(view: View?) {				super.onItemSelected(view)				view?.let { onBottomNavItemSelected(it.tag as Features) }			}		}	/*******************************************************************************************************************	 * [MainActivityContract.View] implementation	 */	override fun onBottomNavItemSelected(tag: Features) {		when (tag) {			Features.TAB_HOME -> pager.setCurrentItem(0, false)			Features.TAB_PRODUCTS -> pager.setCurrentItem(1, false)			Features.TAB_SHOP_CART -> pager.setCurrentItem(2, false)			Features.TAB_CONTACT_US -> pager.setCurrentItem(3, false)			Features.TAB_PROFILE -> pager.setCurrentItem(4, false)			else -> {			}		}	}	override fun changeStatusBarColor(toColor: Int) {		ObjectAnimator.ofObject(window, "statusBarColor", ArgbEvaluator(), window.statusBarColor, toColor).apply {			duration = 400			start()		}	}	override fun onToolbarOptionButtonClick(id: Int) {		when (id) {			R.id.buttonSearch -> {				showToast("Search")			}			R.id.buttonNotification -> {				showToast("Notofications")			}			R.id.iconFilterSearchButton, R.id.labelFilterSearchButton -> {				showToast("Filter search")			}		}	}}