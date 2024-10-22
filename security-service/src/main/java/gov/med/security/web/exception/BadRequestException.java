package gov.med.security.web.exception;

import gov.med.security.web.utils.ErrorMessageConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
	@Serial
	private static final long serialVersionUID = 1L;

	public BadRequestException() {
		super(ErrorMessageConstants.BAD_REQUEST);
	}

	public BadRequestException(final String message) {
		super(message);
	}
}
