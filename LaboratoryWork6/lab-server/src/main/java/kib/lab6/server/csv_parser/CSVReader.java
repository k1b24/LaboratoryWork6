package kib.lab6.server.csv_parser;

import kib.lab6.common.abstractions.AbstractFileReader;
import kib.lab6.common.entities.HumanBeing;
import kib.lab6.common.util.console_workers.ErrorMessage;
import kib.lab6.common.util.StringToTypeConverter;
import kib.lab6.server.utils.Config;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Класс, реализующий чтение данных из CSV файла, наследует абстрактный класс AbstractFileReader
 */
public class CSVReader extends AbstractFileReader {

    private Scanner scannerOfFile;
    private String[] parameters;
    private final ArrayList<String> peopleStrings = new ArrayList<>();
    private final ArrayList<HashMap<String, String>> peopleInfo = new ArrayList<>();
    private final ArrayList<HumanBeing> humanArray = new ArrayList<>();
    private final Field[] humanBeingFields;

    /**
     * Конструктор класса CSVReader, при инициализации с помощью рефлексии задает значение humanBeingFields
     */
    public CSVReader() {
        humanBeingFields = HumanBeing.class.getDeclaredFields();
    }

    /**
     * Метод, возвращающий массив прочитанных элементов коллекции из файла
     */
    @Override
    public ArrayList<HumanBeing> getInfoFromFile() {
        return humanArray;
    }

    /**
     * Метод, заполняющий массив элементов коллекции, читая информацию о них из файла
     */
    @Override
    public void parseFile() {
        readPeople();
        for (HashMap<String, String> humanInfo : peopleInfo) {
            HumanBeing newHuman = createHuman(humanInfo);
            if (Config.getHumanValidator().validateHuman(newHuman)) {
                humanArray.add(newHuman);
            } else {
                Config.getTextSender().printMessage(new ErrorMessage("Ошибка при валидации данных, прочитанных из файла"));
                System.exit(2);
            }
        }
    }

    /**
     * Метод, инициализирующий файл для чтения, получающий в качестве параметра имя этого файла
     *
     * @param fileName имя файла
     * @throws FileNotFoundException
     */
    @Override
    public void initializeFile(String fileName) throws FileNotFoundException {
        File infoFile = new File(fileName);
        scannerOfFile = new Scanner(infoFile);
    }

    private HumanBeing createHuman(HashMap<String, String> humanInfo) {
        HumanBeing newHuman = new HumanBeing();
        for (Map.Entry<String, String> element : humanInfo.entrySet()) {
            for (Field field : humanBeingFields) {
                Class<?> cl = field.getType();
                try {
                    if (field.getName().equals(element.getKey())) {
                        Method setter = HumanBeing.class.getDeclaredMethod("set"
                                + field.getName().substring(0, 1).toUpperCase()
                                + field.getName().substring(1), field.getType());
                        setter.invoke(newHuman, ("null".equals(element.getValue()) ? null : StringToTypeConverter.toObject(field.getType(), element.getValue())));
                    } else {
                        Field[] innerFields = cl.getDeclaredFields();
                        for (Field innerField : innerFields) {
                            if (innerField.getName().equals(element.getKey())) {
                                Method innerSetter = cl.getDeclaredMethod("set"
                                        + innerField.getName().substring(0, 1).toUpperCase()
                                        + innerField.getName().substring(1), innerField.getType());
                                Method getter = HumanBeing.class.getDeclaredMethod("get"
                                        + cl.getSimpleName().substring(0, 1).toUpperCase()
                                        + cl.getSimpleName().substring(1));
                                Method outerSetter = HumanBeing.class.getDeclaredMethod("set"
                                        + cl.getSimpleName().substring(0, 1).toUpperCase()
                                        + cl.getSimpleName().substring(1), cl);
                                if ("".equals(element.getValue())) {
                                    outerSetter.invoke(newHuman, (Object) null);
                                } else if (getter.invoke(newHuman) != null) {
                                    innerSetter.invoke(getter.invoke(newHuman), ("null".equals(element.getValue()) ? null : StringToTypeConverter.toObject(innerField.getType(), element.getValue())));
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    Config.getTextSender().printMessage(new ErrorMessage("Ошибка при чтении файла"));
                    System.exit(2);
                }
            }
        }
        return newHuman;
    }

    private void readStringsFromFile() {
        ArrayList<String> stringsFromFile = new ArrayList<>();
        while (scannerOfFile.hasNextLine()) {
            stringsFromFile.add(scannerOfFile.nextLine());
        }
        parameters = stringsFromFile.get(0).split(",");
        for (int i = 1; i < stringsFromFile.size(); i++) {
            peopleStrings.add(stringsFromFile.get(i));
        }
    }

    private void readPeople() {
        readStringsFromFile();
        for (String peopleString : peopleStrings) {
            HashMap<String, String> newHuman = new HashMap<>();
            String[] humanInfo = peopleString.split(",", -1);
            for (int j = 0; j < parameters.length; j++) {
                newHuman.put(parameters[j], humanInfo[j]);
            }
            peopleInfo.add(newHuman);
        }
    }
}
