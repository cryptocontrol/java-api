package io.cryptocontrol.cryptonewsapi.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * @author enamakel@eshe.io
 */
public class Feed {
    @SerializedName("article")
    private Article article;

    @SerializedName("reddit")
    private RedditPost reddit;

    @SerializedName("type")
    private String type;

    @SerializedName("tweet")
    private Tweet tweet;


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public RedditPost getReddit() {
        return reddit;
    }


    public void setReddit(RedditPost reddit) {
        this.reddit = reddit;
    }


    public Tweet getTweet() {
        return tweet;
    }


    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }


    public Article getArticle() {
        return article;
    }


    public void setArticle(Article article) {
        this.article = article;
    }


    public static class FeedList extends ArrayList<Feed> {
    }
}
