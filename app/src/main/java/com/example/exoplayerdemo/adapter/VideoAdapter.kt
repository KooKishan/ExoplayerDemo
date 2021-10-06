package com.example.exoplayerdemo.adapter

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.exoplayerdemo.OnClickListener
import com.example.exoplayerdemo.R
import com.example.exoplayerdemo.model.Media
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.ui.PlayerView

import java.util.*

class VideoAdapter(mediaObjects: ArrayList<Media>, context: Context, onClickListener: OnClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
    private val mediaObjects: ArrayList<Media> = mediaObjects
    private val context: Context = context
    private val onClickListener: OnClickListener = onClickListener


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RecyclerView.ViewHolder {
        return PlayerViewHolderNew(
                LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.layout_media_list_item, viewGroup, false))
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, i: Int) {
        (viewHolder as PlayerViewHolderNew).onBind(mediaObjects[i], i)
    }

    inner class PlayerViewHolderNew(private val parent: View) : RecyclerView.ViewHolder(parent) {
        /**
         * below view have public modifier because
         * we have access PlayerViewHolder inside the ExoPlayerRecyclerView
         */
        private val playerView: PlayerView = parent.findViewById(R.id.item_video_exoplayer)
        private val thumbnail: ImageView = parent.findViewById(R.id.thumbnail)
        private val exo_play_pause: ImageView = parent.findViewById(R.id.exo_play_pause);
        private val ivFullScreen: ImageView = parent.findViewById(R.id.ivFullScreen);

        fun onBind(mediaObject: Media, item_index: Int) {
            //Create the player using ExoPlayerFactory
            Glide.with(context)
                    .load(mediaObject.cover_img)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(thumbnail);

            val thum_params = thumbnail.getLayoutParams()
            thum_params.width = mediaObject.width
            thum_params.height =  mediaObject.height
            thumbnail.layoutParams = thum_params

            val player_params = playerView.getLayoutParams()
            player_params.width = mediaObject.width
            player_params.height =  mediaObject.height
            playerView.layoutParams = player_params

            exo_play_pause.setOnClickListener(View.OnClickListener {
                onClickListener.onMediaClick(mediaObject,playerView,thumbnail,exo_play_pause)
            })
            ivFullScreen.setOnClickListener(View.OnClickListener {
                onClickListener.onFullScreenClick(playerView,thumbnail,exo_play_pause)
            })
        }
    }

    override fun getItemCount(): Int {
       return mediaObjects.size
    }

}