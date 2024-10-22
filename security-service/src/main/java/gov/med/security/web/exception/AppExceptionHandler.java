package gov.med.security.web.exception;

import gov.med.security.web.dto.response.DetailErrorResponse;
import gov.med.security.web.dto.response.ErrorResponse;
import gov.med.security.web.service.MessageSourceService;
import gov.med.security.web.utils.ErrorMessageConstants;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class AppExceptionHandler {
	private final MessageSourceService messageSourceService;

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public final ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupported(
			HttpServletRequest request,
			final HttpRequestMethodNotSupportedException e) {
		log.error(e.toString(), e.getMessage());
		return build(HttpStatus.METHOD_NOT_ALLOWED, messageSourceService.get(
				ErrorMessageConstants.METHOD_NOT_SUPPORT,
				getLocale(request)));
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public final ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(HttpServletRequest request,
	                                                                        final HttpMessageNotReadableException e) {
		log.error(e.toString(), e.getMessage());
		return build(HttpStatus.BAD_REQUEST, messageSourceService.get(
				ErrorMessageConstants.MALFORMED_JSON_REQUEST,
				getLocale(request)));
	}

	@ExceptionHandler(BindException.class)
	public final ResponseEntity<ErrorResponse> handleBindException(HttpServletRequest request,
	                                                               final BindException e) {
		log.error(e.toString(), e.getMessage());
		Map<String, String> errors = new HashMap<>();

		e.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});

		return build(HttpStatus.UNPROCESSABLE_ENTITY, messageSourceService.get(
				ErrorMessageConstants.BINDING_VALIDATION,
				getLocale(request)
				), errors);
	}

	@ExceptionHandler({
			TokenExpiredException.class,
			RefreshTokenExpiredException.class,
	})
	public final ResponseEntity<ErrorResponse> handleTokenExpiredRequestException(
			final TokenExpiredException e) {
		log.error(e.toString(), e.getMessage());
		return build(HttpStatus.UNAUTHORIZED, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
	}

	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<ErrorResponse> handleNotFoundException(final NotFoundException e) {
		log.error(e.toString(), e.getMessage());
		return build(HttpStatus.NOT_FOUND, e.getMessage());
	}

	/**
	 * Build error response.
	 *
	 * @param httpStatus HttpStatus enum to response status field
	 * @param message    String for response message field
	 * @param errors     Map for response errors field
	 * @return ResponseEntity
	 */
	private ResponseEntity<ErrorResponse> build(final HttpStatus httpStatus,
	                                            final String message,
	                                            final Map<String, String> errors) {
		if (!errors.isEmpty()) {
			return ResponseEntity.status(httpStatus).body(DetailErrorResponse.builder()
					.message(message)
					.items(errors)
					.build());
		}

		return ResponseEntity.status(httpStatus).body(ErrorResponse.builder()
				.message(message)
				.build());
	}

	/**
	 * Build error response.
	 *
	 * @param httpStatus HttpStatus enum to response status field
	 * @param message    String for response message field
	 * @return ResponseEntity
	 */
	private ResponseEntity<ErrorResponse> build(final HttpStatus httpStatus, final String message) {
		return build(httpStatus, message, new HashMap<>());
	}

	private Locale getLocale(HttpServletRequest request) {
		return RequestContextUtils.getLocale(request);
	}
}
