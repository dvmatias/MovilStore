package com.matias.features.ui.main.activityimport android.animation.ObjectAnimatorimport android.annotation.SuppressLintimport android.graphics.Colorimport android.os.Buildimport android.os.Bundleimport android.os.Handlerimport android.view.Menuimport android.view.Viewimport android.view.ViewGroupimport android.widget.Toastimport androidx.appcompat.widget.AppCompatTextViewimport androidx.core.view.GravityCompatimport androidx.core.widget.NestedScrollViewimport androidx.drawerlayout.widget.DrawerLayoutimport androidx.recyclerview.widget.LinearLayoutManagerimport androidx.recyclerview.widget.RecyclerViewimport androidx.vectordrawable.graphics.drawable.ArgbEvaluatorimport com.google.android.material.appbar.AppBarLayoutimport com.google.android.material.floatingactionbutton.FloatingActionButtonimport com.matias.components.toolbar.toolbarmain.StylingToolbarMainModeimport com.matias.components.viewpager.StylingWrapContentHeightViewPagerimport com.matias.core.base.mvp.BasePresenterActivityimport com.matias.core.helpers.VectorDrawableHelperimport com.matias.core.helpers.dpToPximport com.matias.features.Rimport com.matias.features.di.main.MainActivityModuleimport com.matias.features.di.main.MainActivitySubcomponentimport com.matias.features.ui.MainUiComponentimport com.matias.features.ui.main.MainScreenAnimatorimport com.matias.features.ui.main.adapters.OffersRecyclerAdapterimport com.matias.features.ui.main.adapters.ServiceRecyclerAdapterimport com.matias.features.ui.main.fragments.home.novelty.NoveltyPagerAdapterimport kotlinx.android.synthetic.main.activity_main.*import kotlinx.android.synthetic.main.app_bar_main.*import javax.inject.Injectprivate const val DELAY_TOOLBAR_ICON_ANIMATION = 750Lclass MainActivity : BasePresenterActivity<		MainActivity,		MainActivityPresenter,		MainActivitySubcomponent>(),	MainActivityContract.View, View.OnClickListener, OffersRecyclerAdapter.OnOfferClickListener , ServiceRecyclerAdapter.OnServiceClickListener{	// Views.	private lateinit var rootView: ViewGroup	private lateinit var appBar: AppBarLayout	private lateinit var pagerNovelty: StylingWrapContentHeightViewPager	private lateinit var contentMain: ViewGroup	private lateinit var nestedScrollView: NestedScrollView	private lateinit var viewLoading: View	private lateinit var fabContactUs: FloatingActionButton	private lateinit var sectionOffers: ViewGroup	private lateinit var recyclerOffers: RecyclerView	private lateinit var sectionServices: ViewGroup	private lateinit var recyclerServices: RecyclerView	@Inject	lateinit var vectorDrawableHelper: VectorDrawableHelper	@Inject	lateinit var noveltyPagerAdapter: NoveltyPagerAdapter	@Inject	lateinit var offerAdapter: OffersRecyclerAdapter	@Inject	lateinit var serviceAdapter: ServiceRecyclerAdapter	private lateinit var screenAnimator: MainScreenAnimator<View>	override fun bindComponent(): MainActivitySubcomponent =		MainUiComponent.component.plus(MainActivityModule(this@MainActivity))	override fun bindLayout(): Int =		R.layout.activity_main	override fun onCreate(savedInstanceState: Bundle?) {		super.onCreate(savedInstanceState)		setContentView(R.layout.activity_main)		setViews()		setupToolbar()		initViews()		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {			val decor = window.decorView			decor.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR		}		val viewsToAnimate: List<View> = listOf(pagerNovelty, sectionOffers, sectionServices, contentMain.findViewById<AppCompatTextView>(R.id.tv_mock))		screenAnimator = MainScreenAnimator(viewsToAnimate)	}	/**	 * View instances.	 */	private fun setViews() {		rootView = findViewById(R.id.root_view)		appBar = findViewById(R.id.app_bar)		pagerNovelty = findViewById(R.id.pager_novelty)		contentMain = findViewById(R.id.content_main)		nestedScrollView = contentMain.findViewById(R.id.nested_scroll_view)		viewLoading = contentMain.findViewById(R.id.view_loading)		fabContactUs = findViewById(R.id.fab_contact_us)		sectionOffers = findViewById(R.id.section_offers)		recyclerOffers = sectionOffers.findViewById(R.id.recycler_offers)		sectionServices = findViewById(R.id.section_services)		recyclerServices = sectionServices.findViewById(R.id.recycler_services)	}	/**	 * Setup toolbar.	 */	private fun setupToolbar() {		setSupportActionBar(toolbar)		toolbar.navigationIcon = null		supportActionBar?.setDisplayHomeAsUpEnabled(false)	}	/**	 * View init status.	 */	private fun initViews() {		// Toolbar init status with search view collapsed		appBar.setExpanded(false, false)	}	/**	 *	 */	private fun initToolbar() {		// Expand search view.		appBar.setExpanded(true, true)		toolbarMain.apply {			setMode(StylingToolbarMainMode.TO_SEARCH)			setOnCLickListener(this@MainActivity)			animateNavigationIcon(vectorDrawableHelper, DELAY_TOOLBAR_ICON_ANIMATION)		}	}	override fun onClick(view: View?) {		when (view?.id) {			R.id.buttonNavigation -> {				if (!drawerLayout.isDrawerOpen(GravityCompat.START)) drawerLayout.openDrawer(GravityCompat.START)				else drawerLayout.closeDrawer(GravityCompat.END)			}			R.id.buttonSearch -> {				showToast("Search")			}			R.id.buttonCoupon -> {				showToast("Coupons")			}			R.id.buttonNotification -> {				showToast("Notifications")			}			R.id.iconFilterSearchButton,			R.id.labelFilterSearchButton -> {				showToast("Filter Search")			}		}	}	override fun onResume() {		super.onResume()		presenter.getMainPosition()	}	override fun onCreateOptionsMenu(menu: Menu): Boolean =		false	override fun onBackPressed() {		val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)		if (drawerLayout.isDrawerOpen(GravityCompat.START)) {			drawerLayout.closeDrawer(GravityCompat.START)		} else {			super.onBackPressed()		}	}	/*******************************************************************************************************************	 * [OffersRecyclerAdapter.OnOfferClickListener] implementation	 */	override fun onOfferClick(productId: Int) {		Toast.makeText(this, "Click on offer id $productId", Toast.LENGTH_SHORT).show()	}	/*******************************************************************************************************************	 * [ServiceRecyclerAdapter.OnServiceClickListener] implementation	 */	override fun onServiceClick() {		Toast.makeText(this, "Click on service", Toast.LENGTH_SHORT).show()	}	/*******************************************************************************************************************	 * [MainActivityContract.View] implementation	 */	fun showLoadingScreen(show: Boolean) {		when (show) {			true -> {				viewLoading.visibility = View.VISIBLE				nestedScrollView.visibility = View.GONE				fabContactUs.hide()			}			else -> {				viewLoading.visibility = View.GONE				nestedScrollView.visibility = View.VISIBLE				fabContactUs.show()			}		}	}	override fun showLoadingScreen() {		viewLoading.visibility = View.VISIBLE		nestedScrollView.visibility = View.GONE		appBar.setExpanded(false)		rootView.setBackgroundColor(Color.WHITE)		toolbarMain.setMode(StylingToolbarMainMode.INIT)		fabContactUs.hide()	}	override fun showErrorScreen() {	}	override fun showInfoScreen() {		viewLoading.visibility = View.GONE		nestedScrollView.visibility = View.VISIBLE		appBar.setExpanded(true, true)		rootView.setBackgroundColor(Color.TRANSPARENT)		toolbarMain.setMode(StylingToolbarMainMode.FOR_SEARCH)		fabContactUs.show()	}	@SuppressLint("RestrictedApi")	override fun changeStatusBarColor(toColor: Int) {		ObjectAnimator.ofObject(window, "statusBarColor", ArgbEvaluator(), window.statusBarColor, toColor).apply {			duration = 400			start()		}	}	override fun initView() {		Handler().postDelayed({			initToolbar()			updateView()		}, 500)	}	override fun updateView() {		setupNoveltyPager()		setupProductOfferRecycler()		setupServicesRecycler()		screenAnimator.animateIn()	}	private fun setupNoveltyPager() {		noveltyPagerAdapter.setData(presenter.getNoveltyList())		pagerNovelty.apply {			adapter = noveltyPagerAdapter			setCurrentItem(noveltyPagerAdapter.count / 2, false)			offscreenPageLimit = 2			clipToPadding = false			this.setPadding(dpToPx(context, 18F).toInt(), 0, dpToPx(context, 18F).toInt(), 0)//				this.pageMargin = dpToPx(it, 4)		}	}	private fun setupProductOfferRecycler() {		recyclerOffers.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)		offerAdapter.setData(presenter.getProductOfferList())		recyclerOffers.adapter = offerAdapter	}	private fun setupServicesRecycler() {		recyclerServices.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)		recyclerServices.addItemDecoration(ItemServiceDecoration(this))		recyclerServices.adapter = serviceAdapter	}}