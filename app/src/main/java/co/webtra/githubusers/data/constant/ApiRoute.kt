package co.webtra.githubusers.data.constant

/**
 * Created by andreassusilo on 04/05/18.
 */
class ApiRoute {
    companion object {
        val BASE_URL = "https://api.github.com/"
        const val PATH_USERS = "users"
        const val PATH_SEARCH_USER = "search/users"
        const val PATH_PAGE = "page"
        const val QS_PAGE = "page"
        const val QS_QUERY = "q"
    }
}