package co.webtra.githubusers.di.repo

import co.webtra.githubusers.network.Service
import co.webtra.githubusers.ui.userdetail.repo.RepoContract
import co.webtra.githubusers.ui.userdetail.repo.RepoFragment
import co.webtra.githubusers.ui.userdetail.repo.RepoPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by andreassusilo on 08/05/18.
 */
@Module
class RepoFragmentModule {

    @Provides
    fun provideRepoView(repoFragment: RepoFragment): RepoContract.View {
        return repoFragment
    }

    @Provides
    fun provideSearchPresenter(view: RepoContract.View, service: Service): RepoContract.Presenter {
        return RepoPresenter(view, service)
    }

}