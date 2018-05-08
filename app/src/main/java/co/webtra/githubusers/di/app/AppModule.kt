package co.webtra.githubusers.di.app

import android.content.Context
import co.webtra.githubusers.base.GithubUsersApplication
import co.webtra.githubusers.data.constant.ApiRoute
import co.webtra.githubusers.data.sharedpref.RecentSearchManager
import co.webtra.githubusers.network.Service
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by andreassusilo on 07/05/18.
 */
@Singleton
@Module
class AppModule {
    private val tokenPreferencesName: String = "TOKEN_SHARED_PREFERENCES"

    @Provides
    internal fun provideContext(application: GithubUsersApplication): Context {
        return application
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
            .baseUrl(ApiRoute.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(provideOkHttp())
            .build()

    @Provides
    fun provideOkHttp(): OkHttpClient = OkHttpClient.Builder().addInterceptor(provideLoggingInterceptor()).build()

    @Provides
    fun provideService(): Service = provideRetrofit().create(Service::class.java)

    @Provides
    fun provideRecentSearchManageer(context: Context): RecentSearchManager {
        return RecentSearchManager(context.getSharedPreferences(tokenPreferencesName, Context.MODE_PRIVATE))
    }


}