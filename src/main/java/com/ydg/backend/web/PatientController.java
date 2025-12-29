package com.ydg.backend.web;

import com.ydg.backend.domain.Patient;
import com.ydg.backend.service.PatientService;
import com.ydg.backend.web.dto.CreatePatientRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public Patient create(@RequestBody CreatePatientRequest req) {
        return patientService.create(req.getFirstName(), req.getLastName(), req.getPhone(), req.getEmail());
    }

    @GetMapping
    public List<Patient> list() {
        return patientService.list();
    }

    @GetMapping("/{id}")
    public Patient get(@PathVariable Long id) {
        return patientService.get(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        patientService.delete(id);
    }
}
