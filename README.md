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