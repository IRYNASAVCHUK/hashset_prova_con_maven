package com.example.utils;

import java.util.Arrays;

public class CallerUtil {
    public static boolean isCalledFromMain() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        return Arrays.stream(stackTrace)
                // Ignora il primo elemento dello stack trace (quello relativo al metodo
                // isCalledFromMain() stesso)
                .skip(1)
                // Trova il primo elemento non relativo alla classe CallerUtil
                .filter(element -> !element.getClassName().equals(CallerUtil.class.getName()))
                // Ottieni il nome del metodo chiamante
                .map(StackTraceElement::getMethodName)
                // Trova il metodo chiamante main
                .anyMatch(name -> name.equals("main"));
    }
}
