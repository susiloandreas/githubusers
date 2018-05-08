package co.webtra.githubusers.base

import android.app.Activity
import android.support.multidex.MultiDexApplication
import co.webtra.githubusers.di.app.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by andreassusilo on 07/05/18.
 */

class GithubUsersApplication : MultiDexApplication(), HasActivityInjector {


    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().application(this).build().inject(this)
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

}