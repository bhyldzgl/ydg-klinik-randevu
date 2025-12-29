package com.ydg.backend.web.dto;

public class CreateDoctorRequest {
    private String fullName;
    private String specialty;
    private String phone;
    private String email;

    public CreateDoctorRequest() {}

    public String getFullName() { return fullName; }
    public String getSpecialty() { return specialty; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }

    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setEmail(String email) { this.email = email; }
}
