package io.cryptocontrol.cryptonewsapi.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * @author enamakel@cryptocontrol.io
 */
public class CombinedFeedResponse {
    @SerializedName("articles")
    private List<Article> articles = new ArrayList<>();

    @SerializedName("redditPosts")
    private List<RedditPost> redditPosts = new ArrayList<>();

    @SerializedName("tweets")
    private List<Tweet> tweets = new ArrayList<>();


    public List<Article> getArticles() {
        return articles;
    }


    public List<RedditPost> getRedditPosts() {
        return redditPosts;
    }


    public List<Tweet> getTweets() {
        return tweets;
    }
}
