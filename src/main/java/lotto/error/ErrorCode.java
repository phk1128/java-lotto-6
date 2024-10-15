package lotto.error;

public enum ErrorCode {
	INVALID_NUMBERS_SIZE( "숫자는 6개만 가능합니다."),
	DUPLICATE_NUMBER("숫자는 중복되면 안됩니다."),
	INVALID_NUMBER_RANGE("숫자는 1 ~ 45 사이어야 합니다."),
	INVALID_NUMBER_UNIT("금액은 1000원 단위여야 합니다."),
	INVALID_INPUT("숫자만 입력 가능 합니다.");

	private static final String PREFIX = "[ERROR] ";
	private final String message;

	ErrorCode(final String message) {
		this.message = PREFIX + message;
	}

	public String getMessage() {
		return message;
	}
}
