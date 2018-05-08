package co.webtra.githubusers.ui.userdetail.profile

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.webtra.githubusers.R
import co.webtra.githubusers.model.User
import co.webtra.githubusers.ui.userdetail.UserDetailActivity
import com.bumptech.glide.Glide
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.item_user.view.*
import javax.inject.Inject

/**
 * Created by andreassusilo on 07/05/18.
 */

class ProfileFragment : Fragment(), ProfileContract.View {

    @Inject
    lateinit var profilePresenter: ProfileContract.Presenter

    lateinit var username: String

    companion object {
        val TAG: String = ProfileFragment::class.java.name
        fun newInstance(username: String): ProfileFragment {
            val bundle = Bundle()
            bundle.putString(UserDetailActivity.EXTRA_USERNAME, username)
            val fragment = ProfileFragment()
            fragment.arguments = bundle
            return fragment

        }
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            username = it.getString(UserDetailActivity.EXTRA_USERNAME)
        }

        profilePresenter.bind()

    }


    override fun onDestroy() {
        super.onDestroy()
        profilePresenter.unBind()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_profile, container, false)
    }

    override fun getUsrname(): String {
        return username
    }

    override fun onSuccessGetProfile(user: User) {
        fullname_content.text = user.name
        company_content.text = user.company
        blog_content.text = user.blog
        location_content.text = user.location
        followerscontent.text = user.followers.toString()
        followingcontent.text = user.following.toString()
        private_reposcontent.text = user.total_private_repos.toString()
        public_reposcontent.text = user.public_repos.toString()
        Glide.with(context).load(user.avatar_url)
                .thumbnail(Glide.with(context).load(R.drawable.circle))
                .into(profile_picture)

    }

    override fun onFailedGetProfile() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}