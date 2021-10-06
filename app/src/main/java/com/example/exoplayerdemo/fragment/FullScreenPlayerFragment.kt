package com.example.exoplayerdemo.fragment

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.PowerManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.exoplayerdemo.MainActivity
import com.example.exoplayerdemo.R
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.*
import com.google.android.exoplayer2.util.Util
import kotlin.math.roundToInt


class FullScreenPlayerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var exoPlayerView: PlayerView
    private var exoPlayer: SimpleExoPlayer? = null
    private lateinit var iVFullScreen: ImageView
    private lateinit var rootView : View
    private lateinit var pm : PowerManager
    private lateinit var wl : PowerManager.WakeLock
    private var fullscreen : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pm = requireContext().getSystemService(Context.POWER_SERVICE) as PowerManager
        wl = pm!!.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK,"My Tag")as PowerManager.WakeLock
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView =inflater.inflate(R.layout.fragment_full_screen_player, container, false)
        initView(rootView);
        return rootView
    }

    private fun initView(v: View){
        try {
            exoPlayerView = v.findViewById(R.id.idExoPlayerVIew);
            iVFullScreen = v.findViewById(R.id.ivFullScreen);

            iVFullScreen.setOnClickListener {
                fullscreen()
            }
            exoPlayer = (activity as? MainActivity)?.getupPlayer()
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
            exoPlayerView.player = exoPlayer
            exoPlayer!!.playWhenReady = true
        } catch (e: Exception) {
            Log.e("TAG", "Error : $e")
        }
    }



    override fun onStop() {
        super.onStop()
        exoPlayer!!.playWhenReady = false
    }

    override fun onDestroy() {
        super.onDestroy()
        requireActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE)
        requireActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    }

    private fun fullscreen(){
        if (fullscreen) {
            requireActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE)
            requireActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
            val params = exoPlayerView.getLayoutParams()
            params.width = ViewGroup.LayoutParams.MATCH_PARENT
            params.height =  ViewGroup.LayoutParams.MATCH_PARENT
            exoPlayerView.setLayoutParams(params)
            fullscreen = false

        } else {
            requireActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
            requireActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
            val params = exoPlayerView.getLayoutParams()
            params.width = ViewGroup.LayoutParams.MATCH_PARENT
            params.height = ViewGroup.LayoutParams.MATCH_PARENT
            exoPlayerView.setLayoutParams(params)
            fullscreen = true

        }
    }

}