package com.ydg.backend.service;

import com.ydg.backend.domain.Patient;
import com.ydg.backend.repository.PatientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient create(String firstName, String lastName, String phone, String email) {
        if (firstName == null || firstName.isBlank() || lastName == null || lastName.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "firstName ve lastName zorunlu");
        }
        Patient p = new Patient(firstName.trim(), lastName.trim(), phone, email);
        return patientRepository.save(p);
    }

    public Patient get(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient bulunamadı: " + id));
    }

    public List<Patient> list() {
        return patientRepository.findAll();
    }

    public void delete(Long id) {
        Patient p = get(id);
        patientRepository.delete(p);
    }
}
