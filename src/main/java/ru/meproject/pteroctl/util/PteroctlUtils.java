package ru.meproject.pteroctl.util;

public class PteroctlUtils {

    public static String extractParent(String[] arrayedPath) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < arrayedPath.length - 1; i++) {
            stringBuilder.append(arrayedPath[i]);
            if (i < arrayedPath.length - 2) {
                stringBuilder.append("/");
            }
        }
        return stringBuilder.toString();
    }

    public static String extractChild(String[] arrayedPath) {
        return arrayedPath[arrayedPath.length-1];
    }
}
