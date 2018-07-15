package io.cryptocontrol.cryptonewsapi.models;

import java.util.ArrayList;
import java.util.List;

/**
 * A category response from the CryptoControl server
 *
 * @author enamakel@cryptocontrol.io
 */
public class CategoryResponse {
    private List<Article> analysis = new ArrayList<>();
    private List<Article> blockchain = new ArrayList<>();
    private List<Article> exchanges = new ArrayList<>();
    private List<Article> general = new ArrayList<>();
    private List<Article> government = new ArrayList<>();
    private List<Article> ico = new ArrayList<>();
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
