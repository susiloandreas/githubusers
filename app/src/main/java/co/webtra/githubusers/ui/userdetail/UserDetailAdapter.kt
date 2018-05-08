package co.webtra.githubusers.ui.userdetail

import android.arch.lifecycle.ReportFragment
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import co.webtra.githubusers.R
import co.webtra.githubusers.ui.userdetail.profile.ProfileFragment
import co.webtra.githubusers.ui.userdetail.repo.RepoFragment

/**
 * Created by andreassusilo on 07/05/18.
 */
class UserDetailAdapter(fm: FragmentManager, val context: Context,val username: String) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? = when (position) {
        0 -> ProfileFragment.newInstance(username)
        1 -> RepoFragment.newInstance(username)
        else -> null
    }

    override fun getPageTitle(position: Int): CharSequence = when (position) {
        0 -> context.getString(R.string.profile)
        1 -> context.getString(R.string.repo)
        else -> ""
    }

    override fun getCount(): Int = 2
}