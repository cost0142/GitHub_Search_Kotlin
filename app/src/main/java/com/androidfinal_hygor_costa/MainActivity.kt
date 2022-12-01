package com.androidfinal_hygor_costa

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.MotionEvent
import android.view.accessibility.AccessibilityEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.androidfinal_hygor_costa.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    private var searchString = ""

    private val minPage = 1
    private val maxPage = 100
    private val startPage = 30

    private val baseUrl = "https://api.github.com/search/"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener {
            fetchJSONData()

        }

        binding.perPageNumberPicker.minValue = minPage
        binding.perPageNumberPicker.maxValue = maxPage
        binding.perPageNumberPicker.value = startPage

    }

    private fun fetchJSONData() {

    }

    // region: Options Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {



        val inflater = MenuInflater(this)
        inflater.inflate(R.menu.main_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId){
            R.id.menu_about -> {
           val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                return true
            }
        }


        return super.onOptionsItemSelected(item)

    }

    // endregion

    // region keyboard - hide the soft keyboard when no input
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {

        currentFocus?.let {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }

        return super.dispatchTouchEvent(ev)
    }
// endregion

// region Toast Function
fun toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
// endregion

}