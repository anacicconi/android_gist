package com.cicconi.gist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cicconi.gist.R
import com.cicconi.gist.model.Gist
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.gist.view.*

class GistAdapter(private val context: Context, private val gists: List<Gist>) : RecyclerView.Adapter<GistAdapter.GistViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GistViewHolder {
        return GistViewHolder(LayoutInflater.from(context).inflate(R.layout.gist, parent, false))
    }

    override fun onBindViewHolder(holder: GistViewHolder, position: Int) {
        //holder.positionNumber.text = position.toString()
        holder.url.text = gists.get(position).url
        //TODO: add file list
        holder.filename.text = gists.get(position).files?.entries?.first()?.key
        holder.description.text = if (gists.get(position).description !== "") gists.get(position).description else "Description missing"
        holder.userLogin.text = gists.get(position).owner?.login
        Picasso.with(context)
            .load(gists.get(position).owner?.avatar_url)
            .resize(200, 200).centerCrop()
            .placeholder(R.drawable.user_placeholder)
            .into(holder.userImg)
    }

    override fun getItemCount(): Int {
        return gists.size
    }

    class GistViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //val positionNumber: TextView = view.tvPosition
        val url: TextView = view.tvUrl
        val filename: TextView = view.tvFilename
        val description: TextView = view.tvDescription
        val userLogin: TextView = view.tvUserLogin
        val userImg: ImageView = view.ivUserImg
    }
}
