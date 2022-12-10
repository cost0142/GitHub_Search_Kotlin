package com.androidfinal_hygor_costa

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.text.TextUtils
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.accessibility.AccessibilityEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.androidfinal_hygor_costa.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private val localStorage = LocalStorage()
    private lateinit var binding: ActivityMainBinding

    private val internetConnection = InternetConnection(this)

    private var searchString = ""
    private val minPage = 1
    private val maxPage = 100
    private val startPage = 30

    private val baseUrl = "https://api.github.com/search/"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        if (!internetConnection.isConnected) {
            AlertDialog.Builder(this)
                .setTitle(R.string.message_title)
                .setMessage(R.string.message_text)
                .setIcon(R.drawable.ic_baseline_network_check_24)
                .setNegativeButton(R.string.quit){ _, _ ->
                    finish()
                }
                .setCancelable(false)
                .show()
        } else {


            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            binding.searchButton.setOnClickListener {
                fetchJSONData()
            }

            binding.perPageNumberPicker.minValue = minPage
            binding.perPageNumberPicker.maxValue = maxPage
            binding.perPageNumberPicker.value = startPage

            if (localStorage.contains(getString(R.string.repos_key))) {
                binding.minReposEditText.setText(localStorage.getValueString(getString(R.string.repos_key)))
                print(getString(R.string.repos_key))
            } else {
                binding.minReposEditText.setText("0")
            }

            if (localStorage.contains(getString(R.string.followers_key))) {
                binding.minFollowersEditText.setText(localStorage.getValueString(getString(R.string.followers_key)))
            } else {
                binding.minFollowersEditText.setText("0")
            }

            if (localStorage.contains(getString(R.string.page_size_key))) {
                binding.perPageNumberPicker.value =
                    localStorage.getValueInt(getString(R.string.page_size_key))
            } else {
                binding.perPageNumberPicker.value = 0
            }
        }

    }

    //region function onStop
    override fun onStop() {
        super.onStop()
        localStorage.save(getString(R.string.repos_key), binding.minReposEditText.text.toString())
        localStorage.save(getString(R.string.followers_key), binding.minFollowersEditText.text.toString())
        localStorage.save(getString(R.string.page_size_key), binding.perPageNumberPicker.value.toString().toInt())
    }

    //endregion

    private fun fetchJSONData() {

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val restApi = retrofit.create(RestApi::class.java)

        if(TextUtils.isEmpty(binding.minFollowersEditText.text)) {
            binding.minFollowersEditText.setText("0")

        }

        if(TextUtils.isEmpty(binding.minReposEditText.text)) {
            binding.minReposEditText.setText("0")
        }

        val minNumberOfFollowers = binding.minFollowersEditText.text.toString().toInt()

        val minNumbersOfRepos = binding.minReposEditText.text.toString().toInt()

        val searchString = "${binding.searchUser.text} repos:>$minNumbersOfRepos followers:>=$minNumberOfFollowers"

        val call = restApi.getUserData(searchString, binding.perPageNumberPicker.value)

        binding.progressBar.visibility = View.VISIBLE

        call.enqueue( object: Callback<ResponseDataClass>

        {
            override fun onResponse(
                call: Call<ResponseDataClass>,
                response: Response<ResponseDataClass>
            ) {
                val responseBody = response.body()

                val users = responseBody?.items
                val numberOfUsers = users?.size ?: 0

                if(numberOfUsers > 0) {


                    val intent = Intent(TheApp.context, ResultsActivity::class.java)

                    intent.putParcelableArrayListExtra(getString(R.string.user_data_key), users)

                    startActivity(intent)
                }

                binding.progressBar.visibility = View.GONE



            }

            override fun onFailure(call: Call<ResponseDataClass>, t: Throwable) {
                toast(t.message.toString())
                binding.progressBar.visibility = View.GONE
            }


        } )
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