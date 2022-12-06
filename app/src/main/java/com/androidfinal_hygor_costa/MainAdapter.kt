package com.androidfinal_hygor_costa

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androidfinal_hygor_costa.databinding.DataRowBinding
import com.squareup.picasso.Picasso


class CustomViewHolderClass(private val view: View, var login: String = "",
                    var user: Users? = null    )

    : RecyclerView.ViewHolder(view) {

        val binding = DataRowBinding.bind(view)

        class MainAdapter(private val dataSource: ArrayList<Users>) :
                RecyclerView.Adapter<CustomViewHolderClass>() {


            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): CustomViewHolderClass {
          val layoutInflater = LayoutInflater.from(parent.context)
                val cellForRow = layoutInflater.inflate(R.layout.data_row, parent, false)
                return CustomViewHolderClass(cellForRow)
            }

            override fun onBindViewHolder(holder: CustomViewHolderClass, position: Int) {
                holder.binding.loginTextView.text = TheApp.context.getString(R.string.user_login, dataSource[position].login)
                holder.binding.scoreTextView.text = TheApp.context.getString(R.string.user_score,
                    ( dataSource[position].score + 0.5).toInt().toString() )
                holder.binding.idTextView.text = TheApp.context.getString(R.string.user_id, dataSource[position].id.toString())

                Picasso.get().load(dataSource[position].avatar_url).into(holder.binding.imageView)

                holder.user = dataSource[position]

                holder.login = dataSource[position].login

            }

            override fun getItemCount(): Int = dataSource.size

        }

    init {
        view.setOnClickListener() {
            val intent = Intent(view.context, DetailsActivity::class.java)

            intent.putExtra(view.context.getString(R.string.details_title_key), login)
            intent.putExtra(view.context.getString(R.string.details_url_key), user?.url)
            intent.putExtra(view.context.getString(R.string.details_html_url_key), user?.html_url)

            view.context.startActivity(intent)
        }
    }

    }