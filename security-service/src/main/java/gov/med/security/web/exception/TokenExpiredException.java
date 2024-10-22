package gov.med.security.web.exception;

import gov.med.security.web.utils.ErrorMessageConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class TokenExpiredException extends RuntimeException {
	public TokenExpiredException() {
		super(ErrorMessageConstants.TOKEN_EXPIRED);
	}

	public TokenExpiredException(final String message) {
		super(message);
	}
}
