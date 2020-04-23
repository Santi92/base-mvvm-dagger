package com.sundevs.movielistfeature


import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.sundevs.movielistfeature.databinding.ActivityMainBinding
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.DaggerAppCompatActivity

import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    /*
     * I am using DataBinding
     * */
    private  var binding: ActivityMainBinding?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        initialiseView()
    }


    /*
   * Initialising the View using Data Binding
   * */
    private fun initialiseView() {
        val rootView: View =
            layoutInflater.inflate(R.layout.activity_main, null, false)
        binding = DataBindingUtil.bind(rootView)

        setContentView(rootView)

    }


    fun updateBackground(url: String?) {
        binding?.overlayLayout?.updateCurrentBackground(url)
    }

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }
}
