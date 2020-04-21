package com.sundevs.core.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import com.sundevs.core.R

class ViewLoader @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0)
    : LinearLayout(context, attrs,defStyle) {


    private val loader: ProgressBar
    private val rootView: RelativeLayout

    init {

        inflate(context, R.layout.view_loader, this)
        loader = findViewById(R.id.loader)
        rootView = findViewById(R.id.root_view)
    }

    fun hideProgress(){
        rootView.visibility = View.GONE
    }

    fun showProgress(){
        rootView.visibility = View.VISIBLE
    }
}