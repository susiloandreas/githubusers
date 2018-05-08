package co.webtra.githubusers.ui.user

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import co.webtra.githubusers.R
import co.webtra.githubusers.model.User
import co.webtra.githubusers.ui.userdetail.UserDetailActivity
import dagger.android.support.AndroidSupportInjection
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_user.*
import javax.inject.Inject

/**
 * Created by andreassusilo on 07/05/18.
 */

class UserFragment : Fragment(), UserContract.View{

    lateinit var userAdapter: UserAdapter

    @Inject
    lateinit var userPresenter: UserContract.Presenter

    var userList: ArrayList<Any> = ArrayList()

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_user, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        userPresenter.unBind()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val itemDecorator =  DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        val drawable = ContextCompat.getDrawable(context!!, R.drawable.divider)
        itemDecorator.setDrawable(drawable!!)
        user_recyclerview.addItemDecoration(itemDecorator)

        user_recyclerview.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        userAdapter = UserAdapter()
        user_recyclerview.adapter = userAdapter
        userPresenter.bind()

        (0..10).forEach { i -> userList.add("dummy")}
        userAdapter.setData(userList)

    }

    override fun onSuccessGetListOfUser(_userList: ArrayList<User>) {
        userList.clear()
        userList.addAll(_userList)
        userAdapter.setData(userList)

    }

    override fun onFailedGetListOfUser(message: String?) {

    }

    override fun getUserObservable(): Observable<Any> {
        return userAdapter.onClickUserSubject
    }

    override fun moveToUserDetailActivity(user: User) {
        UserDetailActivity.start(context, user.login)
    }

    companion object {
        val TAG: String = UserFragment::class.java.name

        fun newInstance(): UserFragment {
            val userFragment = UserFragment()

            return userFragment
        }
    }



}