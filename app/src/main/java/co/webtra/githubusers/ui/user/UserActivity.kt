package co.webtra.githubusers.ui.user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import co.webtra.githubusers.R
import co.webtra.githubusers.ui.search.SearchActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_user.*
import javax.inject.Inject

class UserActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }

    val TAG: String = UserActivity::class.java.name

    companion object {
        val TAG: String = UserActivity::class.java.name
        fun start(context: Context?) {
            val intent = Intent(context, UserActivity::class.java)
            context?.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        user_search_button.setOnClickListener {
            SearchActivity.start(this@UserActivity)
        }
        supportFragmentManager.beginTransaction().add(R.id.user_container, UserFragment.newInstance(), TAG).commit()
    }
}
