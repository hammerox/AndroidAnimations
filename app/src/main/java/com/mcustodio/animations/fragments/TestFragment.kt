package com.mcustodio.animations.fragments


import android.animation.AnimatorInflater
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
import com.mcustodio.animations.databinding.FragmentTestBinding
import com.mcustodio.animations.R
import kotlinx.android.synthetic.main.fragment_test.view.*

class TestFragment : Fragment() {

    private val viewModel by lazy {  ViewModelProviders.of(this).get(ViewModel::class.java) }
    private lateinit var binding: FragmentTestBinding
    private var currentAnimation: ValueAnimator? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_test, container, false)
        binding.setLifecycleOwner(this)
        binding.viewmodel = viewModel
        val view = binding.root

        view.button_test_animateDynamic.setOnClickListener { animateDynamically() }
        view.button_test_animateXML.setOnClickListener { animateXML() }
        return view
    }

    private fun animateDynamically() {
        currentAnimation?.cancel()
        currentAnimation = ValueAnimator.ofFloat(0f, viewModel.finalValue.value!!).apply {
            interpolator = AccelerateDecelerateInterpolator()
            duration = viewModel.duration.value!!
            addUpdateListener {
                val progress = animatedValue as Float
                view?.text_test_string?.translationY = progress
            }
            start()
        }
    }

    private fun animateXML() {
        currentAnimation?.cancel()
        currentAnimation = (AnimatorInflater.loadAnimator(context, R.animator.test_translatey) as ValueAnimator)
                .apply {
                    addUpdateListener {
                        val progress = animatedValue as Float
                        view?.text_test_string?.translationY = progress
                    }
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
