package com.cicconi.gist.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.cicconi.gist.R
import com.cicconi.gist.api.RetrofitFactory
import com.cicconi.gist.model.Gist
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    private var gist: Gist? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val gistOld: Gist? = intent.getSerializableExtra("gist") as? Gist

        if(null !== gistOld) {
            Picasso.with(this)
                .load(gistOld.owner?.avatar_url)
                .resize(200, 200).centerCrop()
                .placeholder(R.drawable.user_placeholder)
                .into(ivUserImg)
            tvUrl.text = gistOld.url
            tvDescription.text = gistOld.description
            tvUserLogin.text = gistOld.owner?.login
        }

        gistOld?.id?.let {it ->
            getSingleGist(it)
            tvFilename.text = gist?.files?.entries?.first()?.key
            tvFileContent.text = gist?.files?.entries?.first()?.value?.content
        }
    }

    fun getSingleGist(id: String) {
        val service = RetrofitFactory.makeRetrofitService()
        Log.d(TAG, id)
        val call: Call<Gist> = service.getSingleGist(id)
        call.enqueue(object : Callback<Gist> {
            override fun onResponse(call: Call<Gist>?, response: Response<Gist>?) {

                if (200 == response?.code()) {
                    Log.d(TAG, "Got Gist")

                    response.body()?.let { body ->
                        gist = body
                    }
                }
            }

            override fun onFailure(call: Call<Gist>?, t: Throwable?) {
                Log.d(TAG, "Error: " + t.toString())
            }
        })
    }

    companion object {
        private val TAG = DetailActivity::class.java.simpleName
    }
}
