package lotto.util;

public enum ErrorMessage {
    INVALID_INPUT("유효하지 않은 입력입니다. 다시 입력해주세요");;

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }
}
