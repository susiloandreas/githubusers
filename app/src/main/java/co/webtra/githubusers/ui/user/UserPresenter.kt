package co.webtra.githubusers.ui.user

import android.util.Log
import co.webtra.githubusers.model.User
import co.webtra.githubusers.network.Service
import co.webtra.githubusers.ui.userdetail.UserDetailActivity
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by andreassusilo on 07/05/18.
 */

class UserPresenter @Inject constructor(val view: UserContract.View, val service: Service) : UserContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun subscribeUserOnClick() {
        val disposable =  view.getUserObservable().subscribe {
            user -> view.moveToUserDetailActivity(user as User)
        }
        compositeDisposable.add(disposable)
    }

    override fun getListOfUser() {
        val disposable = service.getListOfUser()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribe({ response ->
                    if (response.isSuccessful()) {
                        Log.e("UserPresenter", Gson().toJson(response.body()!!))
                        view.onSuccessGetListOfUser(response.body()!!)
                    } else {
                        //view.onFailedGetListOfUser(RetrofitUtil.getErrorMessage(response.errorBody()))
                    }
                })
        compositeDisposable.add(disposable)
    }


    override fun bind() {
        getListOfUser()
        subscribeUserOnClick()
    }

    override fun unBind() {
        compositeDisposable.clear()
    }

}