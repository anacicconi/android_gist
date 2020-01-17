package com.cicconi.gist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cicconi.gist.R
import com.cicconi.gist.model.Gist

import kotlinx.android.synthetic.main.gist.view.*

class ListAdapter(private val context: Context, private val gists: List<Gist>) : RecyclerView.Adapter<ListAdapter.GistViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GistViewHolder {
        return GistViewHolder(LayoutInflater.from(context).inflate(R.layout.gist, parent, false))
    }

    override fun onBindViewHolder(holder: GistViewHolder, position: Int) {
        holder.positionNumber.text = position.toString()
        holder.url.text = gists.get(position).url
        holder.login.text = gists.get(position).owner?.login
    }

    override fun getItemCount(): Int {
        return gists.size
    }

    class GistViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val positionNumber = view.position
        val url: TextView = view.url
        val login: TextView = view.login
    }
}
