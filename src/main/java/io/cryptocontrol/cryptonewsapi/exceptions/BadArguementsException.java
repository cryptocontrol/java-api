package io.cryptocontrol.cryptonewsapi.exceptions;

/**
 * @author enamakel@cryptocontrol.io
 */
public class BadArguementsException extends CryptoControlException {
    public BadArguementsException() {
        super("Bad arguments. Please check the documentation to give the right query argument to this function.");
    }
}
