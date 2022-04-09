package kib.lab6.server.utils;

import kib.lab6.common.entities.HumanBeing;
import kib.lab6.common.entities.enums.Mood;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * Класс для работы с коллекцией экземпляров HumanBeing
 */
public class CollectionManager {

    private static int humanIdCounter = 1;
    private final PriorityQueue<HumanBeing> humanQueue = new PriorityQueue<>();
    private final LocalDate initializationDate;

    public CollectionManager() {
        initializationDate = LocalDate.now();
    }

    public ArrayList<HumanBeing> getSortedArrayListFromQueue() {
        return (ArrayList<HumanBeing>) humanQueue.stream().sorted().collect(Collectors.toList());
    }

    public void addHuman(HumanBeing human) { //stream api
        human.setId(humanIdCounter++);
        humanQueue.add(human);
    }

    public void removeHumanById(int id) {
        humanQueue.removeIf(human -> human.getId() == id);
    }

    public void removeHumanByAnyMood(Mood mood) {
        humanQueue.removeIf(human -> human.getMood() == mood);
    }

    public void setHumanById(int id, HumanBeing humanToSet) throws IllegalArgumentException {
        if (humanQueue.removeIf(human ->  human.getId() == id)) {
            humanToSet.setId(id);
            humanQueue.add(humanToSet);
        } else {
            throw new IllegalArgumentException("Человек с таким ID не найден");
        }
    }

    public String getStringForShowing() {
        ArrayList<HumanBeing> listToShow = getSortedArrayListFromQueue();
        return listToShow.stream()
                .map(HumanBeing::toString)
                .collect(Collectors.joining("\n"));
    }

    public void fillWithArray(ArrayList<HumanBeing> arrayOfPeople) {
        arrayOfPeople.forEach(human -> {
            human.setId(humanIdCounter++);
            humanQueue.add(human);
        });
    }

    public HumanBeing returnHead() {
        return humanQueue.peek();
    }

    public ArrayList<HumanBeing> returnDescending() {
        ArrayList<HumanBeing> descendingList = getSortedArrayListFromQueue();
        Collections.reverse(descendingList);
        return descendingList;
    }

    public ArrayList<Long> getIDs() {
        return (ArrayList<Long>) humanQueue.stream().map(HumanBeing::getId).collect(Collectors.toList());
    }

    public ArrayList<HumanBeing> filterByCarSpeed(int speed) {
        return (ArrayList<HumanBeing>) humanQueue.stream().filter(human -> (human.getCar() != null && human.getCar().getCarSpeed() < speed)).collect(Collectors.toList());
    }

    public boolean addIfMin(HumanBeing newHuman) {
        for (HumanBeing human : humanQueue) {
            if (newHuman.compareTo(human) > 0) {
                return false;
            }
        }
        addHuman(newHuman);
        return true;
    }

    public void clearCollection() {
        humanQueue.clear();
    }

    public String getInfoAboutCollection() {
        return "Информация о коллекции. Тип: " + humanQueue.getClass() + " Дата инициализации: "
                + initializationDate.toString() + " Количество элементов: " + humanQueue.size();
    }

    public List<String> getArrayOfInfoForSavingToCSV() {
        ArrayList<String> arrayOfInfo = new ArrayList<>();
        for (HumanBeing human : humanQueue) {
            String humanInfo = human.getName() + "," + human.getCoordinates().getX() + ","
                    + human.getCoordinates().getY() + "," + human.getCreationDate().toString() + ","
                    + human.isRealHero() + "," + human.isHasToothpick() + ","
                    + (human.getImpactSpeed() == null ? "null," : human.getImpactSpeed() + ",")
                    + (human.getWeaponType() == null ? "null," : human.getWeaponType() + ",")
                    + (human.getMood() == null ? "null," : human.getMood() + ",")  + (human.getCar() == null ? "," : (human.getCar().getCarCoolness() == null ? "null," : human.getCar().getCarCoolness()) + "," + human.getCar().getCarSpeed());
            arrayOfInfo.add(humanInfo);
        }
        return arrayOfInfo;
    }
}
