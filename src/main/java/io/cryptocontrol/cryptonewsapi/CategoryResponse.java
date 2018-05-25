package io.cryptocontrol.cryptonewsapi;

import java.util.List;

/**
 * A category response from the CryptoControl server
 *
 * @author enamakel@cryptocontrol.io
 */
public class CategoryResponse {
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


    List<Article> analysis;
    List<Article> blockchain;
    List<Article> exchanges;
    List<Article> general;
    List<Article> government;
    List<Article> ico;
    List<Article> mining;
}
