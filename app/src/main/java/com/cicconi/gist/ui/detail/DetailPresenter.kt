package com.cicconi.gist.ui.detail

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class DetailPresenter @Inject constructor(private val detailRepository: DetailRepository): DetailInterface.Presenter {

    private lateinit var activity: DetailInterface.UI

    override fun bind(activity: DetailInterface.UI) {
        this.activity = activity
    }

    override fun getGistDetail(id: String) {
        detailRepository.getSingleGist(id)
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

/*
// The whole call to the rx observable

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
*/