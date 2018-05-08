package co.webtra.githubusers.ui.userdetail.repo

import co.webtra.githubusers.base.BasePresenter
import co.webtra.githubusers.model.Repo

/**
 * Created by andreassusilo on 07/05/18.
 */
interface RepoContract {

    interface View {
        fun onSuccessGetRepo(repoList: ArrayList<Repo>)

        fun onFailedGetRepo()

        fun getUsrname(): String
    }

    interface Presenter : BasePresenter {
        fun getRepo(username: String, page: Int)
    }

}
