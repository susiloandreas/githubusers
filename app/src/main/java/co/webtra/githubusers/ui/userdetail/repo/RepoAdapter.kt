package co.webtra.githubusers.ui.userdetail.repo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.webtra.githubusers.R
import co.webtra.githubusers.model.Repo
import co.webtra.githubusers.model.User
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_repo.view.*

/**
 * Created by andreassusilo on 08/05/18.
 */
class RepoAdapter : RecyclerView.Adapter<RepoAdapter.ViewHolder>() {

    var repoList: ArrayList<Repo> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(repoList!![position])
        holder?.itemView?.setTag(repoList[position])
    }

    fun setData(repoList: ArrayList<Repo>) {
        this.repoList = repoList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return repoList!!.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(repo: Repo) {
            itemView.repo_name.text = repo.name
            itemView.repo_description.text = repo.description
            itemView.repo_updated.text = repo.updated_at
            itemView.repo_stargazers.text = repo.stargazers_count.toString()
            itemView.repo_watchers.text = repo.watchers_count.toString()


        }
    }


}