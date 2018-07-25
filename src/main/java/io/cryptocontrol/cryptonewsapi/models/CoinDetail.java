package io.cryptocontrol.cryptonewsapi.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Represents rich metadata about a given coin
 *
 * @author enamakel@cryptocontrol.io
 */
public class CoinDetail {
    @SerializedName("links")
    private List<Link> links;

    @SerializedName("description")
    private String description;

    @SerializedName("subreddits")
    private List<String> subreddits;

    @SerializedName("twitterUsernames")
    private List<String> twitterUsernames;

    @SerializedName("gitrepos")
    private List<String> gitrepos;


    public List<Link> getLinks() {
        return links;
    }


    public String getDescription() {
        return description;
    }


    public List<String> getSubreddits() {
        return subreddits;
    }


    public List<String> getTwitterUsernames() {
        return twitterUsernames;
    }


    public List<String> getGitrepos() {
        return gitrepos;
    }


    public static class Link {
        @SerializedName("type")
        private String type;

        @SerializedName("name")
        private String name;

        @SerializedName("link")
        private String link;


        public String getType() {
            return type;
        }


        public String getName() {
            return name;
        }


        public String getLink() {
            return link;
        }

    }
}
