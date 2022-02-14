package com.example.code_bar_scanner.activities

//Locale imports
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat

//Created import
import com.example.code_bar_scanner.databinding.ActivityCameraBinding
import com.example.code_bar_scanner.utils.Constants.*
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.common.util.concurrent.ListenableFuture
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage

class CameraActivity : AppCompatActivity(), ImageAnalysis.Analyzer {

    private lateinit var binding: ActivityCameraBinding
    private  lateinit var formatsScan: BarcodeScannerOptions
    private lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>
    private lateinit var cameraProvider: ProcessCameraProvider
    private lateinit var cameraSelector: CameraSelector
    private lateinit var preview: Preview
    private lateinit var scanner: BarcodeScanner
    private lateinit var image: InputImage
    private lateinit var mediaImage: ImageProxy

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initComponents()
        startCamera()

    }

    companion object {

        const val TAG = "Camera Activity"

    }

    private fun initComponents () {
        formatsScan = BarcodeScannerOptions.Builder().setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS).build()
        cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProvider = cameraProviderFuture.get()
        cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
        preview = Preview.Builder().build().also { it.setSurfaceProvider(binding.pvCamera.surfaceProvider) }
        scanner = BarcodeScanning.getClient(formatsScan)
    }

    private fun startCamera () {
        cameraProviderFuture.addListener({
            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview)
            } catch(exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }
        }, ContextCompat.getMainExecutor(this))
    }


    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image
        if (mediaImage != null) {
            image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
        } else {
            println("Error")
        }
    }

}