package com.androidfinal_hygor_costa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.Toast
import okhttp3.*
import com.androidfinal_hygor_costa.databinding.ActivityDetailsBinding
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import java.io.IOException


class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_details)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = intent.getStringExtra(getString(R.string.details_title_key))

        val url = intent.getStringExtra(getString(R.string.details_url_key))
        val htmlUrl = intent.getStringExtra(getString(R.string.details_html_url_key))

        val content = SpannableString(htmlUrl)
        content.setSpan(UnderlineSpan(), 0 , htmlUrl?.length ?: 0, 0 )
        binding.htmlURLTextView.text = content

        binding.htmlURLTextView.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra(getString(R.string.url_key), htmlUrl)
            startActivity(intent)
        }

        url?.let {
            fetchJson(it)
        }

    }

    private fun fetchJson(url: String){

        // We are using okhttp client here, not Retrofit2
        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback { // can't execute from main thread!
            override fun onFailure(call: Call, e: IOException) {
                toast("Request Failed! ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {

                val body = response.body()?.string()

                val gson = GsonBuilder().create()
                val result = gson.fromJson(body, UserDetails::class.java)

                runOnUiThread {
                    Picasso.get().load(result.avatar_url).into(binding.avatarImageView)

                    binding.nameTextView.text =  getString(R.string.user_name, result?.name ?: "Unknown")
                    binding.locationTextView.text =  getString(R.string.user_location, result?.location ?: "Unknown")
                    binding.companyTextView.text =  getString(R.string.user_company, result?.company ?: "Unknown")
                    binding.followersTextView.text =  getString(R.string.user_followers, result?.followers ?: "Unknown")
                    binding.publicGistTextView.text =  getString(R.string.user_public_gist, result?.public_gists ?: "Unknown")
                    binding.publicReposTextView.text =  getString(R.string.user_public_repos, result?.public_repos ?: "Unknown")
                    binding.lastUpdateTextView.text = getString(R.string.user_last_update, result?.updated_at?.substring(0, 10) ?: "Unknown")
                    binding.accountCreatedTextView.text = getString(R.string.user_account_created, result?.created_at?.substring(0, 10) ?: "Unknown")

                }
            }
        })
    }

    // method to show toast message
    fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}

//https://brightspace.algonquincollege.com/d2l/le/content/474457/Home?itemIdentifier=D2L.LE.Content.ContentObject.ModuleCO-7300976

//https://brightspace.algonquincollege.com/d2l/le/content/474457/Home?itemIdentifier=D2L.LE.Content.ContentObject.ModuleCO-7300978

//Notes:
//
//You need to add a new TextView control for all 7 new fields.
//
//You need to use String resources (strings.xml) for each new field.
//
//You need to get the data for all 7 fields from the results object in the fetchJson method in the DetailsActivity.
//
//You need to match the numeric output style shown in the image above.
//
//You need to match the date format/style shown in the image above.
//
//Use the provided Name field as a guide