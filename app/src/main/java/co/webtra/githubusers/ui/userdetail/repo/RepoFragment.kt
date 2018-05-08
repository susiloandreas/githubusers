package co.webtra.githubusers.ui.userdetail.repo

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
import co.webtra.githubusers.model.Repo
import co.webtra.githubusers.ui.userdetail.UserDetailActivity
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_repo.*
import javax.inject.Inject

/**
 * Created by andreassusilo on 07/05/18.
 */
class RepoFragment : Fragment(), RepoContract.View {

    @Inject
    lateinit var repoPresenter: RepoContract.Presenter

    lateinit var username: String

    lateinit var repoAdapter: RepoAdapter


    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    companion object {
        val TAG: String = RepoFragment::class.java.name
        fun newInstance(username: String): RepoFragment {
            val bundle = Bundle()
            bundle.putString(UserDetailActivity.EXTRA_USERNAME, username)
            val fragment = RepoFragment()
            fragment.arguments = bundle
            return fragment

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        repoPresenter.unBind()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
            username = it.getString(UserDetailActivity.EXTRA_USERNAME)
        }

        val itemDecorator =  DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        val drawable = ContextCompat.getDrawable(context!!, R.drawable.divider)
        itemDecorator.setDrawable(drawable!!)
        repo_recyclerview.addItemDecoration(itemDecorator)

        repo_recyclerview.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        repoAdapter = RepoAdapter()
        repo_recyclerview.adapter = repoAdapter
        repoPresenter.bind()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_repo, container, false)
    }

    override fun onSuccessGetRepo(repoList: ArrayList<Repo>) {
        repoAdapter.setData(repoList)
    }

    override fun onFailedGetRepo() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUsrname(): String {
        return username
    }
}