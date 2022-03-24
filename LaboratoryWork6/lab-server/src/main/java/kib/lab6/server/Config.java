package kib.lab6.server;

import kib.lab6.common.entities.CollectionManager;
import kib.lab6.common.util.HumanValidator;
import kib.lab6.common.util.TextSender;

public final class Config {

    private static final String SYS_ENVIRONMENT = "HUMAN_INFO";
    private static final CollectionManager COLLECTION_MANAGER = new CollectionManager(SYS_ENVIRONMENT);
    private static final CommandManager COMMAND_MANAGER = new CommandManager();
    private static final TextSender TEXT_SENDER = new TextSender(System.out);
    private static final HumanValidator HUMAN_VALIDATOR = new HumanValidator(getTextSender());

    private Config() {

    }

    public static CollectionManager getCollectionManager() {
        return COLLECTION_MANAGER;
    }

    public static CommandManager getCommandManager() {
        return COMMAND_MANAGER;
    }

    public static String getSystemEnvironment() {
        return SYS_ENVIRONMENT;
    }

    public static String getFilePath() {
        return System.getenv(getSystemEnvironment());
    }

    public static TextSender getTextSender() {
        return TEXT_SENDER;
    }

    public static HumanValidator getHumanValidator() {
        return HUMAN_VALIDATOR;
    }
}
