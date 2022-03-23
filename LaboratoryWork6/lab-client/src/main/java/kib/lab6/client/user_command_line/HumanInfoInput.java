package kib.lab6.client.user_command_line;

import kib.lab6.client.Config;
import kib.lab6.common.entities.HumanBeing;
import kib.lab6.common.entities.enums.Mood;
import kib.lab6.common.entities.enums.WeaponType;
import kib.lab6.common.util.ErrorMessage;
import kib.lab6.common.util.HumanValidator;
import kib.lab6.common.util.StringToTypeConverter;
import kib.lab6.common.util.SuccessMessage;
import kib.lab6.common.util.TextSender;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Класс, считывающий информации о человеке в интерактивном режиме
 */
public class HumanInfoInput {

    private static final int NAME_ELEMENT_NUMBER = 0;
    private static final int REAL_HERO_ELEMENT_NUMBER = 1;
    private static final int HAS_TOOTHPICK_ELEMENT_NUMBER = 2;
    private static final int IMPACT_SPEED_ELEMENT_NUMBER = 3;
    private static Scanner scanner = new Scanner(System.in);
    private final HumanBeing newHumanToInput;
    private final String name;
    private final String realHero;
    private final String hasToothpick;
    private final String impactSpeed;

    /**
     * Конструктор создающий человека информацию о котором мы хотим получить автоматически
     */
    public HumanInfoInput(String[] args) {
        this.name = args[NAME_ELEMENT_NUMBER];
        this.realHero = args[REAL_HERO_ELEMENT_NUMBER];
        this.hasToothpick = args[HAS_TOOTHPICK_ELEMENT_NUMBER];
        this.impactSpeed = args[IMPACT_SPEED_ELEMENT_NUMBER];
        newHumanToInput = new HumanBeing(false);
        setPrimitives();
    }

    /**
     * Конструктор принимающий человека информацию о котором мы хотим изменить
     * @param newHumanToInput человек информацию о котором мы хотим изменить
     */
    public HumanInfoInput(HumanBeing newHumanToInput, String[] args) {
        this.name = args[NAME_ELEMENT_NUMBER];
        this.realHero = args[REAL_HERO_ELEMENT_NUMBER];
        this.hasToothpick = args[HAS_TOOTHPICK_ELEMENT_NUMBER];
        this.impactSpeed = args[IMPACT_SPEED_ELEMENT_NUMBER];
        this.newHumanToInput = newHumanToInput;
        setPrimitives();
    }

    private void inputName() throws IllegalArgumentException {
        newHumanToInput.setName(name);
        boolean validationResult = Config.getHumanValidator().validateField(newHumanToInput, "name");
        if (!validationResult) {
            throw new IllegalArgumentException("Ошибка ввода имени человека");
        }
    }

    private void inputX() throws NumberFormatException {
        Config.getTextSender().printMessage(new SuccessMessage("Введите X(целое число): "));
        String userInput = scanner.nextLine();
        try {
            newHumanToInput.getCoordinates().setX((Long) StringToTypeConverter.toObject(Long.class, userInput));
            boolean validationResult = Config.getHumanValidator().validateField(newHumanToInput.getCoordinates(), "x");
            if (!validationResult) {
                inputX();
            }
        } catch (NumberFormatException e) {
            Config.getTextSender().printMessage(new ErrorMessage("Ошибка ввода числа X, повторите ввод"));
            inputX();
        }
    }

    private void inputY() throws NumberFormatException {
        Config.getTextSender().printMessage(new SuccessMessage("Введите Y(число с плавающей точкой): "));
        String userInput = scanner.nextLine();
        try {
            Float y = (Float) StringToTypeConverter.toObject(Float.class, userInput);
            if (y.equals(Float.NEGATIVE_INFINITY) || y.equals(Float.POSITIVE_INFINITY)) {
                Config.getTextSender().printMessage(new ErrorMessage("Введено слишком большое"
                        + " число для этого формата, повторите ввод"));
                inputY();
            }
            newHumanToInput.getCoordinates().setY(y);
            boolean validationResult = Config.getHumanValidator().validateField(newHumanToInput.getCoordinates(), "y");
            if (!validationResult) {
                inputY();
            }
        } catch (NumberFormatException e) {
            Config.getTextSender().printMessage(new ErrorMessage("Ошибка ввода числа Y, повторите ввод"));
            inputY();
        }
    }

    private void inputRealHero() throws IllegalArgumentException {
        boolean realHeroValue;
        if ("true".equals(this.realHero)) {
            newHumanToInput.setRealHero(true);
        } else if ("false".equals(this.realHero)) {
            newHumanToInput.setRealHero(false);
        } else {
            throw new IllegalArgumentException("Ошибка ввода героизма человека");
        }
    }

    private void inputHasToothpick() throws IllegalArgumentException {
        boolean hasToothpickValue;
        if ("true".equals(this.hasToothpick)) {
            newHumanToInput.setHasToothpick(true);
        } else if ("false".equals(this.hasToothpick)) {
            newHumanToInput.setHasToothpick(false);
        } else {
            throw new IllegalArgumentException("Ошибка ввода наличия у человека зубочистки");
        }
    }

    private void inputImpactSpeed() throws IllegalArgumentException {
        if ("".equals(this.impactSpeed)) {
            newHumanToInput.setImpactSpeed(null);
        } else {
            newHumanToInput.setImpactSpeed((Integer) StringToTypeConverter.toObject(Integer.class, this.impactSpeed));
            boolean validationResult = Config.getHumanValidator().validateField(newHumanToInput, "impactSpeed");
            if (!validationResult) {
                throw new IllegalArgumentException("Ошибка ввода скорости удара человека");
            }
        }
    }

    private void inputWeaponType() {
        Config.getTextSender().printMessage(new SuccessMessage("Введите тип оружия из предложенных вариантов"
                + " или оставьте пустую строку, если оружия нет: "));
        Config.getTextSender().printMessage(new SuccessMessage(Arrays.toString(WeaponType.values())));
        String userInput = scanner.nextLine();
        if ("".equals(userInput)) {
            newHumanToInput.setWeaponType(null);
        } else {
            try {
                newHumanToInput.setWeaponType((WeaponType) StringToTypeConverter.toObject(WeaponType.class, userInput));
            } catch (IllegalArgumentException e) {
                Config.getTextSender().printMessage(new ErrorMessage("Ошибка ввода типа оружия, повторите ввод"));
                inputWeaponType();
            }
        }

    }

    private void inputMood() {
        Config.getTextSender().printMessage(new SuccessMessage("Введите настроение из предложенных вариантов или оставьте пустую строку, если человек дед инсайд: "));
        Config.getTextSender().printMessage(new SuccessMessage(Arrays.toString(Mood.values())));
        String userInput = scanner.nextLine();
        if ("".equals(userInput)) {
            newHumanToInput.setMood(null);
        } else {
            try {
                newHumanToInput.setMood((Mood) StringToTypeConverter.toObject(Mood.class, userInput));
            } catch (IllegalArgumentException e) {
                Config.getTextSender().printMessage(new ErrorMessage("Ошибка ввода типа настроения, повторите ввод"));
                inputWeaponType();
            }
        }
    }

    private void inputCarSpeed() {
        Config.getTextSender().printMessage(new SuccessMessage("Введите скорость машины: "));
        String userInput = scanner.nextLine();
        try {
            newHumanToInput.getCar().setCarSpeed((Integer) StringToTypeConverter.toObject(Integer.class, userInput));
        } catch (NumberFormatException e) {
            Config.getTextSender().printMessage(new ErrorMessage("Ошибка ввода скорости машины, повторите ввод"));
            inputCarSpeed();
        }
    }

    private void inputCarCoolness() {
        Config.getTextSender().printMessage(new SuccessMessage("Машина крутая?[y/n]: "));
        String userInput = scanner.nextLine().toLowerCase();
        if ("y".equals(userInput)) {
            userInput = "true";
        } else if ("n".equals(userInput)) {
            userInput = "false";
        } else {
            Config.getTextSender().printMessage(new ErrorMessage("Ошибка ввода крутости машины, повторите ввод"));
            inputCarCoolness();
        }
        newHumanToInput.getCar().setCarCoolness((Boolean) StringToTypeConverter.toObject(Boolean.class, userInput));
    }

    private void inputCar() {
        Config.getTextSender().printMessage(new SuccessMessage("Есть ли у человека машина?[y/n]"));
        String userAnswer = scanner.nextLine();
        if ("y".equals(userAnswer)) {
            inputCarCoolness();
            inputCarSpeed();
        } else if ("n".equals(userAnswer)) {
            newHumanToInput.setCar(null);
        } else {
            Config.getTextSender().printMessage(new ErrorMessage("Ошибка ввода, повторите ввод"));
            inputCar();
        }
    }

    /**
     * метод отвечающий за присвоение примитивов человеку
     */
    public void setPrimitives() {
        inputName();
        inputRealHero();
        inputHasToothpick();
        inputImpactSpeed();
    }

    /**
     * метод отвечающий за ввод не примитивных типов данных человека
     */
    public void inputHuman() {
        inputImpactSpeed();
        inputX();
        inputY();
        inputWeaponType();
        inputMood();
        inputCar();
    }

    /**
     * @return введенный пользователем человек
     */
    public HumanBeing getNewHumanToInput() {
        newHumanToInput.setId();
        return newHumanToInput;
    }
}
