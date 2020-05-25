package com.matias.features.ui.main.activityimport android.animation.ObjectAnimatorimport android.annotation.SuppressLintimport android.graphics.Colorimport android.os.Bundleimport android.os.Handlerimport android.view.Menuimport android.view.Viewimport android.view.ViewGroupimport android.widget.Toastimport androidx.appcompat.widget.AppCompatTextViewimport androidx.core.view.GravityCompatimport androidx.core.widget.NestedScrollViewimport androidx.drawerlayout.widget.DrawerLayoutimport androidx.recyclerview.widget.LinearLayoutManagerimport androidx.recyclerview.widget.RecyclerViewimport androidx.vectordrawable.graphics.drawable.ArgbEvaluatorimport com.google.android.material.appbar.AppBarLayoutimport com.google.android.material.floatingactionbutton.FloatingActionButtonimport com.matias.components.toolbar.toolbarmain.StylingToolbarMainModeimport com.matias.components.viewpager.StylingWrapContentHeightViewPagerimport com.matias.core.Constantsimport com.matias.core.base.mvp.BasePresenterActivityimport com.matias.core.helpers.VectorDrawableHelperimport com.matias.core.helpers.dpToPximport com.matias.features.Rimport com.matias.features.di.main.MainActivityModuleimport com.matias.features.di.main.MainActivitySubcomponentimport com.matias.features.ui.MainUiComponentimport com.matias.features.ui.main.MainScreenAnimatorimport com.matias.features.ui.main.adapters.CategoryRecyclerAdapterimport com.matias.features.ui.main.adapters.OffersRecyclerAdapterimport com.matias.features.ui.main.adapters.ServiceRecyclerAdapterimport com.matias.features.ui.main.decorations.ItemCategoryDecorationimport com.matias.features.ui.main.decorations.ItemServiceDecorationimport com.matias.features.ui.main.fragments.home.novelty.NoveltyPagerAdapterimport kotlinx.android.synthetic.main.activity_main.*import kotlinx.android.synthetic.main.app_bar_main.*import javax.inject.Injectprivate const val DELAY_TOOLBAR_ICON_ANIMATION = 350Lclass MainActivity : BasePresenterActivity<		MainActivity,		MainActivityPresenter,		MainActivitySubcomponent>(),	MainActivityContract.View,	View.OnClickListener,	CategoryRecyclerAdapter.CategoryClickListener,	OffersRecyclerAdapter.OfferClickListener,	ServiceRecyclerAdapter.OnServiceClickListener {	// Views.	private lateinit var rootView: ViewGroup	private lateinit var appBar: AppBarLayout	private lateinit var pagerNovelty: StylingWrapContentHeightViewPager	private lateinit var contentMain: ViewGroup	private lateinit var nestedScrollView: NestedScrollView	private lateinit var viewLoading: View	private lateinit var fabContactUs: FloatingActionButton	private lateinit var recyclerCategories: RecyclerView	private lateinit var sectionOffers: ViewGroup	private lateinit var recyclerOffers: RecyclerView	private lateinit var sectionServices: ViewGroup	private lateinit var recyclerServices: RecyclerView	@Inject	lateinit var vectorDrawableHelper: VectorDrawableHelper	@Inject	lateinit var categoryAdapter: CategoryRecyclerAdapter	@Inject	lateinit var noveltyPagerAdapter: NoveltyPagerAdapter	@Inject	lateinit var offerAdapter: OffersRecyclerAdapter	@Inject	lateinit var serviceAdapter: ServiceRecyclerAdapter	private lateinit var screenAnimator: MainScreenAnimator<View>	override fun bindComponent(): MainActivitySubcomponent =		MainUiComponent.component.plus(MainActivityModule(this@MainActivity))	override fun bindLayout(): Int =		R.layout.activity_main	override fun onCreate(savedInstanceState: Bundle?) {		super.onCreate(savedInstanceState)		setContentView(R.layout.activity_main)		setViews()		setupToolbar()		initViews()//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//			val decor = window.decorView//			decor.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR//		}		val viewsToAnimate: List<View> = listOf(pagerNovelty, sectionOffers, sectionServices, contentMain.findViewById<AppCompatTextView>(R.id.tv_mock))		screenAnimator = MainScreenAnimator(viewsToAnimate)	}	/**	 * View instances.	 */	private fun setViews() {		rootView = findViewById(R.id.root_view)		appBar = findViewById(R.id.app_bar)		pagerNovelty = findViewById(R.id.pager_novelty)		contentMain = findViewById(R.id.content_main)		nestedScrollView = contentMain.findViewById(R.id.nested_scroll_view)		viewLoading = contentMain.findViewById(R.id.view_loading)		fabContactUs = findViewById(R.id.fab_contact_us)		recyclerCategories = findViewById(R.id.recycler_categories)		sectionOffers = findViewById(R.id.section_offers)		recyclerOffers = sectionOffers.findViewById(R.id.recycler_offers)		sectionServices = findViewById(R.id.section_services)		recyclerServices = sectionServices.findViewById(R.id.recycler_services)	}	/**	 * Setup toolbar.	 */	private fun setupToolbar() {		setSupportActionBar(toolbar)		toolbar.navigationIcon = null		supportActionBar?.setDisplayHomeAsUpEnabled(false)	}	/**	 * View init status.	 */	private fun initViews() {		// Toolbar init status with search view collapsed		appBar.setExpanded(false, false)	}	/**	 *	 */	private fun initToolbar() {		// Expand search view.		appBar.setExpanded(true, false)		toolbarMain.apply {			setOnCLickListener(this@MainActivity)		}	}	override fun onClick(view: View?) {		when (view?.id) {			R.id.buttonNavigation -> {				if (!drawerLayout.isDrawerOpen(GravityCompat.START)) drawerLayout.openDrawer(GravityCompat.START)				else drawerLayout.closeDrawer(GravityCompat.END)			}			R.id.buttonSearch -> {				showToast("Search")			}			R.id.buttonCoupon -> {				showToast("Coupons")			}			R.id.buttonNotification -> {				showToast("Notifications")			}			R.id.iconFilterSearchButton,			R.id.labelFilterSearchButton -> {				showToast("Filter Search")			}		}	}	override fun onResume() {		super.onResume()		presenter.getMainPosition()	}	override fun onCreateOptionsMenu(menu: Menu): Boolean =		false	override fun onBackPressed() {		val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)		if (drawerLayout.isDrawerOpen(GravityCompat.START)) {			drawerLayout.closeDrawer(GravityCompat.START)		} else {			super.onBackPressed()		}	}	/*******************************************************************************************************************	 * [CategoryRecyclerAdapter.CategoryClickListener] implementation	 */	override fun onCategoryClick(categoryName: CategoryRecyclerAdapter.CategoryName) {		Toast.makeText(this, "Click on category ${categoryName.value}", Toast.LENGTH_SHORT).show()	}	/*******************************************************************************************************************	 * [OffersRecyclerAdapter.OfferClickListener] implementation	 */	override fun onOfferClick(productId: Int) {		val bundle = Bundle()		bundle.putInt(Constants.EXTRA_PRODUCT_ID_KEY, productId)		navigator.toProducDetailsScreen(activityOrigin = this, bundle = bundle, options = null, finish = false)	}	/*******************************************************************************************************************	 * [ServiceRecyclerAdapter.OnServiceClickListener] implementation	 */	override fun onServiceClick() {		Toast.makeText(this, "Click on service", Toast.LENGTH_SHORT).show()	}	/*******************************************************************************************************************	 * [MainActivityContract.View] implementation	 */	override fun showLoadingScreen() {		toolbarMain.setMode(StylingToolbarMainMode.LOADING)		viewLoading.visibility = View.VISIBLE		nestedScrollView.visibility = View.GONE		appBar.setExpanded(false)		rootView.setBackgroundColor(Color.WHITE)		fabContactUs.hide()	}	override fun showErrorScreen() {	}	override fun showInfoScreen() {		screenAnimator.animateIn()		viewLoading.visibility = View.GONE		toolbarMain.setMode(StylingToolbarMainMode.NORMAL)		toolbarMain.animateIcons(vectorDrawableHelper, DELAY_TOOLBAR_ICON_ANIMATION)		nestedScrollView.visibility = View.VISIBLE		appBar.setExpanded(true, true)		rootView.setBackgroundColor(Color.TRANSPARENT)		fabContactUs.show()	}	@SuppressLint("RestrictedApi")	override fun changeStatusBarColor(toColor: Int) {		ObjectAnimator.ofObject(window, "statusBarColor", ArgbEvaluator(), window.statusBarColor, toColor).apply {			duration = 400			start()		}	}	override fun initView() {		recyclerCategories.addItemDecoration(ItemCategoryDecoration())		recyclerServices.addItemDecoration(ItemServiceDecoration(this))		Handler().postDelayed({			initToolbar()			updateView()		}, 500)	}	override fun updateView() {		setupCategoriesRecycler()		setupNoveltyPager()		setupProductOfferRecycler()		setupServicesRecycler()	}	private fun setupCategoriesRecycler() {		recyclerCategories.adapter = categoryAdapter		recyclerCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)		categoryAdapter.setData(			arrayListOf(				CategoryRecyclerAdapter.CategoryModel(CategoryRecyclerAdapter.CategoryName.A),				CategoryRecyclerAdapter.CategoryModel(CategoryRecyclerAdapter.CategoryName.B),				CategoryRecyclerAdapter.CategoryModel(CategoryRecyclerAdapter.CategoryName.C),				CategoryRecyclerAdapter.CategoryModel(CategoryRecyclerAdapter.CategoryName.D)			)		)	}	private fun setupNoveltyPager() {		noveltyPagerAdapter.setData(presenter.getNoveltyList())		pagerNovelty.apply {			adapter = noveltyPagerAdapter			setCurrentItem(noveltyPagerAdapter.count / 2, false)			offscreenPageLimit = 2			clipToPadding = false			this.setPadding(dpToPx(context, 18F).toInt(), 0, dpToPx(context, 18F).toInt(), 0)		}	}	private fun setupProductOfferRecycler() {		recyclerOffers.adapter = offerAdapter		recyclerOffers.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)		offerAdapter.setData(presenter.getProductOfferList())	}	private fun setupServicesRecycler() {		recyclerServices.adapter = serviceAdapter		recyclerServices.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)	}}