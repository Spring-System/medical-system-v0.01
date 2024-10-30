package gov.med.service.web.utils;

import gov.med.service.web.entity.Patient;
import gov.med.service.web.entity.PatientDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class PatientDataValidator {
    private static final Logger logger = LoggerFactory.getLogger(PatientDataValidator.class);
    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = factory.getValidator();

    private PatientDataValidator() {}

    public static boolean patientDataValidate(Patient patient) {
        Set<ConstraintViolation<Patient>> violations = validator.validate(patient);

        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<Patient> violation : violations) {
                sb.append(violation.getMessage()).append("\n");
            }

            if (logger.isErrorEnabled()) {
                logger.error("Patient has invalid input field {}", sb);
            }

            return false;
        }

        return true;
    }

    public static boolean patientDTODataValidate(PatientDTO patientDTO) {
        Set<ConstraintViolation<PatientDTO>> violations = validator.validate(patientDTO);

        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<PatientDTO> violation : violations) {
                sb.append(violation.getMessage()).append("\n");
            }

            if (logger.isErrorEnabled()) {
                logger.error("PatientDTO has invalid input field {}", sb);
            }

            return false;
        }

        return true;
    }
}
