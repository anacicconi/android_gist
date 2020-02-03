package com.cicconi.gist.ui.detail

import android.util.Log
import com.cicconi.gist.api.RetrofitFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailPresenter @Inject constructor(): DetailInterface.Presenter {

    private lateinit var activity: DetailInterface.UI

    override fun bind(activity: DetailInterface.UI) {
        this.activity = activity
    }

    override fun getGistDetail(id: String) {
        val service = RetrofitFactory.makeRetrofitService()

        service.getSingleGist(id)
            .subscribeOn(Schedulers.io()) // have to subscribe in another thread
            .observeOn(AndroidSchedulers.mainThread()) // have to come back to the main thread because the other one can't touch the view
            .subscribe(
                { it ->
                    Log.d(TAG, "Got Gist")
                    activity.displayGist(it)
                },
                {
                    Log.d(TAG, "Error: " + it.message)
                }
            )
    }

    companion object {
        private val TAG = DetailPresenter::class.java.simpleName
    }
}