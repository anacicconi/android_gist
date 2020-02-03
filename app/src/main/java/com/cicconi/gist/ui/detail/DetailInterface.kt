package com.cicconi.gist.ui.detail

import com.cicconi.gist.model.Gist

class DetailInterface {
    interface UI {
        fun displayGist(gist: Gist)
    }

    interface Presenter {
        fun bind(activity: UI)
        fun getGistDetail(id: String)
    }
}