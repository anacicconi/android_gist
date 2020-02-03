package com.cicconi.gist.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.cicconi.gist.R
import com.cicconi.gist.api.RetrofitFactory
import com.cicconi.gist.model.Gist
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    //private var gist: Gist? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val gistOld: Gist? = intent.getSerializableExtra("gist") as? Gist

        if(null !== gistOld) {
            Picasso.get()
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
                .subscribeOn(Schedulers.io()) // have to subscribe in another thread
                .observeOn(AndroidSchedulers.mainThread()) // have to come back to the main thread because the other one can't touch the view
                .subscribe(
                    { it ->
                        Log.d(TAG, "Got Gist")
                        tvFilename.text = it?.files?.entries?.first()?.key
                        tvFileContent.text = it?.files?.entries?.first()?.value?.content
                    },
                    {
                        Log.d(TAG, "Error: " + it.message)
                    }
                )
        }
    }

    fun getSingleGist(id: String): Observable<Gist> {
        val service = RetrofitFactory.makeRetrofitService()
        Log.d(TAG, id)

        return service.getSingleGist(id)
    }

    companion object {
        private val TAG = DetailActivity::class.java.simpleName
    }
}
