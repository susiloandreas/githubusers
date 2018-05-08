package co.webtra.githubusers.ui.user

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.webtra.githubusers.R
import co.webtra.githubusers.model.User
import com.bumptech.glide.Glide
import com.bumptech.glide.TransitionOptions
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_user.view.*
import java.util.concurrent.TimeUnit

/**
 * Created by andreassusilo on 07/05/18.
 */

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    val onClickUserSubject = PublishSubject.create<Any>()

    var userList: ArrayList<Any> = ArrayList()

    val TYPE_SHIMMER = 0

    val TYPE_USER = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        lateinit var view: View
        when(viewType) {
            TYPE_SHIMMER -> view = LayoutInflater.from(parent.context).inflate(R.layout.item_user_shimmer, parent, false)
            TYPE_USER -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
                RxView.clicks(view)
                        .debounce(1, TimeUnit.SECONDS)
                        .map { aVoid -> view.getTag() }
                        .subscribe(onClickUserSubject)
            }
        }

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder?.getItemViewType()) {
            TYPE_USER -> {
                holder.bindItems(userList!![position] as User)
                holder?.itemView?.setTag(userList[position] as User)
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

    override fun getItemViewType(position: Int): Int {
        val o = userList.get(position)
        if(o is String) {
            return TYPE_SHIMMER
        } else {
            return TYPE_USER

        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(user: User) {
            itemView.user_username.text = user.login
            Glide.with(itemView.context).load(user.avatar_url)
                    .thumbnail(Glide.with(itemView.context).load(R.drawable.circle))
                    .into(itemView.user_image)
        }
    }


}