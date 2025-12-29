package com.ydg.backend.service;

import com.ydg.backend.domain.*;
import com.ydg.backend.repository.AppointmentRepository;
import com.ydg.backend.repository.DoctorRepository;
import com.ydg.backend.repository.PatientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              PatientRepository patientRepository,
                              DoctorRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    public Appointment create(Long patientId, Long doctorId, LocalDateTime startTime, LocalDateTime endTime, String notes) {
        if (patientId == null || doctorId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "patientId ve doctorId zorunlu");
        }
        if (startTime == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "startTime zorunlu");
        }

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient bulunamadı: " + patientId));

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor bulunamadı: " + doctorId));

        Appointment a = new Appointment();
        a.setPatient(patient);
        a.setDoctor(doctor);
        a.setStartTime(startTime);
        a.setEndTime(endTime);
        a.setNotes(notes);
        a.setStatus(AppointmentStatus.PLANNED);

        return appointmentRepository.save(a);
    }

    public Appointment get(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment bulunamadı: " + id));
    }

    public List<Appointment> list() {
        return appointmentRepository.findAll();
    }

    public Appointment updateStatus(Long id, AppointmentStatus status) {
        if (status == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "status zorunlu");
        }
        Appointment a = get(id);
        a.setStatus(status);
        return appointmentRepository.save(a);
    }

    public void delete(Long id) {
        Appointment a = get(id);
        appointmentRepository.delete(a);
    }
}
