package co.webtra.githubusers.di.repo

import co.webtra.githubusers.ui.userdetail.repo.RepoFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by andreassusilo on 08/05/18.
 */
@Module
abstract class RepoFragmentProvider{

    @ContributesAndroidInjector(modules = arrayOf(RepoFragmentModule::class))
    abstract fun contributeRepoFragment(): RepoFragment

}