package com.io.org.visualx.core

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabel
import com.io.org.visualx.activities.MainActivity
import java.nio.ByteBuffer
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean

class Analyzer(context: Context) : ImageAnalysis.Analyzer {

    lateinit var context: Context;

    init {
        this.context = context
    }

    private var analyzingImage = AtomicBoolean(false)
    private val TAG: String = "ImageAnalyzer"


    private fun ByteBuffer.toByteArray(): ByteArray {
        rewind()
        val dataArray = ByteArray(remaining())
        get(dataArray)
        return dataArray
    }

    private var lastAnalyzedTimeStamp = 0L

    override fun analyze(image: ImageProxy?, rotationDegrees: Int) {

        var currentTime = System.currentTimeMillis()

        if (currentTime - lastAnalyzedTimeStamp >= TimeUnit.SECONDS.toMillis(5)) {

            var y = image!!.planes[0]
            var u = image!!.planes[1]
            var v = image!!.planes[2]

            var yB = y.buffer.remaining()
            var uB = u.buffer.remaining()
            var vB = v.buffer.remaining()

            var data = ByteArray(yB + uB + vB)

            y.buffer.get(data, 0, yB) //copying the buffer to the array
            u.buffer.get(data, yB, uB)
            v.buffer.get(data, yB + uB, vB)


            var brightnessData = y.buffer.toByteArray()

            var pixels = brightnessData.map { it.toInt() and 0xFF }
            var luma = pixels.average()
            Log.d(TAG, "Luminousity $luma")


            val metadata = FirebaseVisionImageMetadata.Builder()
                    .setFormat(FirebaseVisionImageMetadata.IMAGE_FORMAT_YV12)
                    .setWidth(image.width)
                    .setHeight(image.height)
                    .setRotation(toFirebaseRotation(rotationDegrees))
                    .build()

            val visionImage: FirebaseVisionImage = FirebaseVisionImage.fromByteArray(data, metadata)


            val imageLabel = FirebaseVision.getInstance().onDeviceImageLabeler


            /*  val detector = FirebaseVision.getInstance().onDeviceTextRecognizer

               val result = detector.processImage(visionImage)
                       .addOnSuccessListener { firebaseVisionText ->
                           for (block in firebaseVisionText.textBlocks) {
                               val blockText = block.text
                               val blockConfidence = block.confidence
                               val blockLanguages = block.recognizedLanguages
                               val blockCornerPoints = block.cornerPoints
                               val blockFrame = block.boundingBox


                               Log.d(TAG, blockText)

                               /* for (line in block.lines) {
                                    val lineText = line.text
                                    val lineConfidence = line.confidence
                                    val lineLanguages = line.recognizedLanguages
                                    val lineCornerPoints = line.cornerPoints
                                    val lineFrame = line.boundingBox
                                }*/
                           }

                       }
                       .addOnFailureListener {
                           // Task failed with an exception
                           // ...
                       }*/









            imageLabel.processImage(visionImage)
                    .addOnSuccessListener { label ->
                        analyzingImage.compareAndSet(true, false)
                        var maxConfidence = label.maxBy { it.confidence > 0.8 }
                        val sharedPreferences = context.getSharedPreferences("PREF_ITEM", Context.MODE_PRIVATE)
                       sharedPreferences.edit().clear().commit()

                        val editor = sharedPreferences.edit()
                        editor.putString("query", maxConfidence!!.text)
                        editor.apply()

                        MainActivity.viewPager.currentItem = 1;

                    }

                    .addOnFailureListener { e ->
                        Log.d(TAG, e.message)
                    }



            lastAnalyzedTimeStamp = currentTime
        }

    }

    private fun toFirebaseRotation(rotationDegrees: Int) = when (rotationDegrees) {
        0 -> FirebaseVisionImageMetadata.ROTATION_0
        90 -> FirebaseVisionImageMetadata.ROTATION_90
        180 -> FirebaseVisionImageMetadata.ROTATION_180
        270 -> FirebaseVisionImageMetadata.ROTATION_270
        else -> throw IllegalAccessException("Error rotation undefined")
    }


    private fun openResultsFragment(labels: List<FirebaseVisionImageLabel>) {
        var labelWithHighestConfidence = labels.maxBy { it.confidence }
        val bundle = Bundle()
        bundle.putString("label", labelWithHighestConfidence!!.text)

    }
}