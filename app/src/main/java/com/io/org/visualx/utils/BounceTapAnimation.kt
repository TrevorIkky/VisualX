package com.io.org.visualx.utils

import android.view.animation.Interpolator

class BounceTapAnimation(amplitude: Double, frequency: Double) : Interpolator {
    override fun getInterpolation(time: Float): Float {
        return (-1 * Math.pow(Math.E, -time / thisamplitude) * Math.cos(thisfrequency * time)).toFloat()
    }

    var thisamplitude: Double = 0.0
    var thisfrequency: Double = 0.0

    init {
        this.thisamplitude = amplitude
        this.thisfrequency = frequency
    }
}