package kib.lab6.common.entities;

import javax.validation.constraints.Min;

/**
 * Класс описывающий объект координаты
 */
public class Coordinates {

    private static final int MIN_X_VALUE = -759;
    @Min(MIN_X_VALUE)
    private long x; //Значение поля должно быть больше -759
    private float y;

    /**
     * @return координата по X
     */
    public long getX() {
        return this.x;
    }

    /**
     * Метод, позволяющий задать координату по X
     * @param x значение координаты по X в строковом формате
     */
    public void setX(long x) {
        this.x = x;
    }

    /**
     * @return координата по Y
     */
    public float getY() {
        return this.y;
    }

    /**
     * Метод, позволяющий задать координату по Y
     * @param y значение координаты по X
     */
    public void setY(float y) {
        this.y = y;
    }
}
