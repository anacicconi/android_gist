package com.cicconi.gist.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cicconi.gist.R
import com.cicconi.gist.model.Gist
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val gist: Gist? = intent.getSerializableExtra("gist") as? Gist

        if(null !== gist) {
            Picasso.with(this)
                .load(gist.owner?.avatar_url)
                .resize(200, 200).centerCrop()
                .placeholder(R.drawable.user_placeholder)
                .into(ivUserImg)
            tvUrl.text = gist.url
            tvDescription.text = gist.description
            tvUserLogin.text = gist.owner?.login
        }
    }
}
