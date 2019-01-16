package io.cryptocontrol.cryptonewsapi.exceptions;

/**
 * @author enamakel@cryptocontrol.io
 */
public class BadResponseException extends CryptoControlException {
    public BadResponseException() {
        super("Bad response from the CryptoControl server");
    }
}
