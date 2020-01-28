package com.cicconi.gist.ui.home

import com.cicconi.gist.model.Gist

interface HomeInterface {

    interface UI {
        fun updateList()
    }

    interface Presenter {
        fun bind(activity: UI, gists:MutableList<Gist>)
    }
}