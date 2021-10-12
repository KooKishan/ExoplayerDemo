package com.example.exoplayerdemo

import android.util.Log
import android.view.View

import androidx.recyclerview.widget.RecyclerView
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.RequestManager
import com.example.exoplayerdemo.R
import com.example.exoplayerdemo.model.Media

class VideoPlayerViewHolder(var parent: View,) : RecyclerView.ViewHolder(
    parent
) {
    var media_container: FrameLayout
    var title: TextView
    var thumbnail: ImageView
    var volumeControl: ImageView
    var exoPlay : ImageView
    var exoPause : ImageView
    var ivFullScreen : ImageView
    var progressBar: ProgressBar
    var requestManager: RequestManager? = null
    fun onBind(mediaObject: Media, requestManager: RequestManager?) {
        this.requestManager = requestManager
        parent.tag = this
        //  title.setText(mediaObject.getTitle());
        this.requestManager
            ?.load(mediaObject.cover_img)
            ?.into(thumbnail)

    }

    init {
        media_container = itemView.findViewById(R.id.media_container)
        thumbnail = itemView.findViewById(R.id.thumbnail)
        title = itemView.findViewById(R.id.title)
        progressBar = itemView.findViewById(R.id.progressBar)
        exoPlay = itemView.findViewById(R.id.exo_play)
        exoPause = itemView.findViewById(R.id.exo_pause)
        ivFullScreen = itemView.findViewById(R.id.ivFullScreen)
        volumeControl = itemView.findViewById(R.id.volume_control)


    }
}