package com.io.org.visualx.activities

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.viewpager.widget.ViewPager
import com.io.org.visualx.R
import com.io.org.visualx.adapter.FragmentPagerAdapter
import com.io.org.visualx.fragments.CameraXFragment
import com.io.org.visualx.fragments.SavedItemsFragment
import com.io.org.visualx.utils.SnapTabIndicator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

   companion object {
       lateinit var viewPager: ViewPager
        var s : String ? = null
   }
    lateinit var snapTabIndicator: SnapTabIndicator
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var backgroundView: View

    private fun init() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }

        viewPager = findViewById(R.id.viewPager)
        snapTabIndicator = findViewById(R.id.snap_tab_indicator)
        coordinatorLayout = findViewById(R.id.root_coordinator)
        backgroundView = findViewById(R.id.background_view)

        val fragmentPagerAdapter = FragmentPagerAdapter(supportFragmentManager)
        fragmentPagerAdapter.addFragments(CameraXFragment())
        fragmentPagerAdapter.addFragments(SavedItemsFragment())
        viewPager.adapter = fragmentPagerAdapter
        snapTabIndicator.setUpWithViewPager(viewPager, backgroundView)
        snapTabIndicator.debugEnabled(true)

    }


}
