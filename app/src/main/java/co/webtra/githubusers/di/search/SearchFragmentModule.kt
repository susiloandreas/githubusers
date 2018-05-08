package co.webtra.githubusers.di.search

import co.webtra.githubusers.data.sharedpref.RecentSearchManager
import co.webtra.githubusers.network.Service
import co.webtra.githubusers.ui.search.SearchContract
import co.webtra.githubusers.ui.search.SearchFragment
import co.webtra.githubusers.ui.search.SearchPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by andreassusilo on 07/05/18.
 */
@Module
class SearchFragmentModule {

    @Provides
    fun provideSearchView(searchFragment: SearchFragment): SearchContract.View {
        return searchFragment
    }

    @Provides
    fun provideSearchPresenter(view: SearchContract.View, service: Service, recentSearchManager: RecentSearchManager): SearchContract.Presenter {
        return SearchPresenter(view, service, recentSearchManager)
    }

}