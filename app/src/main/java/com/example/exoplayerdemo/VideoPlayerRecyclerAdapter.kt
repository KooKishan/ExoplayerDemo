package com.example.exoplayerdemo


import android.util.Log
import com.bumptech.glide.RequestManager
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.example.exoplayerdemo.model.Media
import java.util.ArrayList

class VideoPlayerRecyclerAdapter(
    private val mediaObjects: ArrayList<Media>,
    private val requestManager: RequestManager,
    onClickListener: OnClickListenerPosition
) : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{

    private val onClickListener: OnClickListenerPosition = onClickListener
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RecyclerView.ViewHolder {
        return VideoPlayerViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.layout_video_list_item, viewGroup, false)
        )
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, i: Int) {
        (viewHolder as VideoPlayerViewHolder).onBind(mediaObjects[i], requestManager)
        viewHolder.exoPlay.setOnClickListener(View.OnClickListener {
            Log.d("navin", "onPlayerStateChanged: Video ended."+mediaObjects[i].url)
onClickListener.onMediaClick(mediaObjects[i],view = it,i)
        })
        viewHolder.ivFullScreen.setOnClickListener(View.OnClickListener {
            Log.d("navin", "onPlayerStateChanged: Video ended."+mediaObjects[i].url)
onClickListener.onMediaClick(mediaObjects[i],view = it,i)
        })
    }

    override fun getItemCount(): Int {
        return mediaObjects.size
    }
}