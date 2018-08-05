package com.anim.mcustodio.animations.animTest


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
import com.anim.mcustodio.animations.R
import com.anim.mcustodio.animations.databinding.FragmentTestBinding
import kotlinx.android.synthetic.main.fragment_test.view.*

class TestFragment : Fragment() {

    private val viewModel by lazy {  ViewModelProviders.of(this).get(TestViewModel::class.java) }
    private lateinit var binding: FragmentTestBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_test, container, false)
        binding.setLifecycleOwner(this)
        binding.viewmodel = viewModel
        val view = binding.root

        view.button_test_animate.setOnClickListener { animate() }
        return view
    }

    private fun animate() {
        ValueAnimator.ofFloat(0f, viewModel.finalValue.value!!).apply {
            interpolator = AccelerateDecelerateInterpolator()
            duration = viewModel.duration.value!!
            addUpdateListener { animation ->
                val progress = animation.animatedValue as Float
                view?.text_test_string?.translationY = progress
            }
            start()
        }
    }


    // ViewModel class
    class TestViewModel(app: Application) : AndroidViewModel(app) {
        val duration = MutableLiveData<Long>()
        val finalValue = MutableLiveData<Float>()

        init {
            duration.value = 200L
            finalValue.value = 500f
        }
    }


}
