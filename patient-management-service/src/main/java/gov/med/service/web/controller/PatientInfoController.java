package gov.med.service.web.controller;

import gov.med.service.web.entity.Patient;
import gov.med.service.web.entity.PatientDTO;
import gov.med.service.web.service.PatientService;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/patient")
public class PatientInfoController {
    private final PatientService patientService;

    @GetMapping("/home")
    public ResponseEntity<?> home() {
        return ResponseEntity.ok("Welcome to meta medical system");
    }

    @GetMapping("/info")
    public ResponseEntity<?> getPatientInfo(@Nonnull String patientId) {
        Patient patient = patientService.findPatientById(patientId);
        return ResponseEntity.ok(patient);
    }

    @PostMapping("/new")
    public ResponseEntity<?> newPatient(@Valid @RequestBody PatientDTO patientDTO) {
        patientService.createNewPatient(patientDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateRoom(@Nonnull PatientDTO patientDTO, @Nonnull HttpSession httpSession) {
        Object patientId = httpSession.getAttribute("patientId");
        Patient patient = null;
        if (patientId != null) {
            patient = patientService.updatePatient(patientId.toString(), patientDTO);
        }
        return ResponseEntity.ok(patient);
    }


}
