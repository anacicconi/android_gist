package com.cicconi.gist.dagger

import com.cicconi.gist.ui.detail.DetailInterface
import com.cicconi.gist.ui.detail.DetailPresenter
import com.cicconi.gist.ui.detail.DetailRepository
import com.cicconi.gist.ui.home.HomeInterface
import com.cicconi.gist.ui.home.HomePresenter
import com.cicconi.gist.ui.home.HomeRepository
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    // Normally, I wouldn't need to provide the presenter here but I'm using interfaces
    // and dagger does not know which class instantiate
    @Provides
    fun providesHomePresenter(homeRepository: HomeRepository): HomeInterface.Presenter {
        return HomePresenter(homeRepository)
    }

    // As I'm obliged to instantiate the presenter I have to provide the repo too
    @Provides
    fun providesHomeRepository(): HomeRepository {
        return HomeRepository()
    }

    @Provides
    fun providesDetailPresenter(detailRepository: DetailRepository): DetailInterface.Presenter {
        return DetailPresenter(detailRepository)
    }

    @Provides
    fun providesDetailRepository(): DetailRepository {
        return DetailRepository()
    }
}
