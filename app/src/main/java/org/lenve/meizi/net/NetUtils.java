package org.lenve.meizi.net;

import org.lenve.meizi.util.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 王松 on 2016/9/13.
 */
public class NetUtils {
    private static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS).build();
    private static Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.HOST)
            .client(getOkHttpClient())
            .build();

    public static OkHttpClient getOkHttpClient() {
        return client;
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }
    public static ApiService getApiService() {
        return retrofit.create(ApiService.class);
    }
}
