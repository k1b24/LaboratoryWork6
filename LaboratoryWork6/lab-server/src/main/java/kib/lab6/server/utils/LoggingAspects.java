package kib.lab6.server.utils;

import kib.lab6.common.util.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.Arrays;

@Aspect
public class LoggingAspects {

    private static final Logger LOGGER = LogManager.getLogger();

    @Before("execution(void launch*())")
    public void launchingMethodsAdvises(JoinPoint joinPoint) {
        LOGGER.info("Вызван метод: " + ((MethodSignature) joinPoint.getSignature()).getName());
    }

    @AfterReturning(pointcut = "execution(boolean fillCollection())", returning = "result")
    public void collectionFillingAdvise(Object result) {
        if ((Boolean) result) {
            LOGGER.info("Коллекция успешно заполнена из файла");
        } else {
            LOGGER.info("Коллекция не была заполнена, сервер не запустился");
        }

    }

    @AfterReturning(pointcut = "execution(kib.lab6.common.util.Request listen())", returning = "result")
    public void requestListeningAdvise(Object result) {
        if (result != null) {
            LOGGER.info("Получен запрос от " + ((Request) result).getClientInfo() + " --> " + ((Request) result).toString());
        }
    }

    @AfterReturning(pointcut = "execution(Object executeCommandFromRequest(kib.lab6.common.util.Request))", returning = "result")
    public void commandExecutionAdvise(JoinPoint joinPoint, Object result) {
        if (result != null) {
            LOGGER.info("Исполнена команда: "
                    + ((Request) joinPoint.getArgs()[0]).getCommandNameToSend());
        }
    }

    @AfterReturning(pointcut = "execution(String sendResponse(kib.lab6.common.util.Response))", returning = "result")
    public void sendResponseAdvise(JoinPoint joinPoint, Object result) {
        LOGGER.info("Ответ отправлен клиенту " + (String) result);
    }

    @AfterReturning(pointcut = "execution(public * CollectionManager.*(..))")
    public void collectionManagerAnyMethodAdvise(JoinPoint joinPoint) {
        LOGGER.info("Обращение к менеджеру коллекции: " + ((MethodSignature) joinPoint.getSignature()).getName()
                + " / Аргументы: " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterThrowing(pointcut = "execution(* *(..))", throwing = "ex")
    public void afterAnyThrowingAdvise(Throwable ex) {
        System.out.println("HUY");
    }
}
