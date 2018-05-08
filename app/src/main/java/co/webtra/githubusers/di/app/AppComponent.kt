package co.webtra.githubusers.di.app

import co.webtra.githubusers.base.GithubUsersApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

/**
 * Created by andreassusilo on 07/05/18.
 */
@Component(modules = arrayOf(AndroidInjectionModule::class, ActivityBuilder::class, AppModule::class))
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(githubUsersApplication: GithubUsersApplication): Builder

        fun build(): AppComponent
    }

    fun inject(githubUsersApplication: GithubUsersApplication)
}