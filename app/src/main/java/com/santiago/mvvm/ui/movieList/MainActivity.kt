package com.santiago.mvvm.ui.movieList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.santiago.mvvm.R
import com.santiago.mvvm.databinding.ActivityMainBinding
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    /*
     * I am using DataBinding
     * */
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initialiseView()
    }

    /*
   * Initialising the View using Data Binding
   * */
    private fun initialiseView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

    }

    fun updateBackground(url: String?) {
        binding.overlayLayout.updateCurrentBackground(url)
    }

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }
}
