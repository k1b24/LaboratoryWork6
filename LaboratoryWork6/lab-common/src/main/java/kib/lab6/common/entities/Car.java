package kib.lab6.common.entities;

import java.io.Serializable;

/**
 * Класс описывающий объект машина
 */
public class Car implements Serializable {

    private Boolean carCoolness; //Поле может быть null
    private int carSpeed;

    /**
     * @return Скорость машины
     */
    public int getCarSpeed() {
        return carSpeed;
    }

    /**
     * Метод, позволяющий задать скорость машины
     * @param newSpeed новая скорость в строковом формате
     */
    public void setCarSpeed(int newSpeed) {
        this.carSpeed = newSpeed;
    }

    /**
     * @return Крутость машины
     */
    public Boolean getCarCoolness() {
        return carCoolness;
    }

    /**
     * Метод, позволяющий задать крутость машины
     * @param newCoolness Новая крутость машины
     */
    public void setCarCoolness(Boolean newCoolness) {
        this.carCoolness = newCoolness;
    }

    @Override
    public String toString() {
        return "CAR COOLNESS: " + this.getCarCoolness() + " CAR SPEED: " + this.getCarSpeed();
    }
}
