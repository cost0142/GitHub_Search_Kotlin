package com.androidfinal_hygor_costa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }



fun toast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

}