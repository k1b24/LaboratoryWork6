package kib.lab6.server.csv_parser;

import kib.lab6.common.util.console_workers.ErrorMessage;
import kib.lab6.server.utils.Config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Класс, позволяющий сохранить информацию о коллекции в файл в формате CSV
 */
public class CSVSaver {

    private final String fileName;
    private String firstLineOfFile;

    /**
     * Конструктор класса, задающий поле класса fileName
     * @param fileName
     */
    public CSVSaver(String fileName) {
        this.fileName = fileName;
    }

    private void initializeFile() throws FileNotFoundException {
        File infoFile = new File(fileName);
        Scanner scannerOfFile = new Scanner(infoFile);
        firstLineOfFile = scannerOfFile.nextLine();
    }

    /**
     * Метод, сохраняющий значения всех элементов коллекции в файл
     * @param infoStrings
     * @throws IOException
     */
    public void saveToFile(List<String> infoStrings) throws IOException {
        initializeFile();
        try (FileWriter fw = new FileWriter(fileName);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(firstLineOfFile + "\n");
            for (String infoString : infoStrings) {
                bw.write(infoString + "\n");
            }
        } catch (IOException e) {
            Config.getTextSender().printMessage(new ErrorMessage("Ошибка при работе с файлом"));
        }
    }
}
