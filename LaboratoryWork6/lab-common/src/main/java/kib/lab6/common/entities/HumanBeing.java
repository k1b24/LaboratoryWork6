package kib.lab6.common.entities;

import kib.lab6.common.entities.enums.Mood;
import kib.lab6.common.entities.enums.WeaponType;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDate;

/**
 *  Класс человек, коллекцией экземпляров которого управляет коллекция и пользователь в интерактивном режиме
 */
public class HumanBeing implements Comparable<HumanBeing>, Serializable {
    //TODO Human being лежит в Common и статичные переменные для экземпляров имеют свои значения на клиенте и на
    // сервере если добавляю с клиента хумана ему дается 1, а не количество хуманов + 1
    private static final int MAX_IMPACT_SPEED_VALUE = 712;
    private long id;
    @Pattern(regexp = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$")
    @NotNull
    @NotEmpty
    private String name;
    @Valid
    @NotNull
    private Coordinates coordinates = new Coordinates();
    @NotNull
    private LocalDate creationDate;
    @NotNull
    private Boolean realHero;
    private boolean hasToothpick;
    @Max(MAX_IMPACT_SPEED_VALUE)
    private Integer impactSpeed;
    private WeaponType weaponType;
    private Mood mood;
    private Car car = new Car();

    /**
     * Конструктор класса
     */
    public HumanBeing() {
        creationDate = LocalDate.now();
    }

    /**
     * @return ID человека
     */
    public Long getId() {
        return id;
    }

    /**
     * Метод, устанавливающий ID в зависимости от idCounter
     */
    public void setId(int idToSet) {
        this.id = idToSet;
    }

    /**
     * @return имя человека
     */
    public String getName() {
        return name;
    }

    /**
     * Метод, позволяющий задать имя человека
     * @param name значение имени
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return координаты человека
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Метод, позволяющий задать значение координат человека
     * @param coordinates значение координат
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * @return дата создания человека
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * Метод, позволяющий задать дату создания координат человека
     * @param creationDate дата создания
     */
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return героизм человека
     */
    public Boolean isRealHero() {
        return realHero;
    }

    /**
     * Метод, позволяющий задать героизм человека
     * @param realHero героизм человека в строковом формате
     */
    public void setRealHero(Boolean realHero) {
        this.realHero = realHero;
    }

    /**
     * @return наличие зубочистки у человека
     */
    public boolean isHasToothpick() {
        return hasToothpick;
    }

    /**
     * Метод, позволяющий задать наличие зубочистки у человека
     * @param hasToothpick нализчие зубочистки у человека в строковом формате
     */
    public void setHasToothpick(boolean hasToothpick) {
        this.hasToothpick = hasToothpick;
    }

    /**
     * @return скорость удара человека
     */
    public Integer getImpactSpeed() {
        return impactSpeed;
    }

    /**
     * Метод, позволяющий задать значение координат человека
     * @param impactSpeed скорость удара в строковом формате
     */
    public void setImpactSpeed(Integer impactSpeed) {
        this.impactSpeed = impactSpeed;
    }

    /**
     * @return тип оружия человека
     */
    public WeaponType getWeaponType() {
        return weaponType;
    }

    /**
     * Метод, позволяющий задать тип оружия человека
     * @param weaponType тип оружия в строковом формате
     */
    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    /**
     * @return настроение человека
     */
    public Mood getMood() {
        return mood;
    }

    /**
     * Метод, позволяющий задать настроение человека
     * @param mood настроение в строковом формате
     */
    public void setMood(Mood mood) {
        this.mood = mood;
    }

    /**
     * @return машина человека
     */
    public Car getCar() {
        return car;
    }

    /**
     * Метод, позволяющий задать машину человека
     * @param car значение машины
     */
    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public int compareTo(HumanBeing o) {
        if (o == null) {
            return 1;
        }
        int result = this.getName().compareTo(o.getName());
        if (result == 0) {
            result = this.getImpactSpeed().compareTo(o.getImpactSpeed());
        }
        return result;
    }

    @Override
    public String toString() {
        return id + ". " + name + ", X: "
                + coordinates.getX() + ", Y: " + coordinates.getY()
                + ", CREATION DATE: " + creationDate + ", REAL HERO: " + realHero
                + ", HAS TOOTHPICK: " + hasToothpick + ", IMPACT SPEED: " + impactSpeed
                + ", WEAPON TYPE: " + weaponType + ", MOOD: " + mood + ", CAR INFO: " + (car == null ? "no car" : car.toString());
    }
}
