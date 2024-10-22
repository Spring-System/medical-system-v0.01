package gov.med.security.web.exception;

import gov.med.security.web.utils.ErrorMessageConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class RefreshTokenExpiredException extends TokenExpiredException {
	public RefreshTokenExpiredException() {
		super(ErrorMessageConstants.REFRESH_TOKEN_EXPIRED);
	}

	public RefreshTokenExpiredException(final String message) {
		super(message);
	}
}
