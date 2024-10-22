package gov.med.security.web.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@Getter
@Setter
@SuperBuilder
public class DetailErrorResponse extends ErrorResponse {
	private Map<String, String> items;
}
