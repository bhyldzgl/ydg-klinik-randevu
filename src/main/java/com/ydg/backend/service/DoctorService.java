package com.ydg.backend.service;

import com.ydg.backend.domain.Doctor;
import com.ydg.backend.repository.DoctorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor create(String fullName, String specialty, String phone, String email) {
        if (fullName == null || fullName.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "fullName zorunlu");
        }
        Doctor d = new Doctor(fullName.trim(), specialty, phone, email);
        return doctorRepository.save(d);
    }

    public Doctor get(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor bulunamadı: " + id));
    }

    public List<Doctor> list() {
        return doctorRepository.findAll();
    }

    public void delete(Long id) {
        Doctor d = get(id);
        doctorRepository.delete(d);
    }
}
