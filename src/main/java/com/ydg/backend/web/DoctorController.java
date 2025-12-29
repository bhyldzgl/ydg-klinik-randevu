package com.ydg.backend.web;

import com.ydg.backend.domain.Doctor;
import com.ydg.backend.service.DoctorService;
import com.ydg.backend.web.dto.CreateDoctorRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public Doctor create(@RequestBody CreateDoctorRequest req) {
        return doctorService.create(req.getFullName(), req.getSpecialty(), req.getPhone(), req.getEmail());
    }

    @GetMapping
    public List<Doctor> list() {
        return doctorService.list();
    }

    @GetMapping("/{id}")
    public Doctor get(@PathVariable Long id) {
        return doctorService.get(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        doctorService.delete(id);
    }
}
