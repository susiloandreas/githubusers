package co.webtra.githubusers.ui.search

import android.util.Log
import co.webtra.githubusers.data.sharedpref.RecentSearchManager
import co.webtra.githubusers.model.User
import co.webtra.githubusers.network.Service
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by andreassusilo on 07/05/18.
 */
class SearchPresenter @Inject constructor(val view: SearchContract.View, val service: Service, val recentSearchManager: RecentSearchManager) : SearchContract.Presenter {

    override fun subscribeUserOnClick() {
        val disposable = view.getUserLoginObservable().subscribe { userLogin ->

            recentSearchManager.getRecentSearches()?.let {
                Log.e("SearchPresenter","subscribeUserOnClick")
                val recentSearches = recentSearchManager.getRecentSearches()!!.toMutableSet()
                recentSearches!!.add(userLogin)
                recentSearchManager.putRecentSearches(recentSearches!!.toSet())
            }

            view.moveToUserDetailActivity(userLogin)
        }
        compositeDisposable.add(disposable)
    }

    private val compositeDisposable = CompositeDisposable()

    override fun searchUser(username: String, page: Int) {
        val disposable = service.searchUser(username, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribe({ response ->
                    if (response.isSuccessful()) {
                        Log.e("UserPresenter", Gson().toJson(response.body()!!))
                        view.onSuccessSearchUser(response.body()!!)
                    } else {
                        view.onFailedSearchUser()
                    }
                })
        compositeDisposable.add(disposable)
    }


    override fun subscribeSearchOnTextChanges() {
        val disposable = view.getSearchObservable().subscribe({ search ->
            searchUser(search, 1)
        })
        compositeDisposable.add(disposable)
    }

    override fun bind() {
        subscribeSearchOnTextChanges()
        subscribeUserOnClick()
        loadRecentSearches()
    }

    override fun loadRecentSearches() {
        view.setRecentSearches(recentSearchManager.getRecentSearches())
    }

    override fun unBind() {
        compositeDisposable.clear()
    }
}