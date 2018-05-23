package io.cryptocontrol.cryptonewsapi;

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
    Long hotness;
    Long activityHotness;
    String primaryCategory;
    Integer words;
    String description;
    String title;
    String url;
    String thumbnail;

    List<Coin> coins = new ArrayList<Coin>();
    List<SimilarArticle> similarArticles = new ArrayList<SimilarArticle>();

    public static class SimilarArticle {
        String _id;
        String publishedAt;
        String title;
        String url;
    }

    public static class Coin {
        String _id;
        String name;
        String slug;
        String tradingSymbol;
    }
}
