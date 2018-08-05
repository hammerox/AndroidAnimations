package com.mcustodio.animations.fragments

import android.animation.AnimatorInflater
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import com.mcustodio.animations.R
import com.mcustodio.animations.databinding.FragmentObjanimBinding
import kotlinx.android.synthetic.main.fragment_objanim.view.*

class ObjectAnimFragment: Fragment() {

    private val viewModel by lazy {  ViewModelProviders.of(this).get(ViewModel::class.java) }
    private lateinit var binding: FragmentObjanimBinding
    private var currentAnimation: ValueAnimator? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_objanim, container, false)
        binding.setLifecycleOwner(this)
        binding.viewmodel = viewModel
        val view = binding.root

        view.button_objanim_animateDynamic.setOnClickListener { animateDynamically() }
        view.button_objanim_animateXML.setOnClickListener { animateXML() }
        return view
    }

    private fun animateDynamically() {
        currentAnimation?.cancel()
        currentAnimation = ObjectAnimator.ofFloat(view?.text_objanim_string, "translationY", 0f, viewModel.finalValue.value!!)
                .apply {
                    interpolator = AccelerateDecelerateInterpolator()
                    duration = viewModel.duration.value!!
                    start()
                }
    }

    private fun animateXML() {
        currentAnimation?.cancel()
        currentAnimation = (AnimatorInflater.loadAnimator(context, R.animator.objanim_translatey) as ObjectAnimator)
                .apply {
                    target = view?.text_objanim_string
                    start()
                }
    }


    // ViewModel class
    class ViewModel(app: Application) : AndroidViewModel(app) {
        val duration = MutableLiveData<Long>()
        val finalValue = MutableLiveData<Float>()

        init {
            duration.value = 200L
            finalValue.value = 500f
        }
    }
}