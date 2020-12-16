package com.virtusa.banking.appointment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtusa.banking.appointment.models.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment,Long>{

}
