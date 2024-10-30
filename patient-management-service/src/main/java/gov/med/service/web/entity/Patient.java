package gov.med.service.web.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "patient_tbl")
@Jacksonized
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

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

    @Column(columnDefinition = "text")
    private String allergies;

    @NotBlank(message = "Blood type is required")
    @Size(max = 10, message = "Blood type must not exceed 10 characters")
    private String bloodType;

    private Marital maritalStatus;

    @Size(max = 20, message = "Room number must not exceed 20 characters")
    private String roomNumber;

    @Size(max = 15, message = "Insurance policy number must not exceed 15 characters")
    private String insurancePolicyNumber;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
