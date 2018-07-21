package io.cryptocontrol.cryptonewsapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.cryptocontrol.cryptonewsapi.exceptions.BadResponseException;
import io.cryptocontrol.cryptonewsapi.exceptions.InvalidAPIKeyException;
import io.cryptocontrol.cryptonewsapi.models.*;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


/**
 * Interface to the CryptoControl API.
 *
 * @author enamakel@cryptocontrol.io
 */
public class CryptoControlApi {
    private final String apiKey;
    private String proxyURL = null;
    private final Gson gson = new GsonBuilder().create();


    /**
     * A simple contructor which sets a API key
     *
     * @param apiKey The CryptoControl.io API key
     */
    public CryptoControlApi(final String apiKey) {
        if (apiKey == null)
            throw new Error("No API key found. Register for an API key at https://cryptocontrol.io/apis");

        this.apiKey = apiKey;
    }


    /**
     * A contructor which allows a proxy URL to be set. This would be useful if you'd like to setup a proxy URL
     *
     * @param apiKey   The CryptoControl.io API key
     * @param proxyURL The URL to proxy to
     */
    public CryptoControlApi(final String apiKey, final String proxyURL) {
        this(apiKey);
        this.proxyURL = proxyURL;
    }


    /**
     * Helper function to make a request to the CryptoControl server.
     *
     * @param path     The URL to call
     * @param lang     The language to choose from
     * @param callback A callback fn returning the response from the CryptoControl API.
     * @param TypeofT  The type of the object that needs to be returned.
     */
    private void fetch(String path, Language lang, OnResponseHandler callback, Type TypeofT) {
        String jsonStr = "";
        HttpURLConnection urlConnection = null;

        String langSlug;
        switch (lang) {
            default:
            case ENGLISH:
                langSlug = "en";
                break;
            case RUSSIAN:
                langSlug = "ru";
                break;
        }


        try {
            String HOST = proxyURL != null ? proxyURL : "https://cryptocontrol.io/api/v1/public";
            URL url = new URL(HOST + path + (path.contains("?") ? "&" : "?") + "language=" + langSlug);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Content-Type", "application/json");

            // set the api key
            urlConnection.setRequestProperty("x-api-key", apiKey);

            // Set the user agent
            String USER_AGENT = "CryptoControl Java API v2.1.0";
            urlConnection.setRequestProperty("user-agent", USER_AGENT);

            int code = urlConnection.getResponseCode();

            switch (code) {
                case 200:
                    break;
                case 401:
                    throw new InvalidAPIKeyException();
                default:
                    throw new BadResponseException();
            }


            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String line = "";

            while ((line = bufferedReader.readLine()) != null) jsonStr += line;
            in.close();

            callback.onSuccess(gson.fromJson(jsonStr, TypeofT));
        } catch (Exception e) {
            callback.onFailure(e);
        } finally {
            if (urlConnection != null) urlConnection.disconnect();
        }
    }


    /**
     * Get the top news articles from the CryptoControl News API.
     *
     * @param callback A callback fn returning a list of articles
     */
    public void getTopNews(OnResponseHandler<List<Article>> callback) {
        getTopNews(Language.ENGLISH, callback);
    }


    /**
     * Get the top news articles from the CryptoControl News API.
     *
     * @param lang     The language to choose from
     * @param callback A callback fn returning a list of articles
     */
    public void getTopNews(Language lang, OnResponseHandler<List<Article>> callback) {
        fetch("/news", lang, callback, Article.ArticleList.class);
    }


    /**
     * Get the latest news articles from the CryptoControl News API.
     *
     * @param callback A callback fn returning a list of articles
     */
    public void getLatestNews(OnResponseHandler<List<Article>> callback) {
        getLatestNews(Language.ENGLISH, callback);
    }


    /**
     * Get the latest news articles from the CryptoControl News API.
     *
     * @param lang     The language to choose from
     * @param callback A callback fn returning a list of articles
     */
    public void getLatestNews(Language lang, OnResponseHandler<List<Article>> callback) {
        fetch("/news?latest=true", lang, callback, Article.ArticleList.class);
    }


    /**
     * Get news articles grouped by category from the CryptoControl News API.
     *
     * @param callback A callback fn returning the response from the CryptoControl API.
     */
    public void getTopNewsByCategory(OnResponseHandler<CategoryResponse> callback) {
        getTopNewsByCategory(Language.ENGLISH, callback);
    }


    /**
     * Get news articles grouped by category from the CryptoControl News API.
     *
     * @param lang     The language to choose from
     * @param callback A callback fn returning the response from the CryptoControl API.
     */
    public void getTopNewsByCategory(Language lang, OnResponseHandler<CategoryResponse> callback) {
        fetch("/news/category", lang, callback, CategoryResponse.class);
    }


    /**
     * Get the top news articles for a specific coin from the CryptoControl API.
     *
     * @param coinName The coin name to get news for.
     * @param callback A callback fn returning the response from the CryptoControl API.
     */
    public void getTopNewsByCoin(String coinName, OnResponseHandler<List<Article>> callback) {
        getTopNewsByCoin(Language.ENGLISH, coinName, callback);
    }


    /**
     * Get the top news articles for a specific coin from the CryptoControl API.
     *
     * @param coinName The coin name to get news for.
     * @param lang     The language to choose from
     * @param callback A callback fn returning the response from the CryptoControl API.
     */
    public void getTopNewsByCoin(Language lang, String coinName, OnResponseHandler<List<Article>> callback) {
        fetch("/news/coin/" + coinName, lang, callback, Article.ArticleList.class);
    }


    /**
     * Get the latest news articles for a specific coin from the CryptoControl News API.
     *
     * @param coinName The coin name to get news for.
     * @param callback A callback fn returning the response from the CryptoControl API.
     */
    public void getLatestNewsByCoin(String coinName, OnResponseHandler<List<Article>> callback) {
        getLatestNewsByCoin(Language.ENGLISH, coinName, callback);
    }


    /**
     * Get the latest news articles for a specific coin from the CryptoControl News API.
     *
     * @param lang     The language to choose from
     * @param coinName The coin name to get news for.
     * @param callback A callback fn returning the response from the CryptoControl API.
     */
    public void getLatestNewsByCoin(Language lang, String coinName, OnResponseHandler<List<Article>> callback) {
        fetch("/news/coin/" + coinName + "?latest=true", lang, callback, Article.ArticleList.class);
    }


    /**
     * Get news articles grouped by category for a specific coin from the CryptoControl News API.
     *
     * @param coinName The coin name to get news for.
     * @param callback A callback fn returning the response from the CryptoControl API.
     */
    public void getTopNewsByCoinCategory(String coinName, OnResponseHandler<CategoryResponse> callback) {
        getTopNewsByCoinCategory(Language.ENGLISH, coinName, callback);
    }


    /**
     * Get news articles grouped by category for a specific coin from the CryptoControl News API.
     *
     * @param lang     The language to choose from
     * @param coinName The coin name to get news for.
     * @param callback A callback fn returning the response from the CryptoControl API.
     */
    public void getTopNewsByCoinCategory(Language lang, String coinName, OnResponseHandler<CategoryResponse> callback) {
        fetch("/news/coin/" + coinName + "/category", lang, callback, CategoryResponse.class);
    }


    /**
     * Get the top reddit posts for a specific coin from the CryptoControl API.
     *
     * @param coinName The coin name to get reddit items for.
     * @param callback A callback fn returning the response from the CryptoControl API.
     */
    public void getTopRedditPostsByCoin(String coinName, OnResponseHandler<List<RedditPost>> callback) {
        getTopRedditPostsByCoin(Language.ENGLISH, coinName, callback);
    }


    /**
     * Get the top reddit posts for a specific coin from the CryptoControl API.
     *
     * @param lang     The language to choose from
     * @param coinName The coin name to get reddit items for.
     * @param callback A callback fn returning the response from the CryptoControl API.
     */
    public void getTopRedditPostsByCoin(Language lang, String coinName, OnResponseHandler<List<RedditPost>> callback) {
        fetch("/reddit/coin/" + coinName, lang, callback, RedditPost.RedditPostList.class);
    }


    /**
     * Get the latest reddit posts for a specific coin from the CryptoControl API.
     *
     * @param coinName The coin name to get reddit items for.
     * @param callback A callback fn returning the response from the CryptoControl API.
     */
    public void getLatestRedditPostsByCoin(String coinName, OnResponseHandler<List<RedditPost>> callback) {
        getLatestRedditPostsByCoin(Language.ENGLISH, coinName, callback);
    }


    /**
     * Get the latest reddit posts for a specific coin from the CryptoControl API.
     *
     * @param lang     The language to choose from
     * @param coinName The coin name to get reddit items for.
     * @param callback A callback fn returning the response from the CryptoControl API.
     */
    public void getLatestRedditPostsByCoin(Language lang, String coinName,
                                           OnResponseHandler<List<RedditPost>> callback) {
        fetch("/reddit/coin/" + coinName + "?latest=true", lang, callback, RedditPost.RedditPostList.class);
    }


    /**
     * Get the latest tweets for a specific coin from the CryptoControl API.
     *
     * @param coinName The coin name to get reddit items for.
     * @param callback A callback fn returning the response from the CryptoControl API.
     */
    public void getTopTweetsByCoin(String coinName, OnResponseHandler<List<Tweet>> callback) {
        getTopTweetsByCoin(Language.ENGLISH, coinName, callback);
    }


    /**
     * Get the latest tweets for a specific coin from the CryptoControl API.
     *
     * @param lang     The language to choose from
     * @param coinName The coin name to get reddit items for.
     * @param callback A callback fn returning the response from the CryptoControl API.
     */
    public void getTopTweetsByCoin(Language lang, String coinName, OnResponseHandler<List<Tweet>> callback) {
        fetch("/tweets/coin/" + coinName, lang, callback, Tweet.TweetList.class);
    }


    /**
     * Get the top tweets for a specific coin from the CryptoControl API.
     *
     * @param coinName The coin name to get tweets for.
     * @param callback A callback fn returning the response from the CryptoControl API.
     */
    public void getLatestTweetsByCoin(String coinName, OnResponseHandler<List<Tweet>> callback) {
        getLatestTweetsByCoin(Language.ENGLISH, coinName, callback);
    }


    /**
     * Get the top tweets for a specific coin from the CryptoControl API.
     *
     * @param lang     The language to choose from
     * @param coinName The coin name to get tweets for.
     * @param callback A callback fn returning the response from the CryptoControl API.
     */
    public void getLatestTweetsByCoin(Language lang, String coinName, OnResponseHandler<List<Tweet>> callback) {
        fetch("/tweets/coin/" + coinName + "?latest=true", lang, callback, Tweet.TweetList.class);
    }


    /**
     * Get a single feed (articles/tweets/reddit) in english for a specific coin from the CryptoControl API.
     *
     * @param coinName The coin name to get the feed items for.
     * @param callback A callback fn returning the response from the CryptoControl API.
     */
    public void getTopFeedByCoin(String coinName, OnResponseHandler<List<Feed>> callback) {
        getTopFeedByCoin(Language.ENGLISH, coinName, callback);
    }


    /**
     * Get a single feed (articles/tweets/reddit) in english for a specific coin from the CryptoControl API.
     *
     * @param lang     The language of the feed
     * @param coinName The coin name to get the feed items for.
     * @param callback A callback fn returning the response from the CryptoControl API.
     */
    public void getTopFeedByCoin(Language lang, String coinName, OnResponseHandler<List<Feed>> callback) {
        fetch("/feed/coin/" + coinName, lang, callback, Feed.FeedList.class);
    }


    /**
     * Get a single feed (articles/tweets/reddit) for a specific coin from the CryptoControl API.
     *
     * @param coinName The coin name to get the feed for.
     * @param callback A callback fn returning the response from the CryptoControl API.
     */
    public void getLatestFeedByCoin(String coinName, OnResponseHandler<List<Feed>> callback) {
        getLatestFeedByCoin(Language.ENGLISH, coinName, callback);
    }


    /**
     * Get a single feed (articles/tweets/reddit) for a specific coin from the CryptoControl API.
     *
     * @param lang     The language to choose from
     * @param coinName The coin name to get the feed for.
     * @param callback A callback fn returning the response from the CryptoControl API.
     */
    public void getLatestFeedByCoin(Language lang, String coinName, OnResponseHandler<List<Feed>> callback) {
        fetch("/feed/coin/" + coinName + "?latest=true", lang, callback, Feed.FeedList.class);
    }


    /**
     * Get articles/tweets/reddit for a specific coin from the CryptoControl API.
     *
     * @param coinName The coin name to get the feed items for.
     * @param callback A callback fn returning the response from the CryptoControl API.
     */
    public void getTopItemsByCoin(String coinName, OnResponseHandler<CombinedFeedResponse> callback) {
        getTopItemsByCoin(Language.ENGLISH, coinName, callback);
    }


    /**
     * Get articles/tweets/reddit for a specific coin from the CryptoControl API.
     *
     * @param lang     The language to choose from
     * @param coinName The coin name to get the feed items for.
     * @param callback A callback fn returning the response from the CryptoControl API.
     */
    public void getTopItemsByCoin(Language lang, String coinName, OnResponseHandler<CombinedFeedResponse> callback) {
        fetch("/all/coin/" + coinName, lang, callback, CombinedFeedResponse.class);
    }


    /**
     * Get articles/tweets/reddit for a specific coin from the CryptoControl API.
     *
     * @param coinName The coin name to get the feed for.
     * @param callback A callback fn returning the response from the CryptoControl API.
     */
    public void getLatestItemsByCoin(String coinName, OnResponseHandler<CombinedFeedResponse> callback) {
        getLatestItemsByCoin(Language.ENGLISH, coinName, callback);
    }


    /**
     * Get articles/tweets/reddit for a specific coin from the CryptoControl API.
     *
     * @param lang     The language to choose from
     * @param coinName The coin name to get the feed for.
     * @param callback A callback fn returning the response from the CryptoControl API.
     */
    public void getLatestItemsByCoin(Language lang, String coinName, OnResponseHandler<CombinedFeedResponse> callback) {
        fetch("/all/coin/" + coinName + "?latest=true", lang, callback, CombinedFeedResponse.class);
    }


    /**
     * Get coin summary from the cryptocontrol API
     *
     * @param coinName The coin name to get the feed for.
     * @param callback A callback fn returning the response from the CryptoControl API.
     */
    public void getCoinDetails(String coinName, OnResponseHandler<CoinDetail> callback) {
        fetch("/details/coin/" + coinName, Language.ENGLISH, callback, CoinDetail.class);
    }


    public interface OnResponseHandler<T> {
        void onSuccess(T body);

        void onFailure(Exception e);
    }
}
