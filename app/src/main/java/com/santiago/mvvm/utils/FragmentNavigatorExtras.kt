package com.santiago.mvvm.utils

import android.view.View
import androidx.navigation.fragment.FragmentNavigator

/**
 * Create a new [FragmentNavigator.Extras] instance with the given shared elements
 *
 * @param sharedElements One or more pairs of View+String names to be passed through to
 * [FragmentNavigator.Extras.Builder.addSharedElement].
 */
@Suppress("FunctionName")
fun FragmentNavigatorExtras(vararg sharedElements: Pair<View, String>) =
    FragmentNavigator.Extras.Builder().apply {
        sharedElements.forEach { (view, name) ->
            addSharedElement(view, name)
        }
    }.build()