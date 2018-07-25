package io.cryptocontrol.cryptonewsapi.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an article coming from the CryptoControl.io API
 *
 * @author enamakel@cryptocontrol.io
 * @author PrograMonks
 */
public class Article {
    @SerializedName("activityHotness")
    private Double activityHotness;

    @SerializedName("hotness")
    private Double hotness;

    @SerializedName("words")
    private Integer words;

    @SerializedName("_id")
    private String _id;

    @SerializedName("description")
    private String description;

    @SerializedName("originalImageUrl")
    private String originalImageUrl;

    @SerializedName("primaryCategory")
    private String primaryCategory;

    @SerializedName("publishedAt")
    private String publishedAt;

    @SerializedName("sourceDomain")
    private String sourceDomain;

    @SerializedName("thumbnail")
    private String thumbnail;

    @SerializedName("title")
    private String title;

    @SerializedName("url")
    private String url;

    @SerializedName("source")
    private Source source;

    @SerializedName("coins")
    private List<Coin> coins = new ArrayList<>();

    @SerializedName("similarArticles")
    private List<SimilarArticle> similarArticles = new ArrayList<>();


    public String getId() {
        return _id;
    }


    public String getSourceDomain() {
        return sourceDomain;
    }


    public Source getSource() {
        return source;
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
        @SerializedName("_id")
        private String _id;

        @SerializedName("publishedAt")
        private String publishedAt;

        @SerializedName("title")
        private String title;

        @SerializedName("url")
        private String url;

        @SerializedName("sourceDomain")
        private String sourceDomain;

        @SerializedName("source")
        private Source source;


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


        public String getSourceDomain() {
            return sourceDomain;
        }


        public Source getSource() {
            return source;
        }
    }


    public static class Coin {
        @SerializedName("_id")
        private String _id;

        @SerializedName("name")
        private String name;

        @SerializedName("slug")
        private String slug;

        @SerializedName("tradingSymbol")
        private String tradingSymbol;


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


    public static class Source {
        @SerializedName("_id")
        private String _id;

        @SerializedName("name")
        private String name;

        @SerializedName("url")
        private String url;


        public String getId() {
            return _id;
        }


        public String getName() {
            return name;
        }


        public String getUrl() {
            return url;
        }
    }


    public static class ArticleList extends ArrayList<Article> {
    }
}
