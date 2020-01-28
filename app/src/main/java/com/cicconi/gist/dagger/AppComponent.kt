package com.cicconi.gist.dagger

import com.cicconi.gist.ui.home.HomeActivity
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(app: HomeActivity)
}
