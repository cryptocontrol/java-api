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

        api.getLatestNews(Language.RUSSIAN, new CryptoControlApi.OnResponseHandler<List<Article>>() {
            @Override public void onSuccess(List<Article> body) {

            }


            @Override public void onFailure(Exception e) {

            }
        });

        api.getTopNewsByCategory(new CryptoControlApi.OnResponseHandler<CategoryResponse>() {
            @Override public void onSuccess(CategoryResponse body) {
                for (Article article : body.getAnalysisArticles()) {
                    System.out.println(article.getTitle() + article.getSourceDomain());
                }
            }


            @Override public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });

        api.getLatestNews(new CryptoControlApi.OnResponseHandler<List<Article>>() {
            @Override public void onSuccess(List<Article> body) {
                for (Article article : body) {
                    System.out.println(article.getTitle());
                }
            }


            @Override public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });

        api.getLatestNewsByCoin("bitcoin", new CryptoControlApi.OnResponseHandler<List<Article>>() {
            @Override public void onSuccess(List<Article> body) {
                for (Article article : body) {
                    System.out.println(article.getTitle());
                }
            }


            @Override public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });


        api.getTopNewsByCategory(new CryptoControlApi.OnResponseHandler<CategoryResponse>() {
            @Override public void onSuccess(CategoryResponse body) {
                for (Article article : body.getAnalysisArticles()) {
                    System.out.println(article.getTitle());
                }
            }


            @Override public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });

        api.getTopNewsByCoinCategory("bitcoin", new CryptoControlApi.OnResponseHandler<CategoryResponse>() {
            @Override public void onSuccess(CategoryResponse body) {
                for (Article article : body.getAnalysisArticles()) {
                    System.out.println(article.getTitle());
                }
            }


            @Override public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });


        api.getTopRedditPostsByCoin("bitcoin", new CryptoControlApi.OnResponseHandler<List<RedditPost>>() {
            @Override public void onSuccess(List<RedditPost> body) {
                for (RedditPost post : body) {
                    System.out.println(post.getTitle());
                }
            }


            @Override public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });


        api.getTopTweetsByCoin("bitcoin", new CryptoControlApi.OnResponseHandler<List<Tweet>>() {
            @Override public void onSuccess(List<Tweet> body) {
                for (Tweet post : body) {
                    System.out.println(post.getId());
                }
            }


            @Override public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });


        api.getLatestFeedByCoin("bitcoin", new CryptoControlApi.OnResponseHandler<List<Feed>>() {
            @Override public void onSuccess(List<Feed> body) {
                for (Feed post : body) {
                    System.out.println(post.getType());
                }
            }


            @Override public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });
    }
}
