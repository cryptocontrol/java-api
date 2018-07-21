package io.cryptocontrol.cryptonewsapi.exceptions;

/**
 * @author enamakel@cryptocontrol.io
 */
public class InvalidAPIKeyException extends Exception {
    public InvalidAPIKeyException() {
        super("Invalid API Key");
    }
}
