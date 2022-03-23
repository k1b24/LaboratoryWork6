package kib.lab6.common.util;

import kib.lab6.common.entities.HumanBeing;
import kib.lab6.common.entities.enums.Mood;

import java.io.Serializable;

/*TODO Может быть имеет смысл передавать не строку а число, раз уж храним не всегда нужное поле хумана,
    то можно хранить и не всегда нужные числовые, строковые аргументы, в этом есть плюс, так-как на сервере
    не будет использоваться parseInt и прочие функции*/

public class Request implements Serializable {

    private final String commandNameToSend;
    private HumanBeing humanToSend;
    private int numberArgumentToSend;
    private Mood moodArgumentToSend;

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
}
