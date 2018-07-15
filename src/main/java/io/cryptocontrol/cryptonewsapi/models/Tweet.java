package io.cryptocontrol.cryptonewsapi.models;

import java.util.ArrayList;
import java.util.List;

/**
 * @author enamakel@eshe.io
 */
public class Tweet {
    private String text;
    private String username;
    private String id;
    private String publishedAt;
    private String url;
    private Boolean isRetweeted;
    private Number retweetCount;
    private Number favoriteCount;

    private List<String> links;
    private List<String> hashtags;
    private List<String> mentions;
    private List<String> symbols;


    public String getUrl() {
        return url;
    }


    public List<String> getSymbols() {
        return symbols;
    }


    public void setSymbols(List<String> symbols) {
        this.symbols = symbols;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getPublishedAt() {
        return publishedAt;
    }


    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }


    public Boolean getRetweeted() {
        return isRetweeted;
    }


    public void setRetweeted(Boolean retweeted) {
        isRetweeted = retweeted;
    }


    public Number getRetweetCount() {
        return retweetCount;
    }


    public void setRetweetCount(Number retweetCount) {
        this.retweetCount = retweetCount;
    }


    public Number getFavoriteCount() {
        return favoriteCount;
    }


    public void setFavoriteCount(Number favoriteCount) {
        this.favoriteCount = favoriteCount;
    }


    public List<String> getLinks() {
        return links;
    }


    public void setLinks(List<String> list) {
        this.links = list;
    }


    public List<String> getHashtags() {
        return hashtags;
    }


    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }


    public List<String> getMentions() {
        return mentions;
    }


    public void setMentions(List<String> mentions) {
        this.mentions = mentions;
    }


    public String getText() {
        return text;
    }


    public void setText(String text) {
        this.text = text;
    }


    public static class TweetList extends ArrayList<Tweet> {
    }
}
