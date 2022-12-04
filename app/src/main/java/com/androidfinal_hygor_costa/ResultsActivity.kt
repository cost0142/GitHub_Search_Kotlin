package com.androidfinal_hygor_costa

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.androidfinal_hygor_costa.databinding.ActivityResultsBinding

class ResultsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        binding = ActivityResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data: ArrayList<Users>?

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            data = intent.getParcelableArrayListExtra(getString(R.string.user_data_key), Users::class.java)
        } else {
            @Suppress("DEPRECATION")
            data = intent.getParcelableArrayListExtra(getString(R.string.user_data_key))
        }

        supportActionBar?.title = "${data?.size} Results"
    }

}