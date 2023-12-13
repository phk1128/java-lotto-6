package lotto.constant;

public enum ErrorMessage {

    INVALID_MONEY("금액은 1000원 단위의 숫자여야 합니다."),
    INVALID_NUMBER("각 번호는 1~45사이의 숫자여야 합니다."),
    DUPLICATE_WINNING_NUMBER("당첨 번호는 서로 중복될 수 없습니다."),
    DUPLICATE_BONUS_NUMBER("보너스 번호는 당첨번호와 중복될  수 없습니다.");

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }
}
