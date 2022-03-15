package kib.lab6.client.utils;

import kib.lab6.client.entities.enums.Mood;
import kib.lab6.client.entities.enums.WeaponType;

import java.time.LocalDate;

public final class StringToTypeConverter {

    private StringToTypeConverter() {

    }
    //TODO Слишком много условий в ифах ахуеть как это фиксить????
    public static Object toObject(Class<?> requiredClass, String value) throws IllegalArgumentException {
        Object result = null;
        if (checkBoolean(requiredClass)) {
            result = Boolean.parseBoolean(value);
        } else if (checkByte(requiredClass)) {
            result = Byte.parseByte(value);
        } else if (checkShort(requiredClass)) {
            result = Short.parseShort(value);
        } else if (checkInteger(requiredClass)) {
            result = Integer.parseInt(value);
        } else if (checkLong(requiredClass)) {
            result = Long.parseLong(value);
        } else if (checkFloat(requiredClass)) {
            result = Float.parseFloat(value);
        } else if (checkDouble(requiredClass)) {
            result = Double.parseDouble(value);
        } else if (LocalDate.class == requiredClass) {
            result = LocalDate.parse(value);
        } else if (Mood.class == requiredClass) {
            result = Mood.valueOf(value.toUpperCase());
        } else if (WeaponType.class == requiredClass) {
            result = WeaponType.valueOf(value.toUpperCase());
        }
        return (result == null ? value : result);
    }

    private static boolean checkBoolean(Class<?> requiredClass) {
        return (Boolean.class == requiredClass || Boolean.TYPE == requiredClass);
    }

    private static boolean checkByte(Class<?> requiredClass) {
        return (Byte.class == requiredClass || Byte.TYPE == requiredClass);
    }

    private static boolean checkShort(Class<?> requiredClass) {
        return (Short.class == requiredClass || Short.TYPE == requiredClass);
    }

    private static boolean checkInteger(Class<?> requiredClass) {
        return (Integer.class == requiredClass || Integer.TYPE == requiredClass);
    }

    private static boolean checkLong(Class<?> requiredClass) {
        return (Long.class == requiredClass || Long.TYPE == requiredClass);
    }

    private static boolean checkFloat(Class<?> requiredClass) {
        return (Float.class == requiredClass || Float.TYPE == requiredClass);
    }

    private static boolean checkDouble(Class<?> requiredClass) {
        return (Double.class == requiredClass || Double.TYPE == requiredClass);
    }
}
