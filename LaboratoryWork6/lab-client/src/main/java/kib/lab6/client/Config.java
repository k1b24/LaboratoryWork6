package kib.lab6.client;

import kib.lab6.common.util.HumanValidator;
import kib.lab6.common.util.TextSender;

public final class Config {
    private static final TextSender TEXT_SENDER = new TextSender(System.out);
    private static final HumanValidator HUMAN_VALIDATOR = new HumanValidator(getTextSender());

    private Config() {

    }

    public static TextSender getTextSender() {
        return TEXT_SENDER;
    }

    public static HumanValidator getHumanValidator() {
        return HUMAN_VALIDATOR;
    }
}
