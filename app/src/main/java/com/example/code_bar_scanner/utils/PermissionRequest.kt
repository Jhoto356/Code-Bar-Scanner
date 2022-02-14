package com.example.code_bar_scanner.utils

//Locale import functions
import android.content.Context
import androidx.appcompat.app.AppCompatActivity

//Third import libraries
import pub.devrel.easypermissions.EasyPermissions

//Created import files
import com.example.code_bar_scanner.utils.Constants.*

class PermissionRequest {

    private  fun verifyCameraP (context: Context): Boolean {
        pCameraG = EasyPermissions.hasPermissions(context, pCameraM)
        return pCameraG
    }

    private fun requestCameraP (activity: AppCompatActivity) {
        EasyPermissions.requestPermissions(activity, pCameraR, pCameraCode, pCameraM)
    }

    fun permissionApp (context: Context, activity: AppCompatActivity){
        if (!verifyCameraP(context)) {
            requestCameraP(activity)
        }
        verifyCameraP(context)
    }

}