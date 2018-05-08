package co.webtra.githubusers.data.sharedpref

import android.content.SharedPreferences

/**
 * Created by andreassusilo on 07/05/18.
 */
class RecentSearchManager(private val sharedPreferences: SharedPreferences) {

    private val recentSearch: String = "recent_search"

    fun putRecentSearches(recentSearches: Set<String>?) {
        sharedPreferences.edit().putStringSet(recentSearch, recentSearches).apply()
    }

    fun getRecentSearches(): Set<String>? {
        return sharedPreferences.getStringSet(recentSearch  , setOf<String>())
    }


}