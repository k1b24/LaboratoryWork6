package kib.lab6.common.util;


import kib.lab6.common.entities.HumanBeing;
import kib.lab6.common.util.TextSender;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public final class HumanValidator {

    private static final ValidatorFactory VALIDATOR_FACTORY = Validation.buildDefaultValidatorFactory();
    private static final Validator VALIDATOR = VALIDATOR_FACTORY.getValidator();

    private HumanValidator() {

    }

    public static boolean validateHuman(HumanBeing human) {
        Set<ConstraintViolation<HumanBeing>> validateResult = VALIDATOR.validate(human);
        if (validateResult.size() > 0) {
//            for (ConstraintViolation<HumanBeing> violation : validateResult) {
//                TextSender.printError(violation.getPropertyPath() + " " + violation.getMessage());
//            }
            return false;
        }
        return true;
    }

    public static <T> boolean validateField(T t, String fieldName) {
        Set<ConstraintViolation<T>> validateResult = VALIDATOR.validateProperty(t, fieldName);
        if (validateResult.size() > 0) {
//            for (ConstraintViolation<T> violation : validateResult) {
//                TextSender.printError(violation.getPropertyPath() + " " + violation.getMessage());
//            }
            return false;
        }
        return true;
    }
}
