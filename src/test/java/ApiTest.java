import io.cryptocontrol.cryptonewsapi.Article;
import io.cryptocontrol.cryptonewsapi.CategoryResponse;
import io.cryptocontrol.cryptonewsapi.CryptoControlApi;

import java.util.List;

/**
 * @author enamakel@cryptocontrol.io
 */
public class ApiTest {
    public static void main(String args[]) {
        CryptoControlApi api = new CryptoControlApi(System.getenv("API_KEY"));

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


        api.getLatestNews(new CryptoControlApi.OnResponseHandler<List<Article>>() {
            @Override public void onSuccess(List<Article> body) {
                for (Article article : body) {
                    System.out.println(article.getTitle());
                }
            }


            @Override public void onFailure(Exception e) {

            }
        });

        api.getLatestNewsByCoin("bitcoin", new CryptoControlApi.OnResponseHandler<List<Article>>() {
            @Override public void onSuccess(List<Article> body) {
                for (Article article : body) {
                    System.out.println(article.getTitle());
                }
            }


            @Override public void onFailure(Exception e) {

            }
        });


        api.getTopNewsByCategory(new CryptoControlApi.OnResponseHandler<CategoryResponse>() {
            @Override public void onSuccess(CategoryResponse body) {
                for (Article article : body.getAnalysisArticles()) {
                    System.out.println(article.getTitle());
                }
            }


            @Override public void onFailure(Exception e) {

            }
        });

        api.getTopNewsByCoinCategory("bitcoin", new CryptoControlApi.OnResponseHandler<CategoryResponse>() {
            @Override public void onSuccess(CategoryResponse body) {
                for (Article article : body.getAnalysisArticles()) {
                    System.out.println(article.getTitle());
                }
            }


            @Override public void onFailure(Exception e) {

            }
        });
    }
}
