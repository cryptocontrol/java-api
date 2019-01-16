package io.cryptocontrol.cryptonewsapi.exceptions;

/**
 * @author enamakel@cryptocontrol.io
 */
public class RateLimitException extends Exception {
    public RateLimitException() {
        super("Hit rate limits from the CryptoControl server");
    }
}
