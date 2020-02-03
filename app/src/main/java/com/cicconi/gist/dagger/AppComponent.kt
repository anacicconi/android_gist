package com.cicconi.gist.dagger

import com.cicconi.gist.ui.detail.DetailActivity
import com.cicconi.gist.ui.home.HomeActivity
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(app: HomeActivity)

    fun inject(app: DetailActivity)
}
