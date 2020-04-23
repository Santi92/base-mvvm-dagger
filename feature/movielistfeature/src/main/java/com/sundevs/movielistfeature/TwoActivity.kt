package com.sundevs.movielistfeature

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.databinding.DataBindingUtil
import com.sundevs.movielistfeature.databinding.ActivityMainBinding
import com.sundevs.movielistfeature.databinding.ActivityTwoBinding

class TwoActivity : AppCompatActivity() {

    /*
   * I am using DataBinding
   * */
    private   var binding: ActivityTwoBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_two)
    }
}
