package co.webtra.githubusers.ui.search

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import co.webtra.githubusers.R
import co.webtra.githubusers.data.sharedpref.RecentSearchManager
import co.webtra.githubusers.model.User
import co.webtra.githubusers.model.UserList
import co.webtra.githubusers.ui.userdetail.UserDetailActivity
import com.jakewharton.rxbinding2.widget.RxTextView
import dagger.android.support.AndroidSupportInjection
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_search.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by andreassusilo on 07/05/18.
 */
class SearchFragment : Fragment(), SearchContract.View {

    lateinit var searchAdapter: SearchAdapter

    @Inject
    lateinit var searchPresenter: SearchContract.Presenter

    var searchList: ArrayList<Any> = ArrayList()

    companion object {
        val TAG: String = SearchFragment::class.java.name

        fun newInstance(): SearchFragment {
            val searchFragment = SearchFragment()
            return searchFragment
        }
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_search, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        search_recyclerview.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        searchAdapter = SearchAdapter()
        search_recyclerview.adapter = searchAdapter
        search_close.setOnClickListener {
            search_username_edittext.setText(null)
            searchPresenter.loadRecentSearches()
            search_title.visibility = View.VISIBLE
        }
        search_back.setOnClickListener{
            activity?.finish()
        }
        searchPresenter.bind()
    }

    override fun onDestroy() {
        super.onDestroy()
        searchPresenter.unBind()
    }

    override fun getSearchObservable(): Observable<String> {
        return RxTextView.textChanges(search_username_edittext)
                .doOnNext {
                    search_close.visibility = View.GONE
                }
                .filter { t -> t.length >= 1 }
                .doOnNext {
                    search_close.visibility = View.VISIBLE
                    search_progressbar.visibility = View.VISIBLE
                }
                .debounce(1, TimeUnit.SECONDS)
                .map { t: CharSequence -> t.toString() }
                .distinctUntilChanged()

    }

    override fun onSuccessSearchUser(userList: UserList) {
        searchList.clear()
        searchList.addAll(userList.items)
        searchAdapter.setData(searchList)
        search_title.visibility = View.GONE
        search_progressbar.visibility = View.GONE

    }

    override fun moveToUserDetailActivity(login: String) {
        activity?.finish()
        UserDetailActivity.start(context, login)

    }

    override fun getUserLoginObservable(): Observable<String> {
        return searchAdapter.onClickUserSubject
    }

    override fun onFailedSearchUser() {

    }

    override fun setRecentSearches(recentSearches: Set<String>?) {
        recentSearches?.let {
            searchAdapter.setData(ArrayList(recentSearches))
        }

    }
}