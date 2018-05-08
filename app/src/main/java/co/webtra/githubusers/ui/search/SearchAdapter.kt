package co.webtra.githubusers.ui.search

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.webtra.githubusers.R
import co.webtra.githubusers.model.User
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_search.view.*
import java.util.concurrent.TimeUnit

/**
 * Created by andreassusilo on 07/05/18.
 */
class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    var userList: ArrayList<Any> = ArrayList()

    val onClickUserSubject = PublishSubject.create<String>()

    val TYPE_RECENT_SEARCH = 0

    val TYPE_USER = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        RxView.clicks(view)
                .debounce(1, TimeUnit.SECONDS)
                .map { aVoid -> view.getTag().toString() }
                .subscribe(onClickUserSubject)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder?.getItemViewType()) {
            TYPE_USER -> {
                holder.bindItems((userList!![position] as User).login )
                holder?.itemView?.setTag((userList[position] as User).login)
            }

            TYPE_RECENT_SEARCH -> {
                holder.bindItems(userList!![position].toString() )
                holder?.itemView?.setTag(userList[position].toString())

            }

        }

    }

    fun setData(userList: ArrayList<Any>) {
        this.userList = userList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return userList!!.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(userlogin: String) {
            itemView.search_recent_search.text = userlogin

        }
    }

    override fun getItemViewType(position: Int): Int {
        val o = userList.get(position)
        if(o is String) {
            return TYPE_RECENT_SEARCH
        } else {
            return TYPE_USER

        }
    }


}