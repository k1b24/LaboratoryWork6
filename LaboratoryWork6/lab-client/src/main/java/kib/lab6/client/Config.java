package kib.lab6.client;

import kib.lab6.common.util.console_workers.TextSender;

public final class Config {
    private static final TextSender TEXT_SENDER = new TextSender(System.out);

    private Config() {

    }

    public static TextSender getTextSender() {
        return TEXT_SENDER;
    }
}
