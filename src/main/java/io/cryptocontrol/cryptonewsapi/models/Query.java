package io.cryptocontrol.cryptonewsapi.models;

/**
 * @author enamakel@cryptocontrol.io
 */
public class Query {
    private Language language;
    private Boolean isLatest = false;
    private Integer page = 0;
    private String coin = null;
    private Boolean sentimentEnabled = false;


    public Query() {

    }


    public Query(Language language) {
        this(language, false);
    }


    public Query(Language language, Boolean isLatest) {
        this(language, isLatest, 0);
    }


    public Query(Language language, Boolean isLatest, Integer page) {
        this(null, language, isLatest, 0);
    }


    public Query(String coin) {
        this(coin, Language.ENGLISH);
    }


    public Query(String coin, Language language) {
        this(coin, language, false);
    }


    public Query(String coin, Language language, Boolean isLatest) {
        this(coin, language, isLatest, 0);
    }


    public Query(String coin, Language language, Boolean isLatest, Integer page) {
        this.language = language;
        this.isLatest = isLatest;
        this.page = page;
        this.coin = coin;
    }


    public String getCoin() {
        return coin;
    }


    public Query setCoin(String coin) {
        this.coin = coin;
        return this;
    }


    public Boolean getLatest() {
        return isLatest;
    }


    public Query setLatest(Boolean latest) {
        isLatest = latest;
        return this;
    }


    public Integer getPage() {
        return page;
    }


    public Query setPage(Integer page) {
        this.page = page;
        return this;
    }


    public String getLanguageSlug() {
        switch (language) {
            default:
            case ENGLISH:
                return "en";

            case RUSSIAN:
                return "ru";

            case GERMAN:
                return "de";

            case SPANISH:
                return "es";

            case ITALIAN:
                return "it";

            case PORTUGUESE:
                return "po";

            case CHINESE:
                return "cn";

            case JAPANESE:
                return "jp";

            case KOREAN:
                return "ko";
        }
    }


    public Query setLanguage(Language language) {
        this.language = language;
        return this;
    }


    public Query enableSentiment() {
        return this;
    }


    public Boolean isSentimentEnabled() {
        return sentimentEnabled;
    }
}
