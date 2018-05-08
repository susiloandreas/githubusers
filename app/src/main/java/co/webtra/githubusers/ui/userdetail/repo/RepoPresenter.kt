package co.webtra.githubusers.ui.userdetail.repo

import co.webtra.githubusers.network.Service
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by andreassusilo on 07/05/18.
 */
class RepoPresenter @Inject constructor(val view: RepoContract.View, val service: Service) : RepoContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun bind() {
        getRepo(view.getUsrname(), 1)
    }

    override fun unBind() {
        compositeDisposable.clear()
    }

    override fun getRepo(username: String, page: Int) {
        val disposable = service.getRepo(username)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribe({ response ->
                    if (response.isSuccessful()) {
                        view.onSuccessGetRepo(response.body()!!)
                    } else {
                        //view.onFailedGetListOfUser(RetrofitUtil.getErrorMessage(response.errorBody()))
                    }
                })
        compositeDisposable.add(disposable)
    }

}