package com.rakaadinugroho.presensirxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private GithubRepoAdapter adapter   = new GithubRepoAdapter();
    private Subscription subscription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listView = (ListView) findViewById(R.id.listview_github);
        listView.setAdapter(adapter);
        final Button search = (Button) findViewById(R.id.button_search);
        final EditText username = (EditText) findViewById(R.id.edittext_username);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String params = username.getText().toString();
                if (!TextUtils.isEmpty(params)){
                    getStarredRepos(params);
                }
            }
        });
    }

    private void getStarredRepos(String params) {
        subscription    = GithubClient.getInstance()
                .getStarredRepos(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<GithubRepo>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                    }

                    @Override
                    public void onNext(List<GithubRepo> githubRepos) {
                        Log.d(TAG, "onNext: ");
                        adapter.setGithubRepos(githubRepos);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
        super.onDestroy();
    }
}
