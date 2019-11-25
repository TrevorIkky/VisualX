package com.io.org.visualx.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat


class IdleReticuleView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    private val DURATION_RIPPLE_FADE_IN_MS: Long = 333
    private val DURATION_RIPPLE_FADE_OUT_MS: Long = 500
    private val DURATION_RIPPLE_EXPAND_MS: Long = 833
    private val DURATION_RIPPLE_STROKE_WIDTH_SHRINK_MS: Long = 833
    private val DURATION_RESTART_DORMANCY_MS: Long = 1333
    private val START_DELAY_RIPPLE_FADE_OUT_MS: Long = 667
    private val START_DELAY_RIPPLE_EXPAND_MS: Long = 333
    private val START_DELAY_RIPPLE_STROKE_WIDTH_SHRINK_MS: Long = 333
    private val START_DELAY_RESTART_DORMANCY_MS: Long = 1167


    private val innerCircle: Paint = Paint()
    private val outerCircle: Paint = Paint()
    private val ripplePaint: Paint = Paint()

    private var strokeAlpha: Float = 0f
    private var rippleStrokeWidth: Float = 0f
    private var rippleRadius: Float = 0f

    private var innerCircleRadius = 0
    private var innerCircleStrokeWidth = 0

    private var outerRingStrokeRadius = 0
    private var outerRingStrokeWidth = 0

    private var rippleSizeOffset = 0


    private val animatorSet = AnimatorSet()
    private val TAG: String = "IdleReticuleAnimator"

    private var rippleAlpha = 0

    init {

        innerCircleRadius = context.resources.getDimensionPixelSize(com.io.org.visualx.R.dimen.inner_circle_radius)
        innerCircleStrokeWidth = context.resources.getDimensionPixelSize(com.io.org.visualx.R.dimen.inner_circle_stroke_width)

        outerRingStrokeRadius = context.resources.getDimensionPixelSize(com.io.org.visualx.R.dimen.outer_ring_stroke_radius)
        outerRingStrokeWidth = context.resources.getDimensionPixelSize(com.io.org.visualx.R.dimen.outer_ring_stroke_width)
        rippleSizeOffset = context.resources.getDimensionPixelSize(com.io.org.visualx.R.dimen.ripple_size_offset)



        innerCircle.style = Paint.Style.STROKE
        innerCircle.strokeWidth = innerCircleStrokeWidth.toFloat()
        innerCircle.color = ContextCompat.getColor(context, com.io.org.visualx.R.color.white)
        innerCircle.strokeCap = Paint.Cap.ROUND

        outerCircle.style = Paint.Style.STROKE
        outerCircle.strokeWidth = outerRingStrokeWidth.toFloat()
        outerCircle.color = ContextCompat.getColor(context, com.io.org.visualx.R.color.white)
        outerCircle.strokeCap = Paint.Cap.ROUND


        ripplePaint.style = Paint.Style.STROKE
        ripplePaint.color = ContextCompat.getColor(context, com.io.org.visualx.R.color.white)
        rippleAlpha = ripplePaint.alpha


        /*
        *increasing the circle radius
         * decreasing stroke width
         fade in as i fade out   */


        val fadeInAnimator = ValueAnimator.ofFloat(0f, 1f)
                .apply {
                    duration = DURATION_RIPPLE_FADE_IN_MS
                    addUpdateListener { animation ->
                        strokeAlpha = (animation.animatedValue as Float)
                        invalidate()
                    }
                }

        val fadeOutAnimator = ValueAnimator.ofFloat(1f, 0f)
                .apply {
                    duration = DURATION_RIPPLE_FADE_OUT_MS
                    startDelay = START_DELAY_RIPPLE_FADE_OUT_MS
                    addUpdateListener { animation ->
                        strokeAlpha = (animation.animatedValue as Float)
                        invalidate()
                    }
                }

        val strokeAnimator = ValueAnimator.ofFloat(1f, 0.5f)
                .apply {
                    duration = DURATION_RIPPLE_STROKE_WIDTH_SHRINK_MS
                    startDelay = START_DELAY_RIPPLE_STROKE_WIDTH_SHRINK_MS
                    addUpdateListener { animation ->
                        rippleStrokeWidth = animation.animatedValue as Float
                        invalidate()
                    }
                }


        val radiusCircleAnimator = ValueAnimator.ofFloat(1f, 7f)
                .apply {
                    duration = DURATION_RIPPLE_EXPAND_MS
                    startDelay = START_DELAY_RIPPLE_EXPAND_MS
                    addUpdateListener { animation ->
                        rippleRadius = ((animation.animatedValue as Float))
                        invalidate()
                    }

                }


        val animationRestartDelay = ValueAnimator.ofInt(0, 0).apply {
            duration = DURATION_RESTART_DORMANCY_MS
            startDelay = START_DELAY_RESTART_DORMANCY_MS
        }

        animatorSet.playTogether(fadeInAnimator, fadeOutAnimator, strokeAnimator, radiusCircleAnimator, animationRestartDelay)
        animatorSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                animatorSet.start()
            }
        })
        animatorSet.start()

    }


    fun cancelAnimations() {
        if (animatorSet.isRunning) {
            animatorSet.cancel()
            rippleRadius = 0f
            strokeAlpha = 0f
            rippleStrokeWidth = 0f
            Log.d(TAG, "Animation canceled")
        } else
            Log.d(TAG, "Animation was not playing")
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val cx = width / 2f
        val cy = height / 2f

        ripplePaint.alpha = (rippleAlpha * strokeAlpha).toInt()
        ripplePaint.strokeWidth = (outerRingStrokeWidth * rippleStrokeWidth)
        var radius = outerRingStrokeRadius + rippleSizeOffset * rippleRadius

        canvas?.drawCircle(cx, cy, innerCircleRadius.toFloat(), innerCircle)
        canvas?.drawCircle(cx, cy, outerRingStrokeRadius.toFloat(), outerCircle)
        canvas?.drawCircle(cx, cy, radius, ripplePaint)
    }
}