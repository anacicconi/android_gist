package com.cicconi.gist.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cicconi.gist.R
import com.cicconi.gist.dagger.DaggerAppComponent
import com.cicconi.gist.model.Gist
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailActivity : AppCompatActivity(), DetailInterface.UI {

    @Inject
    lateinit var presenter: DetailInterface.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        DaggerAppComponent.create().inject(this)

        val gist: Gist? = intent.getSerializableExtra("gist") as? Gist
        displayGistHeader(gist)

        presenter.bind(this)

        gist?.id?.let {it ->
            presenter.getGistDetail(it)
        }
    }

    private fun displayGistHeader(gist: Gist?) {
        if(null !== gist) {
            Picasso.get()
                .load(gist.owner?.avatar_url)
                .resize(200, 200).centerCrop()
                .placeholder(R.drawable.user_placeholder)
                .into(ivUserImg)
            tvUrl.text = gist.url
            tvDescription.text = gist.description
            tvUserLogin.text = gist.owner?.login
        }
    }

    override fun displayGist(gist: Gist) {
        tvFilename.text = gist.files?.entries?.first()?.key
        tvFileContent.text = gist.files?.entries?.first()?.value?.content
    }

    companion object {
        private val TAG = DetailActivity::class.java.simpleName
    }
}
