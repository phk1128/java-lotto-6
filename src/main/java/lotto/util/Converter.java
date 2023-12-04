package lotto.util;

import static lotto.util.ErrorMessage.INVALID_INPUT;

import java.util.Arrays;
import java.util.List;

public class Converter {

    private Converter() {

    }

    public static int convertStringToInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(INVALID_INPUT.getMessage());
        }
    }

    public static List<Integer> convertStringListToIntegerList(List<String> strList) {
        return strList.stream()
                .map(Integer::parseInt)
                .toList();
    }

    public static List<String> convertStringToList(String str) {
        return Arrays.stream(str.split(","))
                .toList();
    }
}
