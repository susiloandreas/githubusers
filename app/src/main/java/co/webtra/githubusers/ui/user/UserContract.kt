package co.webtra.githubusers.ui.user

import co.webtra.githubusers.base.BasePresenter
import co.webtra.githubusers.model.User
import io.reactivex.Observable

/**
 * Created by andreassusilo on 07/05/18.
 */


interface UserContract {

    interface View {
        fun onSuccessGetListOfUser(userList: ArrayList<User>)

        fun onFailedGetListOfUser(message: String?)

        fun getUserObservable(): Observable<Any>

        fun moveToUserDetailActivity(user: User)
    }

    interface Presenter : BasePresenter {
        fun getListOfUser()

        fun subscribeUserOnClick()
    }

}
