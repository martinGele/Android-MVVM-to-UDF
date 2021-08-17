package com.martin.samplecompose.util

import android.widget.ProgressBar
import androidx.core.view.isVisible

fun ProgressBar.setLoading(isVisible:Boolean){
    this.isVisible = isVisible
}