package com.anim.mcustodio.animations.animTest


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.anim.mcustodio.animations.R
import kotlinx.android.synthetic.main.fragment_test.view.*

class TestFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_test, container, false)
        arguments?.getString("text")?.let {
            view.text_test_string.text = it
        }

        return view
    }


}
