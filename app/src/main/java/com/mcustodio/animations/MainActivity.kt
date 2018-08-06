package com.mcustodio.animations

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val viewModel by lazy { ViewModelProviders.of(this).get(ViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        viewModel.currentFragment.observe(this, Observer {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.layout_main_container, it?.create())
                    .commit()
        })

        if (viewModel.currentFragment.value == null) viewModel.currentFragment.value = FragmentList.getFirst()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        viewModel.currentFragment.value = when (item.itemId) {
            R.id.nav_anim_grav -> FragmentList.GRAV
            R.id.nav_anim_lottie -> FragmentList.LOTTIE
            R.id.nav_anim_objanim -> FragmentList.OBJECT_ANIM
            R.id.nav_anim_valanim -> FragmentList.VALUE_ANIM
            else -> null
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


    class ViewModel(app: Application) : AndroidViewModel(app) {
        val currentFragment = MutableLiveData<FragmentList>()
    }
}
