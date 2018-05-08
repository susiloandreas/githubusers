package co.webtra.githubusers.di.search

import co.webtra.githubusers.ui.search.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by andreassusilo on 07/05/18.
 */
@Module
abstract class SearchFragmentProvider{

    @ContributesAndroidInjector(modules = arrayOf(SearchFragmentModule::class))
    abstract fun contributeSearchFragment(): SearchFragment

}
