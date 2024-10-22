package gov.med.security.web.exception;

import gov.med.security.web.utils.ErrorMessageConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
	@Serial
	private static final long serialVersionUID = 1L;

	public NotFoundException() {
		super(ErrorMessageConstants.NOT_FOUND);
	}

	public NotFoundException(final String message) {
		super(message);
	}
}
