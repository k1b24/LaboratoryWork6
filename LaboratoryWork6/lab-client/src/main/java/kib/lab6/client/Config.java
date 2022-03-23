package kib.lab6.client;

import kib.lab6.common.util.HumanValidator;
import kib.lab6.common.util.TextSender;

public class Config {
    private static final TextSender TEXT_SENDER = new TextSender(System.out);
    private static final HumanValidator humanValidator = new HumanValidator(getTextSender());

    public static TextSender getTextSender() {
        return TEXT_SENDER;
    }

    public static HumanValidator getHumanValidator() {
        return humanValidator;
    }
}
