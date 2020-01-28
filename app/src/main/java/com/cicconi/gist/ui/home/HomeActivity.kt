package com.cicconi.gist.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cicconi.gist.R
import com.cicconi.gist.adapter.GistAdapter
import com.cicconi.gist.dagger.DaggerAppComponent
import com.cicconi.gist.model.Gist
import com.cicconi.gist.ui.detail.DetailActivity
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), HomeInterface.UI {

    @Inject
    lateinit var presenter: HomeInterface.Presenter

    private var adapter: GistAdapter?= null
    private var gists: MutableList<Gist> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        DaggerAppComponent.create().inject(this)

        val listRecyclerView: RecyclerView = findViewById(R.id.listRecyclerView)
        listRecyclerView.layoutManager = LinearLayoutManager(this)

        adapter = GistAdapter(this, gists)
        listRecyclerView.adapter = adapter

        presenter.bind(this, gists)
    }

    override fun updateList() {
        adapter!!.notifyDataSetChanged()
    }

    fun openItem(gist: Gist) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("gist", gist)

        startActivity(intent)
    }

    companion object {
        private val TAG = HomeActivity::class.java.simpleName
    }
}