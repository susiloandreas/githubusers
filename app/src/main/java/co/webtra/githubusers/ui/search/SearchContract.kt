package co.webtra.githubusers.ui.search

import co.webtra.githubusers.base.BasePresenter
import co.webtra.githubusers.model.User
import co.webtra.githubusers.model.UserList
import io.reactivex.Observable

/**
 * Created by andreassusilo on 07/05/18.
 */
interface SearchContract {

    interface View {
        fun getSearchObservable(): Observable<String>

        fun onSuccessSearchUser(userList: UserList)

        fun onFailedSearchUser()

        fun setRecentSearches(recentSearches: Set<String>?)

        fun moveToUserDetailActivity(login: String)

        fun getUserLoginObservable(): Observable<String>
    }

    interface Presenter : BasePresenter {
        fun searchUser(username: String, page: Int)

        fun subscribeSearchOnTextChanges()

        fun subscribeUserOnClick()

        fun loadRecentSearches()
    }

}
