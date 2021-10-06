package com.example.exoplayerdemo.fragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exoplayerdemo.MainActivity
import com.example.exoplayerdemo.R
import com.example.exoplayerdemo.OnClickListener
import com.example.exoplayerdemo.adapter.VideoAdapter
import com.example.exoplayerdemo.model.Media
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util


class ListFragment : Fragment(),OnClickListener {
   // private lateinit var exoPlayerView: PlayerView
    private var exoPlayer: SimpleExoPlayer? = null
    private lateinit var iVFullScreen: ImageView
    private lateinit var rootView: View
    private var isPlayerInit: Boolean = false
    private lateinit var mMediaItem: ArrayList<Media>
    private lateinit var rl_exo : RecyclerView
    private lateinit var mAdapter : VideoAdapter

    //    var videoURL = "https://media.geeksforgeeks.org/wp-content/uploads/20201217163353/Screenrecorder-2020-12-17-16-32-03-350.mp4"
   // var videoURL = "https://bitdash-a.akamaihd.net/content/MI201109210084_1/m3u8s/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.m3u8"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_list, container, false)
        isPlayerInit = (activity as? MainActivity)?.getupPlayer() != null
        (activity as? MainActivity)?.setupPlayer()

        exoPlayer = (activity as? MainActivity)?.getupPlayer()
        initView(rootView)
        return rootView
    }


    private fun initView(v: View) {
        try {
            rl_exo = v.findViewById(R.id.rl_exo)
            rl_exo.setLayoutManager(
                LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            )
            //Drawable dividerDrawable = ContextCompat.getDrawable(this, R.drawable.divider_drawable);
            //Drawable dividerDrawable = ContextCompat.getDrawable(this, R.drawable.divider_drawable);
            /*rl_exo.setOnScrollChangeListener(View.OnScrollChangeListener { view, i, i1, i2, i3 ->
                val linearLayoutManager = rl_exo.getLayoutManager() as LinearLayoutManager
                visibleItemCount = linearLayoutManager.childCount
                firstVisibleItem = linearLayoutManager.findFirstCompletelyVisibleItemPosition()
                if (firstVisibleItem != -1) // mAdapter.playIndexThenPausePreviousPlayer(firstVisibleItem);
                    mAdapter.pauseCurrentPlayingVideo()
            })*/

            mAdapter = VideoAdapter(getData(), requireContext(), this)
            rl_exo.adapter = mAdapter

            /*
            exoPlayerView = v.findViewById(R.id.idExoPlayerVIew)
            iVFullScreen = v.findViewById(R.id.ivFullScreen)

            iVFullScreen.setOnClickListener {
                exoPlayer!!.playWhenReady = false
                var action = ListFragmentDirections.actionListFragmentToFullScreenPlayerFragment(
                    videoURL,
                    exoPlayer!!.currentPosition
                )
                findNavController().navigate(action)
            }
            exoPlayerView.player = exoPlayer
            if (!isPlayerInit) {
                var mediaDataSourceFactory = DefaultDataSourceFactory(
                    requireContext(),
                    Util.getUserAgent(requireContext(), "mediaPlayerSample")
                )
                val hlsMediaSource: HlsMediaSource = HlsMediaSource.Factory(mediaDataSourceFactory)
                    .createMediaSource(MediaItem.fromUri(videoURL))
                exoPlayer!!.setMediaSource(hlsMediaSource)
                // with media source.
                exoPlayer!!.prepare()
            }
            // we are setting our exoplayer
            // when it is ready.
            exoPlayer!!.playWhenReady = true*/
        } catch (e: Exception) {
            // below line is used for
            // handling our errors.
            Log.e("TAG", "Error : $e")
        }
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

    private fun getData(): ArrayList<Media> {
        return mMediaItem
    }

    override fun onStop() {
        super.onStop()
        exoPlayer!!.playWhenReady = false
    }

    override fun onMediaClick(
        media: Media,
        playerView: PlayerView,
        thumbnail: ImageView,
        play_pause: ImageView
    ) {

        if(playerView.player==null){
            playerView.player = exoPlayer
            var mediaDataSourceFactory = DefaultDataSourceFactory(
                requireContext(),
                Util.getUserAgent(requireContext(), "mediaPlayerSample")
            )
            val hlsMediaSource: HlsMediaSource = HlsMediaSource.Factory(mediaDataSourceFactory)
                .createMediaSource(MediaItem.fromUri(media.url))
            exoPlayer!!.setMediaSource(hlsMediaSource)
            playerView.useController = false
            // with media source.
            exoPlayer!!.prepare()
            exoPlayer!!.addListener(object : Player.Listener {

                override fun onPlaybackStateChanged(playbackState: Int) {
                    when (playbackState) {
                        ExoPlayer.STATE_BUFFERING -> {
                        }
                        ExoPlayer.STATE_ENDED -> {

                        }
                        ExoPlayer.STATE_IDLE -> {
                        }
                        ExoPlayer.STATE_READY -> {
                            playerView.visibility = View.VISIBLE
                            thumbnail.visibility = View.GONE
                            play_pause.setImageResource(R.drawable.exo_controls_pause)
                        }
                        else -> {
                        }
                    }
                }

            })
            exoPlayer!!.playWhenReady = true
        }else {
            if(playerView.player!!.isPlaying) {
                exoPlayer!!.playWhenReady = false
                playerView.visibility = View.GONE
                thumbnail.visibility = View.VISIBLE
                play_pause.setImageResource(R.drawable.exo_controls_play)
            }else {
                exoPlayer!!.playWhenReady = true
                playerView.visibility = View.VISIBLE
                thumbnail.visibility = View.GONE
                play_pause.setImageResource(R.drawable.exo_controls_pause)
            }

        }

    }

    override fun onFullScreenClick(
        playerView: PlayerView,
        thumbnail: ImageView,
        play_pause: ImageView
    ) {
        exoPlayer!!.playWhenReady = false
        playerView.visibility = View.GONE
        thumbnail.visibility = View.VISIBLE
        play_pause.setImageResource(R.drawable.exo_controls_play)
        var action = ListFragmentDirections.actionListFragmentToFullScreenPlayerFragment()
        findNavController().navigate(action)
    }


}