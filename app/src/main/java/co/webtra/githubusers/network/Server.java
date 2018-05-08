package co.webtra.githubusers.network;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import co.webtra.githubusers.data.constant.ApiRoute;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ferdi.ninaber@gmail.com on 7/4/17.
 */

public class Server {
    private static Server INSTANCE;
    private Service service;
    private Retrofit retrofit;
    private OkHttpClient.Builder okHttpBuilder;

    public static Server getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new Server();
        }
        return INSTANCE;
    }

    private Server() {
        createRest();
    }

    private void createRest() {
        Calendar.getInstance().getTimeInMillis();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.addInterceptor(logging);
        okHttpBuilder.readTimeout(1, TimeUnit.MINUTES);
        okHttpBuilder.writeTimeout(1, TimeUnit.MINUTES);
        retrofit = new Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).client(okHttpBuilder.build()).baseUrl(ApiRoute.Companion.getBASE_URL()).build();
        service = retrofit.create(Service.class);
    }


    public Retrofit retrofit() {
        return retrofit;
    }


    public Service service() {
        if (null == service) {
            throw new IllegalStateException("Haven't create rest service first");
        }
        return service;
    }
}
