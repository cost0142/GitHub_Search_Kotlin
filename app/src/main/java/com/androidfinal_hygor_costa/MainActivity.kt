package com.androidfinal_hygor_costa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.widget.Toast
import com.androidfinal_hygor_costa.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var bilding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        bilding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bilding.root)

    }


    // region Toast Function
    fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    // endregion

}