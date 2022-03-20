package kib.lab6.client.user_command_line;

import kib.lab6.common.abstractions.AbstractFileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Класс, читающий скрипт из файла, наследует абстрактный класс AbstractFileReader
 */
public class ExecutableFileReader extends AbstractFileReader {

    private Scanner scannerOfFile;
    private final ArrayList<String> commands = new ArrayList<>();

    /**
     * Метод, инициализирующий файл для чтения, получающий в качестве параметра имя этого файла
     * @param fileName имя файла
     * @throws FileNotFoundException
     */
    @Override
    public void initializeFile(String fileName) throws FileNotFoundException {
        File infoFile = new File(fileName);
        scannerOfFile = new Scanner(infoFile);
    }

    /**
     * Метод, заполняющий массив элементов коллекции, читая информацию о них из файла
     */
    @Override
    public void parseFile() {
        while (scannerOfFile.hasNextLine()) {
            String line = scannerOfFile.nextLine();
            commands.add(line);
        }
    }

    /**
     * Метод, возвращающий массив прочитанных элементов коллекции из файла
     */
    @Override
    public ArrayList<String> getInfoFromFile() {
        return commands;
    }
}
