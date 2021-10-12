package com.example.exoplayerdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.exoplayerdemo.model.Media
import java.util.*

class HomeActivity : AppCompatActivity(),OnClickListenerPosition {
    private lateinit var mMediaItem: ArrayList<Media>
    lateinit  var mRecyclerView: VideoPlayerRecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        mRecyclerView = findViewById(R.id.recycler_view)
        initData()
        initRecyclerView()
    }
    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        mRecyclerView.layoutManager = layoutManager
        mRecyclerView.setMediaObjects(mMediaItem)
        val adapter = initGlide()?.let { VideoPlayerRecyclerAdapter(mMediaItem, it,this) }
        mRecyclerView!!.adapter = adapter



    }
    private fun initGlide(): RequestManager? {
        val options = RequestOptions()
            .placeholder(R.drawable.white_background)
            .error(R.drawable.white_background)
        return Glide.with(this)
            .setDefaultRequestOptions(options)
    }


    override fun onDestroy() {
        if (mRecyclerView != null) mRecyclerView!!.releasePlayer()
        super.onDestroy()
    }
    private fun initData() {
        mMediaItem = ArrayList()
        mMediaItem.add(
            Media(
                "https://d2aspyhfct5pw3.cloudfront.net/hls1.0/ae545030-5f9b-4520-85e6-5bae170a6271/hls_1.0/hls_vid.m3u8",
                480,
                264,
                "https://d2aspyhfct5pw3.cloudfront.net/hls1.0/ae545030-5f9b-4520-85e6-5bae170a6271/thumbs/thumb-00001.jpg"
            )
        )
        mMediaItem.add(
            Media(
                "https://d2aspyhfct5pw3.cloudfront.net/hls1.0/0f3e6fd8-e7db-44fc-a4c7-66a9b711646b/hls_1.0/hls_vid.m3u8",
                480,
                216,
                "https://d2aspyhfct5pw3.cloudfront.net/hls1.0/0f3e6fd8-e7db-44fc-a4c7-66a9b711646b/thumbs/thumb-00001.jpg"
            )
        )
        mMediaItem.add(
            Media(
                "https://d2aspyhfct5pw3.cloudfront.net/hls1.0/a552a960-d10e-4a4a-955a-d55ee9c11efc/hls_1.0/hls_vid.m3u8",
                480,
                264,
                "https://d2aspyhfct5pw3.cloudfront.net/hls1.0/a552a960-d10e-4a4a-955a-d55ee9c11efc/thumbs/thumb-00001.jpg"
            )
        )
        mMediaItem.add(
            Media(
                "https://d2aspyhfct5pw3.cloudfront.net/hls1.0/db578762-965f-4536-911c-5f2b148937da/hls_1.0/hls_vid.m3u8",
                480,
                312,
                "https://d2aspyhfct5pw3.cloudfront.net/hls1.0/db578762-965f-4536-911c-5f2b148937da/thumbs/thumb-00001.jpg"
            )
        )
        mMediaItem.add(
            Media(
                "https://d2aspyhfct5pw3.cloudfront.net/hls1.0/eb805575-e4c3-4fd8-b330-dc8e9994cb58/hls_1.0/hls_vid.m3u8",
                426,
                234,
                "https://d2aspyhfct5pw3.cloudfront.net/hls1.0/eb805575-e4c3-4fd8-b330-dc8e9994cb58/thumbs/thumb-00001.jpg"
            )
        )
        mMediaItem.add(
            Media(
                "https://d2aspyhfct5pw3.cloudfront.net/hls1.0/FA895FF5-7112-464F-8BD8-9CD4D7AE79C4/hls_1.0/hls_vid.m3u8",
                480,
                480,
                "https://d2aspyhfct5pw3.cloudfront.net/hls1.0/FA895FF5-7112-464F-8BD8-9CD4D7AE79C4/thumbs/thumb-00001.jpg"
            )
        )
        mMediaItem.add(
            Media(
                "https://d2aspyhfct5pw3.cloudfront.net/hls1.0/bf9f4e77-d1ec-4c24-b1f3-108d69ed9680/hls_1.0/hls_vid.m3u8",
                480,
                270,
                "https://d2aspyhfct5pw3.cloudfront.net/hls1.0/bf9f4e77-d1ec-4c24-b1f3-108d69ed9680/thumbs/thumb-00001.jpg"
            )
        )
        mMediaItem.add(
            Media(
                "https://d2aspyhfct5pw3.cloudfront.net/hls1.0/b8f58ed0-a9e0-49f2-b7d7-021c7104fa38/hls_1.0/hls_vid.m3u8",
                480,
                342,
                "https://d2aspyhfct5pw3.cloudfront.net/hls1.0/b8f58ed0-a9e0-49f2-b7d7-021c7104fa38/thumbs/thumb-00001.jpg"
            )
        )
        mMediaItem.add(
            Media(
                "https://d2aspyhfct5pw3.cloudfront.net/hls1.0/d8b2ded9-ffbb-4071-bfff-b018b047419b/hls_1.0/hls_vid.m3u8",
                480,
                480,
                "https://d2aspyhfct5pw3.cloudfront.net/hls1.0/d8b2ded9-ffbb-4071-bfff-b018b047419b/thumbs/thumb-00001.jpg"
            )
        )
    }

    override fun onMediaClick(media: Media, view: View, itemPosition: Int) {
      //  TODO("Not yet implemented")
      //  mRecyclerView.playVideo(false)
        println("the valoue is $itemPosition")
        if (view.id==R.id.exo_play) {
            mRecyclerView.playVideoByPosition(itemPosition)
        }
        if (view.id==R.id.ivFullScreen)
        {
            mRecyclerView.setPlayerControl(false)
        val intent:Intent= Intent (this,FullScreenActivity::class.java)
            intent.putExtra("URL",media.url)
            startActivity(intent)
        }
    }


    override fun onStop() {
        super.onStop()
        mRecyclerView.setPlayerControl(false)
    }


}