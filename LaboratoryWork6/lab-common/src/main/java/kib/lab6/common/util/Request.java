package kib.lab6.common.util;

import kib.lab6.common.entities.HumanBeing;

import java.io.Serializable;

/*TODO Может быть имеет смысл передавать не строку а число, раз уж храним не всегда нужное поле хумана,
    то можно хранить и не всегда нужные числовые, строковые аргументы, в этом есть плюс, так-как на сервере
    не будет использоваться parseInt и прочие функции*/

public class Request implements Serializable {

    String commandNameToSend;
    String commandArgumentToSend;
    HumanBeing humanToSend;

    public Request(String name, String argument) {
        this.commandNameToSend = name;
        this.commandArgumentToSend = argument;
    }

    public Request(String name, HumanBeing human) {
        this.commandNameToSend = name;
        this.humanToSend = human;
    }

    public Request(String name, String argument, HumanBeing human) {
        this.commandNameToSend = name;
        this.commandArgumentToSend = argument;
        this.humanToSend = human;
    }

    public String getCommandNameToSend() {
        return commandNameToSend;
    }

    public String getCommandArgumentToSend() {
        return commandArgumentToSend;
    }

    public HumanBeing getHumanToSend() {
        return humanToSend;
    }
}
