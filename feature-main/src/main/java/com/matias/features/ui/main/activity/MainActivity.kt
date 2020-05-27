package com.matias.features.ui.main.activityimport android.animation.ObjectAnimatorimport android.annotation.SuppressLintimport android.graphics.Colorimport android.os.Bundleimport android.os.Handlerimport android.view.Menuimport android.view.Viewimport android.widget.Toastimport androidx.core.view.GravityCompatimport androidx.drawerlayout.widget.DrawerLayoutimport androidx.recyclerview.widget.LinearLayoutManagerimport androidx.vectordrawable.graphics.drawable.ArgbEvaluatorimport com.matias.components.toolbar.toolbarmain.StylingToolbarMainModeimport com.matias.core.Constantsimport com.matias.core.base.mvp.BasePresenterActivityimport com.matias.core.helpers.VectorDrawableHelperimport com.matias.core.helpers.dpToPximport com.matias.features.Rimport com.matias.features.di.main.MainActivityModuleimport com.matias.features.di.main.MainActivitySubcomponentimport com.matias.features.ui.MainUiComponentimport com.matias.features.ui.main.MainScreenAnimatorimport com.matias.features.ui.main.adapters.CategoryRecyclerAdapterimport com.matias.features.ui.main.adapters.NewProductRecyclerAdapterimport com.matias.features.ui.main.adapters.OfferProductRecyclerAdapterimport com.matias.features.ui.main.adapters.ServiceRecyclerAdapterimport com.matias.features.ui.main.decorations.ItemCategoryDecorationimport com.matias.features.ui.main.decorations.ItemNewProductDecorationimport com.matias.features.ui.main.decorations.ItemServiceDecorationimport com.matias.features.ui.main.fragments.home.novelty.NoveltyPagerAdapterimport kotlinx.android.synthetic.main.activity_main.*import kotlinx.android.synthetic.main.app_bar_main.*import kotlinx.android.synthetic.main.content_main.*import kotlinx.android.synthetic.main.content_main.view.*import kotlinx.android.synthetic.main.search_bar_main.*import kotlinx.android.synthetic.main.section_offers.*import kotlinx.android.synthetic.main.section_services.*import javax.inject.Injectprivate const val DELAY_TOOLBAR_ICON_ANIMATION = 350Lclass MainActivity : BasePresenterActivity<		MainActivity,		MainActivityPresenter,		MainActivitySubcomponent>(),	MainActivityContract.View,	View.OnClickListener,	CategoryRecyclerAdapter.CategoryClickListener,	NewProductRecyclerAdapter.NewProductClickListener,	OfferProductRecyclerAdapter.OfferClickListener,	ServiceRecyclerAdapter.OnServiceClickListener {	@Inject	lateinit var vectorDrawableHelper: VectorDrawableHelper	@Inject	lateinit var categoryAdapter: CategoryRecyclerAdapter	@Inject	lateinit var noveltyPagerAdapter: NoveltyPagerAdapter	@Inject	lateinit var newProductRecyclerAdapter: NewProductRecyclerAdapter	@Inject	lateinit var offerProductAdapter: OfferProductRecyclerAdapter	@Inject	lateinit var serviceAdapter: ServiceRecyclerAdapter	private lateinit var screenAnimator: MainScreenAnimator<View>	override fun bindComponent(): MainActivitySubcomponent =		MainUiComponent.component.plus(MainActivityModule(this@MainActivity))	override fun bindLayout(): Int =		R.layout.activity_main	override fun onCreate(savedInstanceState: Bundle?) {		super.onCreate(savedInstanceState)		setContentView(R.layout.activity_main)		setupToolbar()		initViews()//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//			val decor = window.decorView//			decor.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR//		}		presenter.getMainPosition()		val viewsToAnimate: List<View> = listOf(pagerNovelty, sectionNew, sectionOffers, sectionServices)		screenAnimator = MainScreenAnimator(viewsToAnimate)	}	/**	 * Setup toolbar.	 */	private fun setupToolbar() {		setSupportActionBar(toolbar)		toolbar.navigationIcon = null		supportActionBar?.setDisplayHomeAsUpEnabled(false)	}	/**	 * View init status.	 */	private fun initViews() {		// Toolbar init status with search view collapsed		appBar.setExpanded(false, false)	}	/**	 *	 */	private fun initToolbar() {		// Expand search view.		appBar.setExpanded(true, false)		toolbarMain.apply {			setOnCLickListener(this@MainActivity)		}	}	override fun onClick(view: View?) {		when (view?.id) {			R.id.buttonNavigation -> {				if (!drawerLayout.isDrawerOpen(GravityCompat.START)) drawerLayout.openDrawer(GravityCompat.START)				else drawerLayout.closeDrawer(GravityCompat.END)			}			R.id.buttonSearch -> {				showToast("Search")			}			R.id.buttonCoupon -> {				showToast("Coupons")			}			R.id.buttonNotification -> {				showToast("Notifications")			}			R.id.iconFilterSearchButton,			R.id.labelFilterSearchButton -> {				showToast("Filter Search")			}		}	}	override fun onResume() {		super.onResume()	}	override fun onCreateOptionsMenu(menu: Menu): Boolean =		false	override fun onBackPressed() {		val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)		if (drawerLayout.isDrawerOpen(GravityCompat.START)) {			drawerLayout.closeDrawer(GravityCompat.START)		} else {			super.onBackPressed()		}	}	/*******************************************************************************************************************	 * [CategoryRecyclerAdapter.CategoryClickListener] implementation	 */	override fun onCategoryClick(categoryName: CategoryRecyclerAdapter.CategoryName) {		Toast.makeText(this, "Click on category ${categoryName.value}", Toast.LENGTH_SHORT).show()	}	/*******************************************************************************************************************	 * [NewProductRecyclerAdapter.NewProductClickListener] implementation	 */	override fun onNewProductClick(productId: Int) {		val bundle = Bundle()		bundle.putInt(Constants.EXTRA_PRODUCT_ID_KEY, productId)		navigator.toProducDetailsScreen(activityOrigin = this, bundle = bundle, options = null, finish = false)	}	/*******************************************************************************************************************	 * [OfferProductRecyclerAdapter.OfferClickListener] implementation	 */	override fun onOfferClick(productId: Int) {		val bundle = Bundle()		bundle.putInt(Constants.EXTRA_PRODUCT_ID_KEY, productId)		navigator.toProducDetailsScreen(activityOrigin = this, bundle = bundle, options = null, finish = false)	}	/*******************************************************************************************************************	 * [ServiceRecyclerAdapter.OnServiceClickListener] implementation	 */	override fun onServiceClick() {		Toast.makeText(this, "Click on service", Toast.LENGTH_SHORT).show()	}	/*******************************************************************************************************************	 * [MainActivityContract.View] implementation	 */	override fun showLoadingScreen() {		toolbarMain.setMode(StylingToolbarMainMode.LOADING)		viewLoading.visibility = View.VISIBLE		contentMain.visibility = View.GONE		appBar.setExpanded(false)		rootView.setBackgroundColor(Color.WHITE)		fabContactUs.hide()	}	override fun showErrorScreen() {	}	override fun showInfoScreen() {		screenAnimator.animateIn()		viewLoading.visibility = View.GONE		toolbarMain.setMode(StylingToolbarMainMode.NORMAL)		toolbarMain.animateIcons(vectorDrawableHelper, DELAY_TOOLBAR_ICON_ANIMATION)		contentMain.visibility = View.VISIBLE		appBar.setExpanded(true, true)		rootView.setBackgroundColor(Color.TRANSPARENT)		fabContactUs.show()	}	@SuppressLint("RestrictedApi")	override fun changeStatusBarColor(toColor: Int) {		ObjectAnimator.ofObject(window, "statusBarColor", ArgbEvaluator(), window.statusBarColor, toColor).apply {			duration = 400			start()		}	}	override fun initView() {		recyclerCategories.addItemDecoration(ItemCategoryDecoration())		recyclerNewProduct.addItemDecoration(ItemNewProductDecoration())		recyclerServices.addItemDecoration(ItemServiceDecoration(this))		Handler().postDelayed({			initToolbar()			updateView()		}, 500)	}	override fun updateView() {		setupCategoriesRecycler()		setupNoveltyPager()		setupNewRecycler()		setupOffersRecycler()		setupServicesRecycler()	}	private fun setupCategoriesRecycler() {		recyclerCategories.adapter = categoryAdapter		recyclerCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)		categoryAdapter.setData(			arrayListOf(				CategoryRecyclerAdapter.CategoryModel(CategoryRecyclerAdapter.CategoryName.A),				CategoryRecyclerAdapter.CategoryModel(CategoryRecyclerAdapter.CategoryName.B),				CategoryRecyclerAdapter.CategoryModel(CategoryRecyclerAdapter.CategoryName.C),				CategoryRecyclerAdapter.CategoryModel(CategoryRecyclerAdapter.CategoryName.D)			)		)	}	private fun setupNoveltyPager() {		noveltyPagerAdapter.setData(presenter.getNoveltyList())		pagerNovelty.apply {			adapter = noveltyPagerAdapter			setCurrentItem(noveltyPagerAdapter.count / 2, false)			offscreenPageLimit = 2			clipToPadding = false			this.setPadding(dpToPx(context, 18F).toInt(), 0, dpToPx(context, 18F).toInt(), 0)		}	}	private fun setupNewRecycler() {		recyclerNewProduct.adapter = newProductRecyclerAdapter		recyclerNewProduct.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)		newProductRecyclerAdapter.setData(presenter.getNewProductList())	}	private fun setupOffersRecycler() {		recyclerOfferProduct.adapter = offerProductAdapter		recyclerOfferProduct.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)		offerProductAdapter.setData(presenter.getProductOfferList())	}	private fun setupServicesRecycler() {		recyclerServices.adapter = serviceAdapter		recyclerServices.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)	}}