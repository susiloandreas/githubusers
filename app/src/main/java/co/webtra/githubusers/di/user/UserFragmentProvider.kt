package co.webtra.githubusers.di.user

import co.webtra.githubusers.ui.user.UserFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by andreassusilo on 07/05/18.
 */
@Module
abstract class UserFragmentProvider{

    @ContributesAndroidInjector(modules = arrayOf(UserFragmentModule::class))
    abstract fun contributeUserFragment(): UserFragment
}
