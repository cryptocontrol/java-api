package io.cryptocontrol.cryptonewsapi.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an article coming from the CryptoControl.io API
 *
 * @author enamakel@cryptocontrol.io
 */
public class Article {
    String _id;
    String publishedAt;
    Double hotness;
    Double activityHotness;
    String primaryCategory;
    Integer words;
    String description;
    String title;
    String url;
    String originalImageUrl;

    String thumbnail;
    List<Coin> coins = new ArrayList<Coin>();
    List<SimilarArticle> similarArticles = new ArrayList<SimilarArticle>();


    public String getId() {
        return _id;
    }


    public String getPublishedAt() {
        return publishedAt;
    }


    public Double getHotness() {
        return hotness;
    }


    public Double getActivityHotness() {
        return activityHotness;
    }


    public String getPrimaryCategory() {
        return primaryCategory;
    }


    public Integer getWords() {
        return words;
    }


    public String getDescription() {
        return description;
    }


    public String getTitle() {
        return title;
    }


    public String getUrl() {
        return url;
    }


    public String getThumbnail() {
        return thumbnail;
    }


    public String getOriginalImageUrl() {
        return originalImageUrl;
    }


    public List<Coin> getCoins() {
        return coins;
    }


    public List<SimilarArticle> getSimilarArticles() {
        return similarArticles;
    }


    public static class SimilarArticle {
        String _id;
        String publishedAt;
        String title;


        public String getId() {
            return _id;
        }


        public String getPublishedAt() {
            return publishedAt;
        }


        public String getTitle() {
            return title;
        }


        public String getUrl() {
            return url;
        }


        String url;
    }


    public static class Coin {
        String _id;
        String name;
        String slug;
        String tradingSymbol;


        public String getId() {
            return _id;
        }


        public String getName() {
            return name;
        }


        public String getSlug() {
            return slug;
        }


        public String getTradingSymbol() {
            return tradingSymbol;
        }
    }


    public static class ArticleList extends ArrayList<Article> {
    }
}
