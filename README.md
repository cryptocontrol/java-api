Java Client - CryptoControl Crypto News Api
===========================================

Official Java client for the [CryptoControl.io](https://cryptocontrol.io) API. The CryptoControl java client lets developers access rich formatted articles from crypto news sources from all around the world.
It also allows developers to get access to a rich database of information (website, coin summary, blockexplorers, extra links etc..) for the top 2000 cryptocurrencies on CoinMarketCap.

Sprecial thanks to our contributors
- [PrograMonks](https://play.google.com/store/apps/developer?id=PrograMonks&hl=en) for pointing out a serialization issue when using with Progaurd

## Installation
Add this dependency into your `pom.xml` file.

```xml
<dependency>
    <groupId>io.cryptocontrol</groupId>
    <artifactId>crypto-news-api</artifactId>
    <version>2.5.0</version>
</dependency>
```

or use with gradle

```
compile 'io.cryptocontrol:crypto-news-api:2.5.0'
```


## Usage
First make sure that you've recieved an API key by visiting [https://cryptocontrol.io/apis](https://cryptocontrol.io/apis). With the API key, you can write the following code.

```java
// Connect to the CryptoControl API
CryptoControlApi api = new CryptoControlApi("API_KEY");

// Connect to a self-hosted proxy server (to improve performance) that points to cryptocontrol.io
CryptoControlApi proxyApi = new CryptoControlApi("API_KEY", "http://cryptocontrol_proxy/api/v1/public");

// Enable sentiment datapoints
api.enableSentiment();

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
- **getCoinDetails(coin: String)** Get all details about a particular coin (links, description, subreddits, twitter etc..)
- **enableSentiment()** Get the sentiment datapoints


`lang` allows developers to choose which language they'd like to get the feed. Currently the CryptoControl API supports the following languages:
- English (`en` default)
- Chinese/Mandarin (`cn`)
- German (`de`)
- Italian (`it`)
- Japanese (`jp`)
- Korean (`ko`)
- Portuguese (`po`)
- Russian (`ru`)
- Spanish (`es`)

The coin slugs are the coin id's used from the CoinMarketCap api. You can see the full list of coins here: [https://api.coinmarketcap.com/v1/ticker/?limit=2000](https://api.coinmarketcap.com/v1/ticker/?limit=2000)

`enableSentiment()` will tell CrpytoControl to return articles/reddit/twitter with sentiment datapoints as well (ie. how much +ve/-ve an article is). This feature is for [CryptoControl premium users](https://cryptocontrol.io/en/about/premium) only.

## Sample response from the server

```
[{
    "hotness": 70862.60323026273,
    "activityHotness": 4.601980262729618,
    "primaryCategory": "General",
    "words": 1444,
    "similarArticles": [
        {
            "_id": "5b363b525b113200191a1d5f",
            "publishedAt": "2018-06-29T13:42:44.000Z",
            "title": "Op-Ed: Challenge of Mining Centralization Unveils Bitcoin’s Elegant Design",
            "url": "https://cryptocontrol.io/r/api/article/5b363b525b113200191a1d5f?ref=5ac11440ec0af7be35528459",
            "source": {
                "_id": "59d8c361ef8bf95cc2bfb66f",
                "name": "Bitcoin Magazine",
                "url": "https://bitcoinmagazine.com/"
            },
            "sourceDomain": "bitcoinmagazine.com",
            "thumbnail": null
        },
        {
            "_id": "5b3865405c5681000f2f7407",
            "publishedAt": "2018-06-30T14:58:00.000Z",
            "title": "Arbitration on a Governed Blockchain: EOS’ Crisis of Dispute Resolution",
            "url": "https://cryptocontrol.io/r/api/article/5b3865405c5681000f2f7407?ref=5ac11440ec0af7be35528459",
            "source": {
                "_id": "59d70be3ef8bf95cc2aa2b4f",
                "name": "CoinTelegraph",
                "url": "https://cointelegraph.com/"
            },
            "sourceDomain": "cointelegraph.com",
            "thumbnail": null
        }
    ],
    "coins": [
        {
            "_id": "59cb59f9b0836b0a63aebc7c",
            "name": "Ethereum",
            "tradingSymbol": "eth",
            "slug": "ethereum"
        },
        {
            "_id": "59d21e9b83a0523906a45dc5",
            "name": "EOS",
            "slug": "eos",
            "tradingSymbol": "eos"
        }
    ],
    "_id": "5b3a2e1b104844000fd64e28",
    "description": "The EOS governance disaster offers a strong reminder of how entrenched human mistrust can be difficult to overcome.",
    "publishedAt": "2018-07-02T12:00:27.000Z",
    "title": "It's Too Soon for On-Chain Governance - CoinDesk",
    "url": "https://cryptocontrol.io/r/api/article/5b3a2e1b104844000fd64e28?ref=5ac11440ec0af7be35528459",
    "source": {
        "_id": "59ce11393a44cf289a9a71f5",
        "name": "CoinDesk",
        "url": "http://coindesk.com"
    },
    "thumbnail": "https://cryptocontrol.io/r/thumbnail/5b3a2e1b104844000fd64e28?ref=5ac11440ec0af7be35528459",
    "sourceName": "CoinDesk",
    "sourceUrl": "http://coindesk.com",
    "sourceDomain": "coindesk.com",
    "originalImageUrl": "https://media.coindesk.com/uploads/2018/06/shutterstock_153840266-e1530230263310.jpg"
}]
```