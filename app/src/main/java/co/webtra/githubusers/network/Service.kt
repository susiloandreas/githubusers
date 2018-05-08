package co.webtra.githubusers.network

import co.webtra.githubusers.data.constant.ApiRoute
import co.webtra.githubusers.model.Repo
import co.webtra.githubusers.model.User
import co.webtra.githubusers.model.UserList
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by andreassusilo on 04/05/18.
 */

interface Service {


    @GET(ApiRoute.PATH_USERS)
    fun getListOfUser(): Observable<Response<ArrayList<User>>>


    @GET(ApiRoute.PATH_SEARCH_USER)
    fun searchUser(@Query(ApiRoute.QS_QUERY) query: String,
                   @Query(ApiRoute.QS_PAGE) page: Int): Observable<Response<UserList>>

    @GET("/users/{username}")
    fun getUser(@Path("username") username: String): Observable<Response<User>>

    @GET("/users/{username}/repos")
    fun getRepo(@Path("username") username: String): Observable<Response<ArrayList<Repo>>>


}

