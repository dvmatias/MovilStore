package com.matias.components.tablayout

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.TextViewCompat
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.matias.components.R

class LoginTabLayout : TabLayout {
    
    private val titles: MutableList<String> = arrayListOf()
    
    private var typeFaceUnselected: Typeface? = null
    private var typeFaceSelected: Typeface? = null
    
    constructor(context: Context) : super(context) {
        init(context, null)
    }
    
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }
    
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }
    
    @Suppress("UNUSED_PARAMETER")
    private fun init(context: Context, attrs: AttributeSet?) {
        typeFaceUnselected = Typeface.createFromAsset(context.assets, "font/open_sans_bold.ttf")
        typeFaceSelected = Typeface.createFromAsset(context.assets, "font/open_sans_bold.ttf")
        addOnTabSelectedListener()
    }
    
    private fun addOnTabSelectedListener() {
        addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabReselected(tab: Tab?) {
            }
            
            override fun onTabUnselected(tab: Tab?) {
                val view = tab?.customView
                if (view is AppCompatTextView) {
                    view.typeface = typeFaceUnselected
                }
            }
            
            override fun onTabSelected(tab: Tab?) {
                val view = tab?.customView
                if (view is AppCompatTextView) {
                    view.typeface = typeFaceSelected
                }
            }
        })
    }
    
    override fun setupWithViewPager(viewPager: ViewPager?, autoRefresh: Boolean) {
        super.setupWithViewPager(viewPager, autoRefresh)
        addViews()
    }
    
    private fun addViews() {
        for (i in 0 until tabCount) {
            val tab = getTabAt(i)
            if (tab != null) {
                val customFontTextView = createCustomFontTextViewForTab()
                if (i == 0) {
                    if (typeFaceUnselected == null) {
                        typeFaceUnselected = customFontTextView.typeface
                    }
                    customFontTextView.setTypeface(customFontTextView.typeface, Typeface.NORMAL)
                }
                tab.customView = customFontTextView
            }
        }
    }
    
    private fun createCustomFontTextViewForTab(): AppCompatTextView {
        val customFontTextView = AppCompatTextView(context)
        customFontTextView.gravity = Gravity.CENTER
        customFontTextView.width = 300
        customFontTextView.setLines(1)
        TextViewCompat.setTextAppearance(customFontTextView, R.style.TabTitleStyle)
        return customFontTextView
    }
    
    fun setTitlesAtTabs(titles: List<String>?) {
        if (titles == null || titles.size < tabCount) {
            return
        }
        if (this.titles.size > 0) {
            this.titles.clear()
        }
        this.titles.addAll(titles)
        for (i in 0 until tabCount) {
            val tab = getTabAt(i)
            if (tab != null) {
                val view = tab.customView
                if (view is AppCompatTextView) {
                    view.text = this.titles[i]
                }
            }
        }
    }
    
}