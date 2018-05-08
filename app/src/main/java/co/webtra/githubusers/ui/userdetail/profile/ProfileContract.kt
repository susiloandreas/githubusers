package co.webtra.githubusers.ui.userdetail.profile

import co.webtra.githubusers.base.BasePresenter
import co.webtra.githubusers.model.User

/**
 * Created by andreassusilo on 07/05/18.
 */
interface ProfileContract {

    interface View {
        fun onSuccessGetProfile(user: User)

        fun onFailedGetProfile()

        fun getUsrname(): String
    }

    interface Presenter : BasePresenter {
        fun getProfile(username: String)
    }

}
