package kib.lab6.common.util.client_server_communication;

import kib.lab6.common.entities.HumanBeing;
import kib.lab6.common.entities.enums.Mood;

import java.io.Serializable;

public class Request implements Serializable {

    private final String commandNameToSend;
    private HumanBeing humanToSend = null;
    private Integer numberArgumentToSend = null;
    private Mood moodArgumentToSend = null;
    private String clientInfo = null;
    private boolean serverRequest = false;

    public Request(String name) {
        this.commandNameToSend = name;
    }

    public Request(String name, HumanBeing human) {
        this.commandNameToSend = name;
        this.humanToSend = human;
    }

    public Request(String name, int argument, HumanBeing human) {
        this.commandNameToSend = name;
        this.numberArgumentToSend = argument;
        this.humanToSend = human;
    }

    public Request(String name, int argument) {
        this.commandNameToSend = name;
        this.numberArgumentToSend = argument;
    }

    public Request(String name, Mood argument) {
        this.commandNameToSend = name;
        this.moodArgumentToSend = argument;
    }

    public String getCommandNameToSend() {
        return commandNameToSend;
    }

    public HumanBeing getHumanToSend() {
        return humanToSend;
    }

    public int getNumberArgumentToSend() {
        return numberArgumentToSend;
    }

    public Mood getMoodArgumentToSend() {
        return moodArgumentToSend;
    }

    public String getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(String clientInfo) {
        this.clientInfo = clientInfo;
    }

    @Override
    public String toString() {
        return "Имя команды: " + commandNameToSend
                + (humanToSend == null ? "" : " / Информация о человеке: " + humanToSend.toString())
                + (numberArgumentToSend == null ? "" : " / Числовой аргумент = " + numberArgumentToSend)
                + (moodArgumentToSend == null ? "" : " / Настроение: " + moodArgumentToSend);
    }

    public boolean isServerRequest() {
        return serverRequest;
    }

    public void setServerRequest(boolean serverRequest) {
        this.serverRequest = serverRequest;
    }
}
