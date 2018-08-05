package com.anim.mcustodio.animations.animTest


import android.animation.ValueAnimator
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator

import com.anim.mcustodio.animations.R
import kotlinx.android.synthetic.main.fragment_test.view.*

class TestFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_test, container, false)
        view.button_test_animate.setOnClickListener { animate() }
        return view
    }


    private fun animate() {
        ValueAnimator.ofFloat(0f, 500f).apply {
            interpolator = AccelerateDecelerateInterpolator()
            duration = 300
            addUpdateListener { animation ->
                val progress = animation.animatedValue as Float
                view?.text_test_string?.translationY = progress
            }
            start()
        }
    }


}
