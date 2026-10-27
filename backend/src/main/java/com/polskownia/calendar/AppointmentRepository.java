package com.polskownia.calendar;

import java.util.concurrent.ThreadPoolExecutor;

interface AppointmentRepository
{
    Appointment save(Appointment toSave);
}
