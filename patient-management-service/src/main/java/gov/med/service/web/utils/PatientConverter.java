package gov.med.service.web.utils;

import gov.med.service.web.entity.Patient;
import gov.med.service.web.entity.PatientDTO;

public class PatientConverter {
    public static Patient patientDTOToPatient(PatientDTO patientDTO) {
        PatientDataValidator.patientDTODataValidate(patientDTO);
        return Patient.builder()
                .firstName(patientDTO.getFirstName())
                .lastName(patientDTO.getLastName())
                .gender(patientDTO.getGender())
                .birthDate(patientDTO.getBirthDate())
                .nationality(patientDTO.getNationality())
                .address(patientDTO.getAddress())
                .email(patientDTO.getEmail())
                .contactNumber(patientDTO.getContactNumber())
                .emergencyContact(patientDTO.getEmergencyContact())
                .allergies(patientDTO.getAllergies())
                .bloodType(patientDTO.getBloodType())
                .maritalStatus(patientDTO.getMaritalStatus())
                .roomNumber(patientDTO.getRoomNumber())
                .insurancePolicyNumber(patientDTO.getInsurancePolicyNumber())
                .build();
    }

    public static PatientDTO patientToPatientDTO(Patient patient) {
        PatientDataValidator.patientDataValidate(patient);
        return PatientDTO.builder()
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .gender(patient.getGender())
                .birthDate(patient.getBirthDate())
                .nationality(patient.getNationality())
                .address(patient.getAddress())
                .email(patient.getEmail())
                .contactNumber(patient.getContactNumber())
                .emergencyContact(patient.getEmergencyContact())
                .allergies(patient.getAllergies())
                .bloodType(patient.getBloodType())
                .maritalStatus(patient.getMaritalStatus())
                .roomNumber(patient.getRoomNumber())
                .insurancePolicyNumber(patient.getInsurancePolicyNumber())
                .build();
    }

    public static Patient updatePatientWithPatientDTO(Patient patient, PatientDTO patientDTO) {
        PatientDataValidator.patientDataValidate(patient);
        PatientDataValidator.patientDTODataValidate(patientDTO);
        return Patient.builder()
                .id(patient.getId())
                .firstName(patientDTO.getFirstName())
                .lastName(patientDTO.getLastName())
                .gender(patientDTO.getGender())
                .birthDate(patientDTO.getBirthDate())
                .nationality(patientDTO.getNationality())
                .address(patientDTO.getAddress())
                .email(patientDTO.getEmail())
                .contactNumber(patientDTO.getContactNumber())
                .emergencyContact(patientDTO.getEmergencyContact())
                .allergies(patientDTO.getAllergies())
                .bloodType(patientDTO.getBloodType())
                .maritalStatus(patientDTO.getMaritalStatus())
                .roomNumber(patientDTO.getRoomNumber())
                .insurancePolicyNumber(patientDTO.getInsurancePolicyNumber())
                .build();
    }
}
