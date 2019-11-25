package com.io.org.visualx.utils

import android.animation.ArgbEvaluator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.io.org.visualx.R

class SnapTabIndicator @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr), ViewPager.OnPageChangeListener {

    lateinit var viewPager: ViewPager
    lateinit var view: View
    var argbEvaluator: ArgbEvaluator = ArgbEvaluator()
    val TAG: String? = "SnapTabIndicator"

    init {
        initView()
    }

    private var startColor: Int? = null
    private var endColor: Int? = null
    lateinit var captureButton: ImageView
    lateinit var savedButton: ImageView
    lateinit var activeIndicator: ImageView
    lateinit var flipCamera: ImageView
    private var debug: Boolean = true
    var distance: Float = 0.0f
    var distanceOffset: Float = 0f

    private lateinit var bounceAnimation: Animation


    private fun initView() {
        LayoutInflater.from(context).inflate(R.layout.snap_tabs_layout, this, true)
        captureButton = findViewById(R.id.capture_button)
        savedButton = findViewById(R.id.saved_button)
        activeIndicator = findViewById(R.id.active_tab_indicator)
        flipCamera = findViewById(R.id.flip_camera)

        startColor = ContextCompat.getColor(context, R.color.white)
        endColor = ContextCompat.getColor(context, R.color.gray)

        bounceAnimation = AnimationUtils.loadAnimation(context, R.anim.shake_anim)

        savedButton.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                distance = (savedButton.x - captureButton.x) - distanceOffset
                distanceOffset = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80f, resources.displayMetrics)
                Log.d(TAG, distance.toString())
                savedButton.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })



        flipCamera.setOnClickListener { view ->
            bounceAnim(view)
        }



        savedButton.setOnClickListener { view ->
            bounceAnim(view)
            viewPager.currentItem = 1
        }


        captureButton.setOnClickListener { view ->
            bounceAnim(view)
            viewPager.currentItem = 0
        }


    }


    private fun bounceAnim(view: View?) {
        view?.startAnimation(bounceAnimation)
    }

    fun setUpWithViewPager(viewPager: ViewPager, view: View) {
        viewPager.addOnPageChangeListener(this)
        this.viewPager = viewPager
        this.view = view
        this.viewPager.currentItem = 0

    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageSelected(position: Int) {

    }


    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        if (position == 0)
            addTransitions(positionOffset)
        if (debug) {
            /*good for setting alpha high->low*/
            Log.d(TAG, "Minus : " + (1 - positionOffset).toString())
            /*low->high*/
            Log.d(TAG, "Normal  : $positionOffset")
            /*like minus but on the -ve*/
            Log.d(TAG, "Reversed Minus: " + (positionOffset - 1).toString())
        }
    }

    fun debugEnabled(enable: Boolean) {
        this.debug = enable
    }

    private fun addTransitions(fractionFromCenter: Float) {
        view.alpha = fractionFromCenter

        var color = argbEvaluator.evaluate(fractionFromCenter, startColor, endColor) as Int

        savedButton.setColorFilter(color)
        captureButton.setColorFilter(color)
        activeIndicator.setColorFilter(color)
        flipCamera.setColorFilter(color)
        flipCamera.alpha = 1 - fractionFromCenter

        Log.d(TAG, "Dist: $distance")
        activeIndicator.translationX = fractionFromCenter * distance.toFloat()
    }
}
