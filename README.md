Java Client - CryptoCryptol Crypto News Api
===========================================

Official Java client for the [CryptoControl.io](https://cryptocontrol.io) API. The CryptoControl java client lets developers access rich formatted articles from cryptonews sources from all around the world.

## Installation
Add this dependency into your `pom.xml` file.

```xml
<dependency>
    <groupId>io.cryptocontrol</groupId>
    <artifactId>crypto-news-api</artifactId>
    <version>1.0.0</version>
</dependency>
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

- **getTopNews()** Get the top news articles from the CryptoControl News API.
- **getLatestNews()** Get the latest news articles from the CryptoControl News API.
- **getTopNewsByCategory()** Get news articles grouped by category from the CryptoControl News API.
- **getTopNewsByCoin(coin: String)** Get the top news articles for a specific coin from the CryptoControl API.
- **getLatestNewsByCoin(coin: String)** Get the latest news articles for a specific coin from the CryptoControl News API.
- **getTopNewsByCoinCategory(coin: String)** Get news articles grouped by category for a specific coin from the CryptoControl News API.
