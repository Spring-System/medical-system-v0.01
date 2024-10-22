package gov.med.security.web.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.experimental.SuperBuilder;

// Chỉ serialize các trường không null
@JsonInclude(JsonInclude.Include.NON_NULL)
//Các trường không có trong Java sẽ bị bỏ qua khi deserialize JSON
@JsonIgnoreProperties(ignoreUnknown = true)
@SuperBuilder
public abstract class AbstractBaseResponse {
}
