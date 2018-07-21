Java Client - CryptoControl Crypto News Api
===========================================

Official Java client for the [CryptoControl.io](https://cryptocontrol.io) API. The CryptoControl java client lets developers access rich formatted articles from crypto news sources from all around the world.
It also allows developers to get access to a rich database of information (website, coin summary, blockexplorers, extra links etc..) for the top 2000 cryptocurrencies on CoinMarketCap.

## Installation
Add this dependency into your `pom.xml` file.

```xml
<dependency>
    <groupId>io.cryptocontrol</groupId>
    <artifactId>crypto-news-api</artifactId>
    <version>2.1.0</version>
</dependency>
```

or use with gradle

```
compile 'io.cryptocontrol:crypto-news-api:2.1.0'
```


## Usage
First make sure that you've recieved an API key by visiting [https://cryptocontrol.io/apis](https://cryptocontrol.io/apis). With the API key, you can write the following code.

```java
// Connect to the CryptoControl API
CryptoControlApi api = new CryptoControlApi("API_KEY");

// Connect to a self-hosted proxy server (to improve performance) that points to cryptocontrol.io
CryptoControlApi proxyApi = new CryptoControlApi("API_KEY", "http://cryptocontrol_proxy/api/v1/public");

// Get top crypto news
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

// Get latest tweets for bitcoin
api.getLatestTweetsByCoin("bitcoin", new CryptoControlApi.OnResponseHandler<List<Tweet>>() {
    @Override public void onSuccess(List<Tweet> body) {
        for (Tweet post : body) {
            System.out.println(post.getId());
        }
    }


    @Override public void onFailure(Exception e) {
        e.printStackTrace();
    }
});

// Get latest russian crypto news
api.getLatestNews(Language.RUSSIAN, new CryptoControlApi.OnResponseHandler<List<Article>>() {
    @Override public void onSuccess(List<Article> body) {
        for (Article article : body) {
            System.out.println(article.getTitle());
        }
    }


    @Override public void onFailure(Exception e) {

    }
});

// Get rich metadata (wallets, blockexplorers, twitter handles etc..) for ethereum
api.getCoinDetails("ethereum", new CryptoControlApi.OnResponseHandler<CoinDetail>() {
    @Override public void onSuccess(CoinDetail body) {
        System.out.println(body.getDescription());
    }


    @Override public void onFailure(Exception e) {
        e.printStackTrace();
    }
});

```


## Available Functions

- **getTopNews(lang?: enum)** Get the top news articles.
- **getLatestNews(lang?: enum)** Get the latest news articles.
- **getTopNewsByCategory(lang?: enum)** Get news articles grouped by category.
- **getTopNewsByCoin(lang?: enum, coin: String)** Get the top news articles for a specific coin from the CryptoControl API.
- **getLatestNewsByCoin(lang?: enum, coin: String)** Get the latest news articles for a specific coin.
- **getTopNewsByCoinCategory(lang?: enum, coin: String)** Get news articles grouped by category for a specific coin.
- **getTopRedditPostsByCoin(lang?: enum, coin: String)** Get top reddit posts for a particular coin
- **getLatestRedditPostsByCoin(lang?: enum, coin: String)** Get latest reddit posts for a particular coin
- **getTopTweetsByCoin(lang?: enum, coin: String)** Get top tweets for a particular coin
- **getLatestTweetsByCoin(lang?: enum, coin: String)** Get latest tweets for a particular coin
- **getTopFeedByCoin(lang?: enum, coin: String)** Get a combined feed (reddit/tweets/articles) for a particular coin (sorted by time)
- **getLatestFeedByCoin(lang?: enum, coin: String)** Get a combined feed (reddit/tweets/articles) for a particular coin (sorted by relevance)