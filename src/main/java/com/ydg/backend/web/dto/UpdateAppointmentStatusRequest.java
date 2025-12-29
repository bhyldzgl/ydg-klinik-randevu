package com.ydg.backend.web.dto;

import com.ydg.backend.domain.AppointmentStatus;

public class UpdateAppointmentStatusRequest {
    private AppointmentStatus status;

    public UpdateAppointmentStatusRequest() {}

    public AppointmentStatus getStatus() { return status; }
    public void setStatus(AppointmentStatus status) { this.status = status; }
}
