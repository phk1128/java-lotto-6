package lotto.error.exception;

import lotto.error.AppException;
import lotto.error.ErrorCode;

public class InvalidNumbersException extends AppException {

	public InvalidNumbersException(final ErrorCode errorCode) {
		super(errorCode);
	}
}
