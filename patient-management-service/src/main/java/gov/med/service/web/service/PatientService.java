package gov.med.service.web.service;

import gov.med.service.web.entity.Patient;
import gov.med.service.web.entity.PatientDTO;
import gov.med.service.web.repository.PatientRepository;
import gov.med.service.web.utils.PatientConverter;
import gov.med.service.web.utils.PatientDataValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PatientService {
    private final PatientRepository repository;

    public void createNewPatient(final PatientDTO patientDTO) {
        Patient patient = PatientConverter.patientDTOToPatient(patientDTO);
        savePatient(patient);
    }

    public Patient findPatientById(final String id) {
        return repository.findById(id).orElse(null);
    }

    public Patient updatePatient(final String id, final PatientDTO updatedPatientDTO) {
        Patient patient = findPatientById(id);
        PatientConverter.updatePatientWithPatientDTO(patient, updatedPatientDTO);
        return savePatient(patient);
    }

    public Patient savePatient(Patient patient) {
        if (PatientDataValidator.patientDataValidate(patient)) {
            repository.save(patient);
        }

        return patient;
    }
}
