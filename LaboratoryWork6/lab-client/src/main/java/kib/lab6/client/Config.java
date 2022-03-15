package kib.lab6.client;

import kib.lab6.client.entities.CollectionManager;
import kib.lab6.client.user_command_line.CommandManager;

public final class Config {

    private static final String SYS_ENVIRONMENT = "HUMAN_INFO";
    private static final CollectionManager COLLECTION_MANAGER = new CollectionManager(SYS_ENVIRONMENT);
    private static final CommandManager COMMAND_MANAGER = new CommandManager();

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
}
