package co.webtra.githubusers.di.profile

import co.webtra.githubusers.network.Service
import co.webtra.githubusers.ui.userdetail.profile.ProfileContract
import co.webtra.githubusers.ui.userdetail.profile.ProfileFragment
import co.webtra.githubusers.ui.userdetail.profile.ProfilePresenter
import dagger.Module
import dagger.Provides

/**
 * Created by andreassusilo on 08/05/18.
 */
@Module
class ProfileFragmentModule {

    @Provides
    fun provideProfileView(profileFragment: ProfileFragment): ProfileContract.View {
        return profileFragment
    }

    @Provides
    fun provideSearchPresenter(view: ProfileContract.View, service: Service): ProfileContract.Presenter {
        return ProfilePresenter(view, service)
    }

}