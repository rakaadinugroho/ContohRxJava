package com.rakaadinugroho.presensirxjava;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by Raka Adi Nugroho on 13/02/2017.
 */
public class GithubClient {
    private static final String BASE_URL    = "https://api.github.com/";
    private static GithubClient instance;
    private GithubService githubService;

    public GithubClient() {
        final Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        githubService   = retrofit.create(GithubService.class);
    }

    public static GithubClient getInstance() {
        if (instance == null)
            instance    = new GithubClient();
        return instance;
    }
    public Observable<List<GithubRepo>> getStarredRepos(String username){
        return githubService.getStarredRepositories(username);
    }
}
