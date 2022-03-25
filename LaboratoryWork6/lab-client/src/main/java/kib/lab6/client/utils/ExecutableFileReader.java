package kib.lab6.client.utils;

import kib.lab6.common.InputedCommand;
import kib.lab6.common.abstractions.AbstractFileReader;
import kib.lab6.common.util.SmartSplitter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ExecutableFileReader extends AbstractFileReader {

    private Scanner scannerOfFile;
    private final ArrayList<InputedCommand> commandsFromFile = new ArrayList<>();

    @Override
    public void initializeFile(String fileName) throws FileNotFoundException {
        File infoFile = new File(fileName);
        scannerOfFile = new Scanner(infoFile);
    }

    @Override
    public void parseFile() {
        while (scannerOfFile.hasNext()) {
            String line = scannerOfFile.nextLine();
            String[] inputString = SmartSplitter.smartSplit(line).toArray(new String[0]);
            String commandName = inputString[0].toLowerCase();
            String[] commandArgs = Arrays.copyOfRange(inputString, 1, inputString.length);
            commandsFromFile.add(new InputedCommand(commandName, commandArgs));
        }
    }

    @Override
    public ArrayList<InputedCommand> getInfoFromFile() {
        return commandsFromFile;
    }
}
