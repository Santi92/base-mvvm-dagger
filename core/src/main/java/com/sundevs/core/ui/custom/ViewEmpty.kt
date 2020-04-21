package com.sundevs.core.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import com.sundevs.core.R

class ViewEmpty(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {


    private val emptyContainer: RelativeLayout

    init {

        inflate(context, R.layout.view_empty, this)

        emptyContainer = findViewById(R.id.emptyContainer)
    }

    fun hideProgress(){
        emptyContainer.visibility = View.GONE
    }

    fun showProgress(){
        emptyContainer.visibility = View.VISIBLE
    }
}