package lotto.error.exception;

import lotto.error.AppException;
import lotto.error.ErrorCode;

public class InvalidInputException extends AppException {
	public InvalidInputException(final ErrorCode errorCode) {
		super(errorCode);
	}
}
