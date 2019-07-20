package io.cryptocontrol.cryptonewsapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.cryptocontrol.cryptonewsapi.exceptions.*;
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
     * A simple constructor which sets a API key
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
     * @param query    The query object
     * @param callback A callback fn returning the response from the CryptoControl API.
     * @param TypeofT  The type of the object that needs to be returned.
     */
    private void fetch(String path, Query query, OnResponseHandler callback, Type TypeofT) {
        HttpURLConnection urlConnection = null;

        if (query == null) query = new Query();


        try {
            // prepare the url
            String HOST = proxyURL != null ? proxyURL : "https://cryptocontrol.io/api/v1/public";
            URL url = new URL(HOST + path +
                    "?language=" + query.getLanguageSlug() +
                    "&sentiment=" + query.isSentimentEnabled() +
                    "&page=" + query.getPage()
            );

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Content-Type", "application/json");

            // set the api key
            urlConnection.setRequestProperty("x-api-key", apiKey);

            // Set the user agent
            String USER_AGENT = "CryptoControl Java API v3.0.0";
            urlConnection.setRequestProperty("user-agent", USER_AGENT);

            int code = urlConnection.getResponseCode();

            // check status code
            switch (code) {
                case 200:
                    break;
                case 429:
                    throw new RateLimitException();
                case 401:
                    throw new InvalidAPIKeyException();
                case 405:
                    throw new NotPremiumException();
                default:
                    throw new BadResponseException();
            }


            // read response
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            String jsonStr = "";

            while ((line = bufferedReader.readLine()) != null) jsonStr += line;
            in.close();

            // parse and return
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
    public void getAllNews(Query query, OnResponseHandler<List<Article>> callback) {
        if (query.getCoin() != null) fetch("/news/coin" + query.getCoin(), query, callback, Article.ArticleList.class);
        else fetch("/news", query, callback, Article.ArticleList.class);
    }


    /**
     * Get news by category
     *
     * @param q
     * @param callback
     */
    public void getNewsByCategory(Query q, OnResponseHandler<CategoryResponse> callback) {
        if (q.getCoin() != null) fetch("/news/coin/" + q.getCoin() + "/category", q, callback, CategoryResponse.class);
        else fetch("/news/category", q, callback, CategoryResponse.class);
    }


    /**
     * Get the top reddit posts for a specific coin from the CryptoControl API.
     *
     * @param callback A callback fn returning the response from the CryptoControl API.
     */
    public void getRedditPostsByCoin(Query q, OnResponseHandler<List<RedditPost>> callback) {
        if (q.getCoin() == null) callback.onFailure(new BadArguementsException());
        else fetch("/reddit/coin/" + q.getCoin(), q, callback, RedditPost.RedditPostList.class);
    }


    /**
     * Get the latest tweets for a specific coin from the CryptoControl API.
     *
     * @param callback A callback fn returning the response from the CryptoControl API.
     */
    public void getTweetsByCoin(Query q, OnResponseHandler<List<Tweet>> callback) {
        if (q.getCoin() == null) callback.onFailure(new BadArguementsException());
        else fetch("/tweets/coin/" + q.getCoin(), q, callback, Tweet.TweetList.class);
    }


    /**
     * Get a single feed (articles/tweets/reddit) in english for a specific coin from the CryptoControl API.
     *
     * @param callback A callback fn returning the response from the CryptoControl API.
     */
    public void getFeedByCoin(Query q, OnResponseHandler<List<Feed>> callback) {
        if (q.getCoin() == null) callback.onFailure(new BadArguementsException());
        else fetch("/feed/coin/" + q.getCoin(), q, callback, Feed.FeedList.class);
    }


    /**
     * Get articles/tweets/reddit for a specific coin from the CryptoControl API.
     *
     * @param callback A callback fn returning the response from the CryptoControl API.
     */
    public void getAllItemsByCoin(Query q, OnResponseHandler<CombinedFeedResponse> callback) {
        if (q.getCoin() == null) callback.onFailure(new BadArguementsException());
        else fetch("/all/coin/" + q.getCoin(), q, callback, CombinedFeedResponse.class);
    }


    /**
     * Get coin summary from the cryptocontrol API
     *
     * @param coinName The coin name to get the feed for.
     * @param callback A callback fn returning the response from the CryptoControl API.
     */
    public void getCoinDetails(String coinName, OnResponseHandler<CoinDetail> callback) {
        fetch("/details/coin/" + coinName, null, callback, CoinDetail.class);
    }


    public interface OnResponseHandler<T> {
        void onSuccess(T body);

        void onFailure(Exception e);
    }
}
