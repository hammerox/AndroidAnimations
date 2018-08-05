package com.mcustodio.animations

import android.support.v4.app.Fragment
import com.mcustodio.animations.fragments.LottieFragment
import com.mcustodio.animations.fragments.ObjectAnimFragment
import com.mcustodio.animations.fragments.ValueAnimFragment

enum class FragmentList {
    LOTTIE,
    OBJECT_ANIM,
    VALUE_ANIM;

    fun create(): Fragment = when (this) {
        LOTTIE -> LottieFragment()
        OBJECT_ANIM -> ObjectAnimFragment()
        VALUE_ANIM -> ValueAnimFragment()
    }

    companion object {
        fun getFirst() : FragmentList = FragmentList.values()[0]
    }
}