package io.cryptocontrol.cryptonewsapi.models;

import java.util.List;

/**
 * Represents rich metadata about a given coin
 *
 * @author enamakel@cryptocontrol.io
 */
public class CoinDetail {
    private List<Link> links;
    private String description;

    private List<String> subreddits;
    private List<String> twitterUsernames;
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


    private class Link {
        String type;
        String name;
        String link;


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
