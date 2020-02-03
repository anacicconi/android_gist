package com.cicconi.gist.ui.detail

import com.cicconi.gist.api.RetrofitFactory
import com.cicconi.gist.model.Gist
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailRepository @Inject constructor() {

    fun getSingleGist(id: String): Observable<Gist> {

        val service = RetrofitFactory.makeRetrofitService()

        return service.getSingleGist(id)
            .subscribeOn(Schedulers.io()) // have to subscribe in another thread
    }
}