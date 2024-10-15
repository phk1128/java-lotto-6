package lotto.error.exception;

import lotto.error.AppException;
import lotto.error.ErrorCode;

public class InvalidNumberException extends AppException {

	public InvalidNumberException(final ErrorCode errorCode) {
		super(errorCode);
	}
}
