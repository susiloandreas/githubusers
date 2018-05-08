package co.webtra.githubusers.ui.userdetail.profile

import co.webtra.githubusers.network.Service
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by andreassusilo on 07/05/18.
 */
class ProfilePresenter @Inject constructor(val view: ProfileContract.View, val service: Service) : ProfileContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun bind() {
        getProfile(view.getUsrname())
    }

    override fun unBind() {
        compositeDisposable.clear()
    }

    override fun getProfile(username: String) {
        val disposable = service.getUser(username)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribe({ response ->
                    if (response.isSuccessful()) {
                        view.onSuccessGetProfile(response.body()!!)
                    } else {
                        //view.onFailedGetListOfUser(RetrofitUtil.getErrorMessage(response.errorBody()))
                    }
                })
        compositeDisposable.add(disposable)
    }

}
