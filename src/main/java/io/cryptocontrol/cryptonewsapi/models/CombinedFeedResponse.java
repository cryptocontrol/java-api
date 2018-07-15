package io.cryptocontrol.cryptonewsapi.models;

import java.util.ArrayList;
import java.util.List;

/**
 * @author enamakel@cryptocontrol.io
 */
public class CombinedFeedResponse {
    private List<Article> articles = new ArrayList<>();
    private List<RedditPost> redditPosts = new ArrayList<>();
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
