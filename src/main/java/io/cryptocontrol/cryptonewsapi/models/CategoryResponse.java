package io.cryptocontrol.cryptonewsapi.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * A category response from the CryptoControl server
 *
 * @author enamakel@cryptocontrol.io
 * @author PrograMonks
 */
public class CategoryResponse {
    @SerializedName("analysis")
    private List<Article> analysis = new ArrayList<>();

    @SerializedName("blockchain")
    private List<Article> blockchain = new ArrayList<>();

    @SerializedName("exchanges")
    private List<Article> exchanges = new ArrayList<>();

    @SerializedName("general")
    private List<Article> general = new ArrayList<>();

    @SerializedName("government")
    private List<Article> government = new ArrayList<>();

    @SerializedName("ico")
    private List<Article> ico = new ArrayList<>();

    @SerializedName("mining")
    private List<Article> mining = new ArrayList<>();


    public List<Article> getAnalysisArticles() {
        return analysis;
    }


    public List<Article> getBlockchainArticles() {
        return blockchain;
    }


    public List<Article> getExchangesArticles() {
        return exchanges;
    }


    public List<Article> getGeneralArticles() {
        return general;
    }


    public List<Article> getGovernmentArticles() {
        return government;
    }


    public List<Article> getICOArticles() {
        return ico;
    }


    public List<Article> getMiningArticles() {
        return mining;
    }
}
