package kib.lab6.common.util.console_workers;

import kib.lab6.common.entities.HumanBeing;
import kib.lab6.common.entities.enums.Mood;
import kib.lab6.common.entities.enums.WeaponType;
import kib.lab6.common.entities.HumanValidator;
import kib.lab6.common.util.StringToTypeConverter;

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
    private static final Scanner SCANNER = new Scanner(System.in);
    private final HumanBeing newHumanToInput;
    private final String name;
    private final String realHero;
    private final String hasToothpick;
    private final String impactSpeed;
    private final HumanValidator humanValidator;
    private final TextSender textSender;

    /**
     * Конструктор создающий человека информацию о котором мы хотим получить автоматически
     */
    public HumanInfoInput(String[] args, TextSender textSender) {
        this.name = args[NAME_ELEMENT_NUMBER];
        this.realHero = args[REAL_HERO_ELEMENT_NUMBER];
        this.hasToothpick = args[HAS_TOOTHPICK_ELEMENT_NUMBER];
        this.impactSpeed = args[IMPACT_SPEED_ELEMENT_NUMBER];
        this.humanValidator = new HumanValidator(textSender);
        this.textSender = textSender;
        newHumanToInput = new HumanBeing();
        setPrimitives();
    }

    private void inputName() throws IllegalArgumentException {
        newHumanToInput.setName(name);
        boolean validationResult = humanValidator.validateField(newHumanToInput, "name");
        if (!validationResult) {
            throw new IllegalArgumentException("Ошибка ввода имени человека");
        }
    }

    private void inputX() throws NumberFormatException {
        textSender.printMessage(new SuccessMessage("Введите X(целое число): "));
        String userInput = SCANNER.nextLine();
        try {
            newHumanToInput.getCoordinates().setX((Long) StringToTypeConverter.toObject(Long.class, userInput));
            boolean validationResult = humanValidator.validateField(newHumanToInput.getCoordinates(), "x");
            if (!validationResult) {
                inputX();
            }
        } catch (NumberFormatException e) {
            textSender.printMessage(new ErrorMessage("Ошибка ввода числа X, повторите ввод"));
            inputX();
        }
    }

    private void inputY() throws NumberFormatException {
        textSender.printMessage(new SuccessMessage("Введите Y(число с плавающей точкой): "));
        String userInput = SCANNER.nextLine();
        try {
            Float y = (Float) StringToTypeConverter.toObject(Float.class, userInput);
            if (y.equals(Float.NEGATIVE_INFINITY) || y.equals(Float.POSITIVE_INFINITY)) {
                textSender.printMessage(new ErrorMessage("Введено слишком большое"
                        + " число для этого формата, повторите ввод"));
                inputY();
            }
            newHumanToInput.getCoordinates().setY(y);
            boolean validationResult = humanValidator.validateField(newHumanToInput.getCoordinates(), "y");
            if (!validationResult) {
                inputY();
            }
        } catch (NumberFormatException e) {
            textSender.printMessage(new ErrorMessage("Ошибка ввода числа Y, повторите ввод"));
            inputY();
        }
    }

    private void inputRealHero() throws IllegalArgumentException {
        if ("true".equals(this.realHero)) {
            newHumanToInput.setRealHero(true);
        } else if ("false".equals(this.realHero)) {
            newHumanToInput.setRealHero(false);
        } else {
            throw new IllegalArgumentException("Ошибка ввода героизма человека");
        }
    }

    private void inputHasToothpick() throws IllegalArgumentException {
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
            boolean validationResult = humanValidator.validateField(newHumanToInput, "impactSpeed");
            if (!validationResult) {
                throw new IllegalArgumentException("Ошибка ввода скорости удара человека");
            }
        }
    }

    private void inputWeaponType() {
        textSender.printMessage(new SuccessMessage("Введите тип оружия из предложенных вариантов"
                + " или оставьте пустую строку, если оружия нет: "));
        textSender.printMessage(new SuccessMessage(Arrays.toString(WeaponType.values())));
        String userInput = SCANNER.nextLine();
        if ("".equals(userInput)) {
            newHumanToInput.setWeaponType(null);
        } else {
            try {
                newHumanToInput.setWeaponType((WeaponType) StringToTypeConverter.toObject(WeaponType.class, userInput));
            } catch (IllegalArgumentException e) {
                textSender.printMessage(new ErrorMessage("Ошибка ввода типа оружия, повторите ввод"));
                inputWeaponType();
            }
        }

    }

    private void inputMood() {
        textSender.printMessage(new SuccessMessage("Введите настроение из предложенных вариантов или оставьте пустую строку, если человек дед инсайд: "));
        textSender.printMessage(new SuccessMessage(Arrays.toString(Mood.values())));
        String userInput = SCANNER.nextLine();
        if ("".equals(userInput)) {
            newHumanToInput.setMood(null);
        } else {
            try {
                newHumanToInput.setMood((Mood) StringToTypeConverter.toObject(Mood.class, userInput));
            } catch (IllegalArgumentException e) {
                textSender.printMessage(new ErrorMessage("Ошибка ввода типа настроения, повторите ввод"));
                inputMood();
            }
        }
    }

    private void inputCarSpeed() {
        textSender.printMessage(new SuccessMessage("Введите скорость машины: "));
        String userInput = SCANNER.nextLine();
        try {
            newHumanToInput.getCar().setCarSpeed((Integer) StringToTypeConverter.toObject(Integer.class, userInput));
        } catch (NumberFormatException e) {
            textSender.printMessage(new ErrorMessage("Ошибка ввода скорости машины, повторите ввод"));
            inputCarSpeed();
        }
    }

    private void inputCarCoolness() {
        textSender.printMessage(new SuccessMessage("Машина крутая?[y/n]: "));
        String userInput = SCANNER.nextLine().toLowerCase();
        if ("y".equals(userInput)) {
            userInput = "true";
        } else if ("n".equals(userInput)) {
            userInput = "false";
        } else {
            textSender.printMessage(new ErrorMessage("Ошибка ввода крутости машины, повторите ввод"));
            inputCarCoolness();
        }
        newHumanToInput.getCar().setCarCoolness((Boolean) StringToTypeConverter.toObject(Boolean.class, userInput));
    }

    private void inputCar() {
        textSender.printMessage(new SuccessMessage("Есть ли у человека машина?[y/n]"));
        String userAnswer = SCANNER.nextLine();
        if ("y".equals(userAnswer)) {
            inputCarCoolness();
            inputCarSpeed();
        } else if ("n".equals(userAnswer)) {
            newHumanToInput.setCar(null);
        } else {
            textSender.printMessage(new ErrorMessage("Ошибка ввода, повторите ввод"));
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
        return newHumanToInput;
    }
}
