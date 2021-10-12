package com.example.exoplayerdemo

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PowerManager
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.BandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

class FullScreenActivity : AppCompatActivity() {
    private lateinit var exoPlayerView: PlayerView
    private var exoPlayer: SimpleExoPlayer? = null
    private lateinit var iVFullScreen: ImageView
    private lateinit var rootView : View
    private lateinit var pm : PowerManager
    private lateinit var wl : PowerManager.WakeLock
    private var fullscreen : Boolean = false


    @SuppressLint("InvalidWakeLockTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen)
        pm = getSystemService(Context.POWER_SERVICE) as PowerManager
        wl = pm!!.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK,"My Tag")as PowerManager.WakeLock
      var  urlVideo = intent.getStringExtra("URL")
        urlVideo?.let { initView(it) }
    }
    private fun initView(urlVideo:String){
        try {
            exoPlayerView = findViewById(R.id.idExoPlayerVIew);
            iVFullScreen = findViewById(R.id.ivFullScreen);

            iVFullScreen.setOnClickListener {
                fullscreen()
            }

            val bandwidthMeter: BandwidthMeter = DefaultBandwidthMeter()
            val trackSelector = DefaultTrackSelector(this).apply {
                setParameters(buildUponParameters().setMaxVideoSizeSd())
            }
            exoPlayer = SimpleExoPlayer.Builder(this)
                .setTrackSelector(trackSelector)
                .setBandwidthMeter(bandwidthMeter)
                .build()

            var mediaDataSourceFactory = DefaultDataSourceFactory(
                this, Util.getUserAgent(this, "RecyclerView VideoPlayer")
            )
            val mediaUrl: String = urlVideo!!

            val hlsMediaSource: HlsMediaSource = HlsMediaSource.Factory(mediaDataSourceFactory)
                .createMediaSource(MediaItem.fromUri(mediaUrl))
            exoPlayer!!.setMediaSource(hlsMediaSource)
            exoPlayerView.player = exoPlayer
            exoPlayer!!.prepare()
            exoPlayer!!.playWhenReady = true
            exoPlayer!!.addListener(object : Player.Listener {

                override fun onPlaybackStateChanged(playbackState: Int) {
                    when (playbackState) {
                        ExoPlayer.STATE_BUFFERING -> {
                        }
                        ExoPlayer.STATE_ENDED -> {
                            wl.release();
                        }
                        ExoPlayer.STATE_IDLE -> {
                        }
                        ExoPlayer.STATE_READY -> {
                            wl.acquire();
                        }
                        else -> {
                        }
                    }
                }

            })

        } catch (e: Exception) {
            Log.e("TAG", "Error : $e")
        }
    }

    private fun fullscreen(){
        if (fullscreen) {
           getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
            val params = exoPlayerView.getLayoutParams()
            params.width = ViewGroup.LayoutParams.MATCH_PARENT
            params.height =  ViewGroup.LayoutParams.MATCH_PARENT
            exoPlayerView.setLayoutParams(params)
            fullscreen = false

        } else {
          getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
          setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
            val params = exoPlayerView.getLayoutParams()
            params.width = ViewGroup.LayoutParams.MATCH_PARENT
            params.height = ViewGroup.LayoutParams.MATCH_PARENT
            exoPlayerView.setLayoutParams(params)
            fullscreen = true

        }
    }

    override fun onStop() {
        super.onStop()
        exoPlayer!!.playWhenReady = false
    }

    override fun onDestroy() {
        super.onDestroy()
       getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    }



}