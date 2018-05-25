Java Client - CryptoCryptol Crypto News Api
===========================================

This document details a java client for the CryptoControl News API.

## Usage

```java


CryptoControlApi api = new CryptoControlApi("API_KEY");

api.getTopNews(new CryptoControlApi.OnResponseHandler<List<Article>>() {
    public void onSuccess(List<Article> body) {
        for (Article article : body) {
            System.out.println(article.getTitle());
        }
    }


    public void onFailure(Exception e) {
        e.printStackTrace();
    }
});

```


## Available Functions

- **getTopNews()** Get the top news articles from the CryptoControl News API.
- **getLatestNews()** Get the latest news articles from the CryptoControl News API.
- **getTopNewsByCategory()** Get news articles grouped by category from the CryptoControl News API.
- **getTopNewsByCoin(coin: String)** Get the top news articles for a specific coin from the CryptoControl API.
- **getLatestNewsByCoin(coin: String)** Get the latest news articles for a specific coin from the CryptoControl News API.
- **getTopNewsByCoinCategory(coin: String)** Get news articles grouped by category for a specific coin from the CryptoControl News API.
