package com.rakaadinugroho.presensirxjava;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raka Adi Nugroho on 14/02/2017.
 */
public class GithubRepoAdapter extends BaseAdapter {
    private List<GithubRepo> githubRepos    = new ArrayList<>();
    @Override
    public int getCount() {
        return githubRepos.size();
    }

    @Override
    public Object getItem(int position) {
        if (position<0 || position>=githubRepos.size())
            return null;
        else
            return githubRepos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View view = (convertView != null? convertView:createView(parent));
        final GithubRepoViewHolder viewHolder   = (GithubRepoViewHolder) view.getTag();
        viewHolder.setGithubRepo((GithubRepo) getItem(position));
        return null;
    }

    private View createView(ViewGroup parent) {
        final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View view = layoutInflater.inflate(R.layout.item_github, parent, false);
        final GithubRepoViewHolder viewHolder   = new GithubRepoViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }
    public void setGithubRepos(List<GithubRepo> repos){
        if (repos == null)
            return;
        githubRepos.clear();
        githubRepos.addAll(repos);
        notifyDataSetChanged();
    }
    private static class GithubRepoViewHolder{
        private TextView text_reponame, text_repodesc, text_language, text_stars;
        public GithubRepoViewHolder(View view){
            text_reponame   = (TextView) view.findViewById(R.id.text_reponame);
            text_repodesc   = (TextView) view.findViewById(R.id.text_repodesc);
            text_language   = (TextView) view.findViewById(R.id.text_language);
            text_stars      = (TextView) view.findViewById(R.id.text_stars);
        }
        public void setGithubRepo(GithubRepo githubRepo){
            text_reponame.setText(githubRepo.name);
            text_repodesc.setText(githubRepo.description);
            text_language.setText(githubRepo.language);
            text_stars.setText(githubRepo.stargazersCount);
        }

    }
}
