package com.rakaadinugroho.presensirxjava;

/**
 * Created by Raka Adi Nugroho on 13/02/2017.
 */
public class GithubRepo {
    public int id;
    public String name;
    public String htmlUrl;
    public String description;
    public String language;
    public int stargazersCount;

    public GithubRepo(int id, String name, String htmlUrl, String description, String language, int stargazersCount) {
        this.id = id;
        this.name = name;
        this.htmlUrl = htmlUrl;
        this.description = description;
        this.language = language;
        this.stargazersCount = stargazersCount;
    }
}
