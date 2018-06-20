Java Client - CryptoCryptol Crypto News Api
===========================================

Official Java client for the [CryptoControl.io](https://cryptocontrol.io) API. The CryptoControl java client lets developers access rich formatted articles from cryptonews sources from all around the world.

## Installation
Add this dependency into your `pom.xml` file.

```xml
<dependency>
    <groupId>io.cryptocontrol</groupId>
    <artifactId>crypto-news-api</artifactId>
    <version>1.1.0</version>
</dependency>
```

or use with gradle

```
compile 'io.cryptocontrol:crypto-news-api:1.1.0'
```


## Usage
First make sure that you've recieved an API key by visiting [https://cryptocontrol.io/apis](https://cryptocontrol.io/apis). With the API key, you can write the following code.

**NOTE**: API access is rate-limited to 5 req/sec and 10,000 req/hour. It'll be a good idea to cache your API calls.

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

- **getTopNews()** Get the top news articles.
- **getLatestNews()** Get the latest news articles.
- **getTopNewsByCategory()** Get news articles grouped by category.
- **getTopNewsByCoin(coin: String)** Get the top news articles for a specific coin from the CryptoControl API.
- **getLatestNewsByCoin(coin: String)** Get the latest news articles for a specific coin.
- **getTopNewsByCoinCategory(coin: String)** Get news articles grouped by category for a specific coin.
- **getTopRedditPostsByCoin(coin: String)** Get top reddit posts for a particular coin
- **getLatestRedditPostsByCoin(coin: String)** Get latest reddit posts for a particular coin
- **getTopTweetsByCoin(coin: String)** Get top tweets for a particular coin
- **getLatestTweetsByCoin(coin: String)** Get latest tweets for a particular coin
- **getTopFeedByCoin(coin: String)** Get a combined feed (reddit/tweets/articles) for a particular coin (sorted by time)
- **getLatestFeedByCoin(coin: String)** Get a combined feed (reddit/tweets/articles) for a particular coin (sorted by relevance)