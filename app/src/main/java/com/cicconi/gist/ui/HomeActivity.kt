package com.cicconi.gist.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cicconi.gist.R
import com.cicconi.gist.adapter.GistAdapter
import com.cicconi.gist.api.RetrofitFactory
import com.cicconi.gist.model.Gist
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {
    //private var gistUrl: TextView? = null

    private var adapter: GistAdapter?= null
    private var gists: MutableList<Gist> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val listRecyclerView: RecyclerView = findViewById(R.id.listRecyclerView)
        listRecyclerView.layoutManager = LinearLayoutManager(this)

        adapter = GistAdapter(this, gists)
        listRecyclerView.adapter = adapter

        //gistUrl = findViewById(R.id.url)

        getCurrentData()
    }

    fun getCurrentData() {
        val service = RetrofitFactory.makeRetrofitService()
        val call: Call<List<Gist>> = service.getAllPublicGists()
        call.enqueue(object : Callback<List<Gist>> {
            override fun onResponse(call: Call<List<Gist>>?, response: Response<List<Gist>>?) {
                if (200 == response?.code()) {
                    Log.d(TAG, "Total Gists: " + response.body()!!.size)

                    val gistsResponse = response.body()!!

                    if(gistsResponse !== null) {
                        gists.addAll(gistsResponse)
                        adapter!!.notifyDataSetChanged()
                    }
                    //gistUrl?.setText(gists[0].url)
                }
            }

            override fun onFailure(call: Call<List<Gist>>?, t: Throwable?) {
                Log.d(TAG, "Error: " + t.toString())
            }
        })
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