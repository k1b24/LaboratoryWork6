package kib.lab6.common.abstractions;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Абстрактный класс, реализующий абстрактную логику парсинга текстового файла
 */
public abstract class AbstractFileReader {

    /**
     * Абстрактный метод инициализации файла
     * @param fileName имя файла
     * @throws FileNotFoundException
     */
    public abstract void initializeFile(String fileName) throws FileNotFoundException;

    /**
     * Абстрактный метод парсинга инициализированного файла
     */
    public abstract void parseFile();

    /**
     * Абстрактный метод получения массива значений из файла
     * @return ArrayList<?> массив значений полученных из файла
     */
    public abstract ArrayList<?> getInfoFromFile();

}
