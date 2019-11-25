package com.io.org.visualx.fragments


import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Matrix
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.view.*
import android.widget.Toast
import androidx.camera.core.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.io.org.visualx.R
import com.io.org.visualx.core.Analyzer

/*Extends app fragment*/

class CameraXFragment : androidx.fragment.app.Fragment() {


    private val REQUEST_PERMISSIONS_CODE = 14
    private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    private var lens = CameraX.LensFacing.BACK

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_camera_x, container, false)
        init(rootView)
        checkInternetPermission()
        return rootView
    }

    lateinit var viewFinder: TextureView


    private fun init(rootView: View) {

        viewFinder = rootView.findViewById(R.id.view_finder)

        if (allPermissionsAreGranted()) {
            viewFinder.post {
                startCamera()
            }
        } else {
            ActivityCompat.requestPermissions(activity!!, REQUIRED_PERMISSIONS, REQUEST_PERMISSIONS_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == REQUEST_PERMISSIONS_CODE) {
            if (allPermissionsAreGranted()) {
                viewFinder.post {
                    startCamera()
                }
            } else {
                val errorMessage = "Not all permissions for the app have been granted"
                Toast.makeText(activity, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun allPermissionsAreGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(activity!!, it) == PackageManager.PERMISSION_GRANTED
    }

    private fun checkInternetPermission() {
        if (ContextCompat.checkSelfPermission(activity!!, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED)
            Toast.makeText(activity!!, "Permission granted", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(activity!!, "Permission not", Toast.LENGTH_SHORT).show()
    }

    private fun startCamera() {
        val config = PreviewConfig.Builder()
                .setLensFacing(lens)
                .build()
        val preview = Preview(config)

        val analyzerConfig = ImageAnalysisConfig.Builder().apply {
            val handlerThread = HandlerThread("Thread").apply { start() }
            setCallbackHandler(Handler(handlerThread.looper))
            setImageReaderMode(ImageAnalysis.ImageReaderMode.ACQUIRE_LATEST_IMAGE)
        }.build()

        val analysis = ImageAnalysis(analyzerConfig).apply {
            analyzer = Analyzer(activity!!)
        }

        preview.setOnPreviewOutputUpdateListener {
            var parent = viewFinder.parent as ViewGroup
            parent.removeView(viewFinder)
            parent.addView(viewFinder, 0)
            viewFinder.surfaceTexture = it.surfaceTexture
            updateViewTransforms()
        }

        CameraX.bindToLifecycle(this as LifecycleOwner, preview, analysis)
    }

    private fun updateViewTransforms() {
        val matrix = Matrix()

        var centerX = viewFinder.width / 2
        var centerY = viewFinder.height / 2

        var rotationInDegrees = when (viewFinder.display.rotation) {
            Surface.ROTATION_0 -> 0
            Surface.ROTATION_90 -> 90
            Surface.ROTATION_180 -> 180
            Surface.ROTATION_270 -> 270
            else -> return
        }
        matrix.postRotate(-rotationInDegrees.toFloat(), centerX.toFloat(), centerY.toFloat())
        viewFinder.setTransform(matrix)
    }
}

