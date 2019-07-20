import io.cryptocontrol.cryptonewsapi.CryptoControlApi;
import io.cryptocontrol.cryptonewsapi.models.*;

import java.util.List;

/**
 * @author enamakel@cryptocontrol.io
 */
public class ApiTest {
    public static void main(String args[]) {
        CryptoControlApi api = new CryptoControlApi(System.getenv("API_KEY"));
        CryptoControlApi proxyApi = new CryptoControlApi(System.getenv("API_KEY"),
                "http://localhost:6999/api/v1/public");

        api.getAllNews(
                new Query(Language.ENGLISH),
                new CryptoControlApi.OnResponseHandler<List<Article>>() {
                    @Override public void onSuccess(List<Article> body) {
                        for (Article article : body) {
                            System.out.println(article.getTitle());
                        }

                    }


                    @Override public void onFailure(Exception e) {
                        e.printStackTrace();
                    }
                });

        api.getAllNews(
                new Query(Language.ENGLISH).enableSentiment(),
                new CryptoControlApi.OnResponseHandler<List<Article>>() {
                    @Override public void onSuccess(List<Article> body) {
                        for (Article article : body) {
                            System.out.println(article.getTitle());
                        }

                    }


                    @Override public void onFailure(Exception e) {
                        e.printStackTrace();
                    }
                });

        proxyApi.getAllNews(
                new Query(Language.ENGLISH),
                new CryptoControlApi.OnResponseHandler<List<Article>>() {
                    @Override public void onSuccess(List<Article> body) {
                        for (Article article : body) {
                            System.out.println(article.getTitle());
                        }

                    }


                    @Override public void onFailure(Exception e) {
                        e.printStackTrace();
                    }
                });

        api.getNewsByCategory(
                new Query("bitcoin", Language.RUSSIAN),
                new CryptoControlApi.OnResponseHandler<CategoryResponse>() {
                    @Override public void onSuccess(CategoryResponse body) {
                        for (Article article : body.getAnalysisArticles()) {
                            System.out.println(article.getTitle() + article.getSourceDomain());
                        }
                    }


                    @Override public void onFailure(Exception e) {
                        e.printStackTrace();
                    }
                });

        api.getTweetsByCoin(
                new Query("bitcoin"),
                new CryptoControlApi.OnResponseHandler<List<Tweet>>() {
                    @Override public void onSuccess(List<Tweet> body) {
                        for (Tweet post : body) System.out.println(post.getId());
                    }


                    @Override public void onFailure(Exception e) {
                        e.printStackTrace();
                    }
                });

        api.getRedditPostsByCoin(
                new Query("bitcoin"),
                new CryptoControlApi.OnResponseHandler<List<RedditPost>>() {
                    @Override public void onSuccess(List<RedditPost> body) {
                        for (RedditPost post : body) System.out.println(post.getId());
                    }


                    @Override public void onFailure(Exception e) {
                        e.printStackTrace();
                    }
                });

        api.getFeedByCoin(
                new Query("bitcoin", Language.ENGLISH),
                new CryptoControlApi.OnResponseHandler<List<Feed>>() {
                    @Override public void onSuccess(List<Feed> body) {
                        for (Feed post : body) {
                            System.out.println(post.getType());
                        }
                    }


                    @Override public void onFailure(Exception e) {
                        e.printStackTrace();
                    }
                });

        api.getCoinDetails("ethereum", new CryptoControlApi.OnResponseHandler<CoinDetail>() {
            @Override public void onSuccess(CoinDetail body) {
                System.out.println(body.getDescription());
            }


            @Override public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });

        api.getAllItemsByCoin(
                new Query("bitcoin", Language.ENGLISH),
                new CryptoControlApi.OnResponseHandler<CombinedFeedResponse>() {
                    @Override public void onSuccess(CombinedFeedResponse body) {
                        System.out.println(body.getArticles().size());
                    }


                    @Override public void onFailure(Exception e) {
                        e.printStackTrace();
                    }
                });
    }
}
