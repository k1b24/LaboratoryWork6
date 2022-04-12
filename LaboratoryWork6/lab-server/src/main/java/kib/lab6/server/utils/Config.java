package kib.lab6.server.utils;

import kib.lab6.common.util.console_workers.TextSender;


public final class Config {

    private static boolean isWorking = true;
    private static final String SYS_ENVIRONMENT = "HUMAN_INFO";
    private static final CollectionManager COLLECTION_MANAGER = new CollectionManager();
    private static final CommandManager COMMAND_MANAGER = new CommandManager();
    private static final TextSender TEXT_SENDER = new TextSender(System.out);

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

    public static boolean isWorking() {
        return isWorking;
    }

    public static void setIsWorking(boolean isWorking) {
        Config.isWorking = isWorking;
    }
}
