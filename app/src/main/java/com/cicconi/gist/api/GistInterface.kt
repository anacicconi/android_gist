package com.cicconi.gist.api

import com.cicconi.gist.model.Gist
import retrofit2.Call
import retrofit2.http.GET

interface GistInterface {
    @GET("gists/public")
    fun getAllPublicGists(): Call<List<Gist>>
}
