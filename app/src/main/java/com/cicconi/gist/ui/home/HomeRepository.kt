package com.cicconi.gist.ui.home

import com.cicconi.gist.api.RetrofitFactory
import com.cicconi.gist.model.Gist
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeRepository @Inject constructor() {

    fun getAllPublicGists(): Observable<List<Gist>> {
        val service = RetrofitFactory.makeRetrofitService()

        return service.getAllPublicGists()
            .subscribeOn(Schedulers.io()) // have to subscribe in another thread
    }
}