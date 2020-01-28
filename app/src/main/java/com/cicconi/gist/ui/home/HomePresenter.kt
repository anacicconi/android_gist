package com.cicconi.gist.ui.home

import android.util.Log
import com.cicconi.gist.api.RetrofitFactory
import com.cicconi.gist.model.Gist
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class HomePresenter @Inject constructor(): HomeInterface.Presenter {

    private lateinit var activity: HomeInterface.UI
    private lateinit var gists: MutableList<Gist>

    override fun bind(activity: HomeInterface.UI, gists: MutableList<Gist>) {
        this.activity = activity
        this.gists = gists

        getPublicGists(this.activity, this.gists)
    }

    fun getPublicGists(activity: HomeInterface.UI, gists: MutableList<Gist>) {
        val service = RetrofitFactory.makeRetrofitService()
        val call: Call<List<Gist>> = service.getAllPublicGists()
        call.enqueue(object : Callback<List<Gist>> {
            override fun onResponse(call: Call<List<Gist>>?, response: Response<List<Gist>>?) {
                if (200 == response?.code()) {
                    Log.d(TAG, "Total Gists: " + response.body()!!.size)

                    val gistsResponse = response.body()

                    if(gistsResponse !== null) {
                        gists.addAll(gistsResponse)
                        activity.updateList()
                    }
                }
            }

            override fun onFailure(call: Call<List<Gist>>?, t: Throwable?) {
                Log.d(TAG, "Error: " + t.toString())
            }
        })
    }

    companion object {
        private val TAG = HomePresenter::class.java.simpleName
    }
}
