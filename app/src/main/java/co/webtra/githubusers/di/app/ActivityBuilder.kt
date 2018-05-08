package co.webtra.githubusers.di.app

import co.webtra.githubusers.di.profile.ProfileFragmentProvider
import co.webtra.githubusers.di.repo.RepoFragmentProvider
import co.webtra.githubusers.di.search.SearchFragmentProvider
import co.webtra.githubusers.di.user.UserFragmentProvider
import co.webtra.githubusers.ui.search.SearchActivity
import co.webtra.githubusers.ui.user.UserActivity
import co.webtra.githubusers.ui.userdetail.UserDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by andreassusilo on 07/05/18.
 */


@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = arrayOf(UserFragmentProvider::class))
    abstract fun contributeUserActivity(): UserActivity

    @ContributesAndroidInjector(modules = arrayOf(SearchFragmentProvider::class))
    abstract fun contributeSearchActivity(): SearchActivity

    @ContributesAndroidInjector(modules = arrayOf(RepoFragmentProvider::class, ProfileFragmentProvider::class))
    abstract fun contributeUserDetailActivity(): UserDetailActivity
}