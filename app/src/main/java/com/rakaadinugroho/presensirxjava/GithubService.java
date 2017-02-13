package com.rakaadinugroho.presensirxjava;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Raka Adi Nugroho on 13/02/2017.
 */
public interface GithubService {
    @GET("user/{user}/starred")
    Observable<List<GithubRepo>> getStarredRepositories(
            @Path("user") String username
    );
}
