package lotto.error;

public abstract class AppException extends IllegalArgumentException {

	protected AppException(final ErrorCode errorCode) {
		super(errorCode.getMessage());
	}

}
