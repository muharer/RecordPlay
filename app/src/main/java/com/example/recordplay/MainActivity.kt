package com.example.recordplay

import android.graphics.Matrix
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.DisplayMetrics
import android.util.Rational

import android.util.Size
import android.view.Surface
import android.view.TextureView
import androidx.camera.core.AspectRatio
import androidx.lifecycle.LifecycleOwner
import androidx.camera.core.PreviewConfig
import androidx.camera.core.Preview
import androidx.camera.core.CameraX


class MainActivity : AppCompatActivity() {


    private var lensFacing = CameraX.LensFacing.BACK
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        video_texture.post { startCamera() }


        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

//        val previewConfig = PreviewConfig.Builder().build()
//        val preview = Preview(previewConfig)
//
//        preview.setOnPreviewOutputUpdateListener {
//                previewOutput: Preview.PreviewOutput? ->
//
//            // Your code here. For example, use previewOutput?.getSurfaceTexture()
//            // and post to a GL renderer.
//        }

//        CameraX.bindToLifecycle(this as LifecycleOwner, preview)
    }
    private fun startCamera() {
        val metrics = DisplayMetrics().also { video_texture.display.getRealMetrics(it)}
        val screenSize = Size(metrics.widthPixels, metrics.heightPixels)
        val screenAspectRatio = Rational(metrics.widthPixels, metrics.heightPixels)

        val previewConfig = PreviewConfig.Builder().apply {
            setLensFacing(lensFacing)
            setTargetResolution(screenSize)
            setTargetAspectRatio(screenAspectRatio as AspectRatio )
            setTargetRotation(windowManager.defaultDisplay.rotation)
            setTargetRotation(video_texture.display.rotation)
        }.build()

        val preview = Preview(previewConfig)
        preview.setOnPreviewOutputUpdateListener {
//            video_texture = it.surfaceTexture
//            updateTransform()
        }
    }

//    private fun updateTransform() {
//        val matrix = Matrix()
//        val centerX = vv.width / 2f
//        val centerY = vv.height / 2f
//
//        val rotationDegrees = when (vv.display.rotation) {
//            Surface.ROTATION_0 -> 0
//            Surface.ROTATION_90 -> 90
//            Surface.ROTATION_180 -> 180
//            Surface.ROTATION_270 -> 270
//            else -> return
//        }
//        matrix.postRotate(-rotationDegrees.toFloat(), centerX, centerY)
//        vv.setTransform(matrix)
//    }


        override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
