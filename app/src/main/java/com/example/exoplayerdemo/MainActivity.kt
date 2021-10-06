package com.example.exoplayerdemo

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.BandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util


class MainActivity : AppCompatActivity() {
    lateinit var navController : NavController
    lateinit var toolbar : Toolbar
    private var exoPlayer: SimpleExoPlayer?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = findNavController(R.id.navfragment)
//my_nav_host defined in activity xml file as id of fragment or FragmentContainerView
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return true
    }


    fun setupPlayer(){
        if(exoPlayer==null) {
            val bandwidthMeter: BandwidthMeter = DefaultBandwidthMeter()

            exoPlayer = SimpleExoPlayer.Builder(this)
                    //.setTrackSelector(trackSelector)
                    .setBandwidthMeter(bandwidthMeter)
                    .build()
        }
    }

    fun getupPlayer():SimpleExoPlayer? {
        return exoPlayer
    }


}