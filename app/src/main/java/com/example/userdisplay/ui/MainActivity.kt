package com.example.userdisplay.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.userdisplay.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        try {
            super.onActivityResult(requestCode, resultCode, data)
            if (getForegroundFragment() != null) {
                getForegroundFragment()!!.onActivityResult(requestCode, resultCode, data)
            }
        } catch (e: Exception) {
            Log.e("MainActivity","",e)
        }
    }
    private fun getForegroundFragment(): Fragment? {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.user)
        return navHostFragment?.childFragmentManager?.fragments?.get(0)
    }
}