package com.example.exoplayerdemo

import android.view.View
import com.example.exoplayerdemo.model.Media

interface OnClickListenerPosition {
    fun onMediaClick (media : Media, view:View, itemPosition: Int)

}