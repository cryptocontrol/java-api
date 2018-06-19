package io.cryptocontrol.cryptonewsapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.cryptocontrol.cryptonewsapi.models.Article;
import io.cryptocontrol.cryptonewsapi.models.Articles;
import io.cryptocontrol.cryptonewsapi.models.CategoryResponse;

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
    private final String USER_AGENT = "CryptoControl Java API v1.1.0";
    private final String HOST = "https://cryptocontrol.io/api/v1/public";

    private final Gson gson = new GsonBuilder().create();


    public CryptoControlApi(final String apiKey) {
        if (apiKey == null)
            throw new Error("No API key found. Register for an API key at https://cryptocontrol.io/apis");

        this.apiKey = apiKey;
    }


    /**
     * Helper function to make a request to the CryptoControl server.
     *
     * @param path     The URL to call
     * @param callback A callback fn returning the response from the CryptoControl API.
     * @param TypeofT  The type of the object that needs to be returned.
     */
    private void fetch(String path, OnResponseHandler callback, Type TypeofT) {
        String jsonStr = "";
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL(HOST + path);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("x-api-key", apiKey);
            urlConnection.setRequestProperty("user-agent", USER_AGENT);
            int code = urlConnection.getResponseCode();

            switch (code) {
                case 200:
                    break;
                case 401:
                    throw new Exception("Invalid API Key");
                default:
                    throw new Exception("Bad response from the CryptoControl server");
            }


            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String line = "";

            while ((line = bufferedReader.readLine()) != null)
                jsonStr += line;
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
     * @param callback A callback fn returning the response from the CryptoControl API.
     */
    public void getTopNews(OnResponseHandler<List<Article>> callback) {
        fetch("/news", callback, Articles.class);
    }


    /**
     * Get the latest news articles from the CryptoControl News API.
     *
     * @param callback A callback fn returning the response from the CryptoControl API.
     */
    public void getLatestNews(OnResponseHandler<List<Article>> callback) {
        fetch("/news?latest=true", callback, Articles.class);
    }


    /**
     * Get news articles grouped by category from the CryptoControl News API.
     *
     * @param callback A callback fn returning the response from the CryptoControl API.
     */
    public void getTopNewsByCategory(OnResponseHandler<CategoryResponse> callback) {
        fetch("/news/category", callback, CategoryResponse.class);
    }


    /**
     * Get the top news articles for a specific coin from the CryptoControl API.
     *
     * @param coinName The coin name to get news for.
     * @param callback A callback fn returning the response from the CryptoControl API.
     */
    public void getTopNewsByCoin(String coinName, OnResponseHandler<List<Article>> callback) {
        fetch("/news/coin/" + coinName, callback, Articles.class);
    }


    /**
     * Get the latest news articles for a specific coin from the CryptoControl News API.
     *
     * @param coinName The coin name to get news for.
     * @param callback A callback fn returning the response from the CryptoControl API.
     */
    public void getLatestNewsByCoin(String coinName, OnResponseHandler<List<Article>> callback) {
        fetch("/news/coin/" + coinName + "?latest=true", callback, Articles.class);
    }


    /**
     * Get news articles grouped by category for a specific coin from the CryptoControl News API.
     *
     * @param coinName The coin name to get news for.
     * @param callback A callback fn returning the response from the CryptoControl API.
     */
    public void getTopNewsByCoinCategory(String coinName, OnResponseHandler<CategoryResponse> callback) {
        fetch("/news/coin/" + coinName + "/category", callback, CategoryResponse.class);
    }


    public static interface OnResponseHandler<T> {
        void onSuccess(T body);

        void onFailure(Exception e);
    }
}
