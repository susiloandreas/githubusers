package co.webtra.githubusers.di.profile

import co.webtra.githubusers.ui.userdetail.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by andreassusilo on 08/05/18.
 */
@Module
abstract class ProfileFragmentProvider{

    @ContributesAndroidInjector(modules = arrayOf(ProfileFragmentModule::class))
    abstract fun contributeProfileFragment(): ProfileFragment

}