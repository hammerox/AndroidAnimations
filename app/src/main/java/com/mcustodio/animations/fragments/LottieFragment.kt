package com.mcustodio.animations.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mcustodio.animations.R
import kotlinx.android.synthetic.main.fragment_lottie.*

class LottieFragment: Fragment() {




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_lottie, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lottie_drop.setOnClickListener { lottie_drop.playAnimation() }
        lottie_favourite.setOnClickListener { lottie_favourite.playAnimation() }
        lottie_done.setOnClickListener { lottie_done.playAnimation() }
        lottie_lego.setOnClickListener { lottie_lego.playAnimation() }
    }
}