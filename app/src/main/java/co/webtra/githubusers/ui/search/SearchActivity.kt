package co.webtra.githubusers.ui.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import co.webtra.githubusers.R
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Created by andreassusilo on 07/05/18.
 */
class SearchActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }

    val TAG: String = SearchActivity::class.java.name

    companion object {
        val TAG: String = SearchActivity::class.java.name
        fun start(context: Context?) {
            val intent = Intent(context, SearchActivity::class.java)
            context?.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        supportFragmentManager.beginTransaction().add(R.id.search_container, SearchFragment.newInstance(), TAG).commit()
    }
}
