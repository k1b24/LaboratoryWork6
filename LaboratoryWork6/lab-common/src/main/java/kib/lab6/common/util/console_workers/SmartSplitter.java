package kib.lab6.common.util.console_workers;

import java.util.ArrayList;

/**
 * Утилитарный класс для парсинга команды, введенной пользователем
 */
public class SmartSplitter {

    /**
     * Метод класса отвечающий за парсинг команды введенной пользователем
     * @param line строка для парсинга
     * @return массив содержащий разбитую по пробелам и кавычкам команду
     */
    public ArrayList<String> smartSplit(String line) {
        ArrayList<String> splittedLine = new ArrayList<>();
        StringBuilder currentString = new StringBuilder();
        boolean screeningStarted = false;
        for (char ch : line.toCharArray()) {
            if (ch == ' ' && !screeningStarted) {
                splittedLine.add(currentString.toString());
                currentString.setLength(0);
            } else if (ch == '"') {
                screeningStarted = !screeningStarted;
            } else {
                currentString.append(ch);
            }
        }
        splittedLine.add(currentString.toString());
        return splittedLine;
    }

}

