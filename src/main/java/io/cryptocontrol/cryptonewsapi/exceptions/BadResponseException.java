package io.cryptocontrol.cryptonewsapi.exceptions;

/**
 * @author enamakel@cryptocontrol.io
 */
public class BadResponseException extends Exception {
    public BadResponseException() {
        super("Bad response from the CryptoControl server");
    }
}
