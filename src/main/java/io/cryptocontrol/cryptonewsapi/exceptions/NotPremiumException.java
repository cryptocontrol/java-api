package io.cryptocontrol.cryptonewsapi.exceptions;

public class NotPremiumException extends CryptoControlException {
    public NotPremiumException() {
        super("You are not a premium user. Visit https://cryptocontrol.io/about/premium for more info");
    }
}

