package co.webtra.githubusers.ui.userdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import co.webtra.githubusers.R
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_userdetail.*
import javax.inject.Inject

/**
 * Created by andreassusilo on 07/05/18.
 */
class UserDetailActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }

    companion object {
        val EXTRA_USERNAME: String = "EXTRA_USERNAME"
        fun start(context: Context?, username: String) {
            val intent = Intent(context, UserDetailActivity::class.java)
            intent.putExtra(EXTRA_USERNAME, username)
            context?.startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userdetail)
        val username = intent.getStringExtra(EXTRA_USERNAME)
        setSupportActionBar(userdetail_toolbar)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setTitle(username)
        val adapter = UserDetailAdapter(supportFragmentManager, this@UserDetailActivity,  intent.getStringExtra(EXTRA_USERNAME))
        userdetail_viewpager.adapter = adapter
        userdetail_tablayout.setupWithViewPager(userdetail_viewpager)
        userdetail_tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }
}