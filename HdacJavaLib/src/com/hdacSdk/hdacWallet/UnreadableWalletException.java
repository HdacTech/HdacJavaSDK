package com.hdacSdk.hdacWallet;

/**
 * @brief Definition of Hdac wallet operational errors
 * @details Defining an error that occurs when creating a wallet class
 * @class UnreadableWalletException
 * @date 2018-05-23.
 * @author Hdac Technology 
 *
 */

public class UnreadableWalletException extends Exception {
    public UnreadableWalletException(String s) {
        super(s);
    }

    public UnreadableWalletException(String s, Throwable t) {
        super(s, t);
    }

    public static class BadPassword extends UnreadableWalletException {
        public BadPassword() {
            super("Password incorrect");
        }
    }

    public static class FutureVersion extends UnreadableWalletException {
        public FutureVersion() { super("Unknown wallet version from the future."); }
    }

    public static class WrongNetwork extends UnreadableWalletException {
        public WrongNetwork() {
            super("Mismatched network ID");
        }
    }
}
