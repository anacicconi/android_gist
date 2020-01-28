package com.cicconi.gist.dagger

import com.cicconi.gist.ui.home.HomeInterface
import com.cicconi.gist.ui.home.HomePresenter
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    open fun providesHomePresenter(): HomeInterface.Presenter {
        return HomePresenter()
    }
}
