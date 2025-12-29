package com.ydg.backend.web;

import com.ydg.backend.domain.Appointment;
import com.ydg.backend.service.AppointmentService;
import com.ydg.backend.web.dto.CreateAppointmentRequest;
import com.ydg.backend.web.dto.UpdateAppointmentStatusRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public Appointment create(@RequestBody CreateAppointmentRequest req) {
        return appointmentService.create(
                req.getPatientId(),
                req.getDoctorId(),
                req.getStartTime(),
                req.getEndTime(),
                req.getNotes()
        );
    }

    @GetMapping
    public List<Appointment> list() {
        return appointmentService.list();
    }

    @GetMapping("/{id}")
    public Appointment get(@PathVariable Long id) {
        return appointmentService.get(id);
    }

    @PutMapping("/{id}/status")
    public Appointment updateStatus(@PathVariable Long id, @RequestBody UpdateAppointmentStatusRequest req) {
        return appointmentService.updateStatus(id, req.getStatus());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        appointmentService.delete(id);
    }
}
