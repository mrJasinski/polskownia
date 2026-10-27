package com.polskownia.calendar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlAppointmentRepository extends AppointmentRepository, JpaRepository<Appointment, Integer>
{

}
