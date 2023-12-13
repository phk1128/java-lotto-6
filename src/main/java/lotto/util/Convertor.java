package lotto.util;

import java.util.Arrays;
import java.util.List;
import lotto.constant.ErrorMessage;

public class Convertor {

    private Convertor() {

    }

    public static int convertToInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER.getMessage());
        }
    }

    public static List<String> convertToStringList(String input) {
        return Arrays.stream(input.split(","))
                .toList();
    }

    public static List<Integer> convertToIntegerList(List<String> strList) {
        return strList.stream()
                .map(Convertor::convertToInt)
                .toList();
    }

}
