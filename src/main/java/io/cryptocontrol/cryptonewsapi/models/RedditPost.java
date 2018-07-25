package io.cryptocontrol.cryptonewsapi.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * @author enamakel@eshe.io
 */
public class RedditPost {
    @SerializedName("isSelf")
    private Boolean isSelf;

    @SerializedName("score")
    private Double score;

    @SerializedName("comments")
    private Long comments;

    @SerializedName("upvotes")
    private Long upvotes;

    @SerializedName("downvotes")
    private Number downvotes;

    @SerializedName("description")
    private String description;

    @SerializedName("id")
    private String id;

    @SerializedName("publishedAt")
    private String publishedAt;

    @SerializedName("subreddit")
    private String subreddit;

    @SerializedName("title")
    private String title;

    @SerializedName("url")
    private String url;


    public Boolean getSelf() {
        return isSelf;
    }


    public void setSelf(Boolean self) {
        isSelf = self;
    }


    public Double getScore() {
        return score;
    }


    public void setScore(Double score) {
        this.score = score;
    }


    public Long getComments() {
        return comments;
    }


    public void setComments(Long comments) {
        this.comments = comments;
    }


    public Long getUpvotes() {
        return upvotes;
    }


    public void setUpvotes(Long upvotes) {
        this.upvotes = upvotes;
    }


    public Number getDownvotes() {
        return downvotes;
    }


    public void setDownvotes(Number downvotes) {
        this.downvotes = downvotes;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
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


    public String getSubreddit() {
        return subreddit;
    }


    public void setSubreddit(String subreddit) {
        this.subreddit = subreddit;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
    }


    public static class RedditPostList extends ArrayList<RedditPost> {
    }
}
