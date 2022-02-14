package com.example.code_bar_scanner

//Locale import
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

//Created import files
import com.example.code_bar_scanner.activities.CameraActivity
import com.example.code_bar_scanner.databinding.ActivityMainBinding
import com.example.code_bar_scanner.utils.PermissionRequest
import com.example.code_bar_scanner.utils.Constants.*

class MainActivity : AppCompatActivity() {

    //Inflate layout and call elements layout
    private lateinit var binding: ActivityMainBinding
    //Elements necessary by init camera
    private lateinit var context: Context
    private lateinit var activity: AppCompatActivity
    //Classes variables
    private lateinit var permissionRequest: PermissionRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Inflate layout and init binding for the use
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        context = this
        activity = this

        initComponent()

        actionButtons()

        permissionCamera()
    }

    private fun initComponent () {
        permissionRequest = PermissionRequest()
    }

    private fun permissionCamera () {
        permissionRequest.permissionApp(context, activity)
    }

    private fun actionButtons () {
        openCamera()
    }

    private fun openCamera () {
        binding.btnStartScan.setOnClickListener {
            startActivity(Intent(context, CameraActivity::class.java))
        }
    }

}