package com.cicconi.gist.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cicconi.gist.R
import com.cicconi.gist.model.Gist
import com.cicconi.gist.ui.home.HomeActivity
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.gist.view.*

class GistAdapter(private val context: Context, private val gistList: List<Gist>) : RecyclerView.Adapter<GistAdapter.GistViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GistViewHolder {
        return GistViewHolder(LayoutInflater.from(context).inflate(R.layout.gist, parent, false))
    }

    override fun onBindViewHolder(holder: GistViewHolder, position: Int) {
        //holder.positionNumber.text = position.toString()
        holder.url.text = gistList.get(position).url
        //TODO: add file list
        holder.filename.text = gistList.get(position).files?.entries?.first()?.key
        holder.description.text = if (gistList.get(position).description !== "") gistList.get(position).description else "Description missing"
        holder.userLogin.text = gistList.get(position).owner?.login
        Picasso.get()
            .load(gistList.get(position).owner?.avatar_url)
            .resize(200, 200).centerCrop()
            .placeholder(R.drawable.user_placeholder)
            .into(holder.userImg)

        // replace this by https://medium.com/android-gate/recyclerview-item-click-listener-the-right-way-daecc838fbb9
        // https://www.littlerobots.nl/blog/Handle-Android-RecyclerView-Clicks/
        holder.itemView.setOnClickListener {
            Log.i(TAG, "click on recycler view item")
            (context as HomeActivity).openItem(gistList.get(position))
        }
    }

    override fun getItemCount(): Int {
        return gistList.size
    }

    class GistViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //val positionNumber: TextView = view.tvPosition
        val url: TextView = view.tvUrl
        val filename: TextView = view.tvFilename
        val description: TextView = view.tvDescription
        val userLogin: TextView = view.tvUserLogin
        val userImg: ImageView = view.ivUserImg
    }

    companion object {
        private val TAG = GistAdapter::class.java.simpleName
    }
}
