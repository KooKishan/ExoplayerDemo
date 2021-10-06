package com.example.exoplayerdemo

import android.widget.ImageView
import com.example.exoplayerdemo.model.Media
import com.google.android.exoplayer2.ui.PlayerView

interface OnClickListener {
    fun onMediaClick (media : Media, playerView: PlayerView, thumbnail : ImageView,play_pause : ImageView)
    fun onFullScreenClick (playerView: PlayerView, thumbnail : ImageView,play_pause : ImageView)
}