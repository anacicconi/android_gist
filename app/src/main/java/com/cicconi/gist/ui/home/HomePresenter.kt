package com.cicconi.gist.ui.home

import android.util.Log
import com.cicconi.gist.api.RetrofitFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomePresenter @Inject constructor(): HomeInterface.Presenter {

    private lateinit var activity: HomeInterface.UI

    override fun bind(activity: HomeInterface.UI) {
        this.activity = activity

        getPublicGists(this.activity)
    }

    fun getPublicGists(activity: HomeInterface.UI) {
        val service = RetrofitFactory.makeRetrofitService()

        service.getAllPublicGists()
            .subscribeOn(Schedulers.io()) // have to subscribe in another thread
            .observeOn(AndroidSchedulers.mainThread()) // have to come back to the main thread because the other one can't touch the view
            .subscribe(
                { it ->
                    Log.d(TAG, "Got Gists")
                    activity.updateList(it)
                },
                {
                    Log.d(TAG, "Error: " + it.message)
                }
            )
    }

    companion object {
        private val TAG = HomePresenter::class.java.simpleName
    }
}
