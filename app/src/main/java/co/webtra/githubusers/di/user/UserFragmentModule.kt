package co.webtra.githubusers.di.user

import co.webtra.githubusers.network.Service
import co.webtra.githubusers.ui.user.UserContract
import co.webtra.githubusers.ui.user.UserFragment
import co.webtra.githubusers.ui.user.UserPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by andreassusilo on 07/05/18.
 */
@Module
class UserFragmentModule {

    @Provides
    fun provideUserView(userFragment: UserFragment): UserContract.View {
        return userFragment
    }

    @Provides
    fun provideUserPresenter(view: UserContract.View, service: Service): UserContract.Presenter {
        return UserPresenter(view, service)
    }

}