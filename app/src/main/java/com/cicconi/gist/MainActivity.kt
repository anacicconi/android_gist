package com.cicconi.gist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.cicconi.gist.api.GistInterface
import com.cicconi.gist.model.Gist
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cicconi.gist.adapter.ListAdapter

class MainActivity : AppCompatActivity() {

    //private var gistUrl: TextView? = null

    private var adapter: ListAdapter?= null;
    private var gists: MutableList<Gist> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listRecyclerView: RecyclerView = findViewById(R.id.listRecyclerView)
        listRecyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ListAdapter(this, gists)
        listRecyclerView.adapter = adapter

        //gistUrl = findViewById(R.id.url)

        getCurrentData()
    }

    fun getCurrentData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(GistInterface::class.java!!)
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

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}
