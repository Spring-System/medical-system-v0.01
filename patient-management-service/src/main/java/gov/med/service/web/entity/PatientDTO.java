package gov.med.service.web.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Builder
@Jacksonized
public class PatientDTO {
    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must not exceed 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must not exceed 50 characters")
    private String lastName;

    private Gender gender;

    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;

    @Size(max = 15, message = "Nationality must not exceed 15 characters")
    private String nationality;

    @Size(max = 150, message = "Address must not exceed 150 characters")
    private String address;

    @Email(message = "Email should be valid")
    @Size(max = 20, message = "Email must not exceed 20 characters")
    private String email;

    @Size(max = 11, message = "Contact number must not exceed 11 characters")
    private String contactNumber;

    @NotBlank(message = "Emergency contact is required")
    @Size(max = 11, message = "Emergency contact must not exceed 11 characters")
    private String emergencyContact;

    @Size(max = 500, message = "Allergies description must not exceed 500 characters")
    private String allergies;

    @NotBlank(message = "Blood type is required")
    @Size(max = 10, message = "Blood type must not exceed 10 characters")
    private String bloodType;

    private Marital maritalStatus;

    @Size(max = 20, message = "Room number must not exceed 20 characters")
    private String roomNumber;

    @Size(max = 15, message = "Insurance policy number must not exceed 15 characters")
    private String insurancePolicyNumber;
}
