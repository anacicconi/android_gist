package com.cicconi.gist.api

import com.cicconi.gist.model.Gist
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface GistInterface {
    @GET("gists/public")
    fun getAllPublicGists(): Observable<List<Gist>>

    @GET("gists/{id}")
    fun getSingleGist(@Path("id") id: String): Observable<Gist>
}
