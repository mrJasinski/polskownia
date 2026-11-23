package com.polskownia.calendar;

import com.polskownia.calendar.dto.AppointmentWriteModel;
import com.polskownia.calendar.dto.SimpleAppointment;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
class CalendarService
{
    private final WorkdayRepository workdayRepo;
    private final AppointmentRepository appointmentRepo;
    private final CustomerRepository customerRepo;

    CalendarService(final WorkdayRepository workdayRepo, final AppointmentRepository appointmentRepo, final CustomerRepository customerRepo)
    {
        this.workdayRepo = workdayRepo;
        this.appointmentRepo = appointmentRepo;
        this.customerRepo = customerRepo;
    }

//    Map<LocalDate, List<LocalTime>> getAvailableSlotsByDates(final LocalDate startDate, final LocalDate endDate, final SimpleAppointment toAppoint)
//    TODO uproszczona wersja dla testów
    Map<LocalDate, List<LocalTime>> getAvailableSlotsByDates(final LocalDate startDate, final LocalDate endDate, final int durationMinutes)
    {
//        gdzie jest zapisywany/podawany step?
        var stepMinutes = 30;

        var result = new HashMap<LocalDate, List<LocalTime>>();

        var workdays = this.workdayRepo.findByDates(startDate, endDate);

        for (Workday workday : workdays)
        {
            var slots = new ArrayList<LocalTime>();

            if (workday.hasAppointments())
            {
                for (var startAt = workday.getWorkTimeStart(); !startAt.plusMinutes(durationMinutes).isAfter(workday.getWorkTimeEnd()); startAt = startAt.plusMinutes(stepMinutes))
                {
                    var finalStartAt = startAt;
                    var endAt = startAt.plusMinutes(durationMinutes);

                    var occupied = workday.getAppointments()
                            .stream()
                            .anyMatch(a -> finalStartAt.isBefore(a.getEndTime()) && endAt.isAfter(a.getStartTime()));

                    if (!occupied)
                        slots.add(startAt);
                }
            }
            else
            {
                var lastStart = workday.getWorkTimeEnd().minusMinutes(durationMinutes);

                for (var startAt = workday.getWorkTimeStart(); !startAt.isAfter(lastStart); startAt = startAt.plusMinutes(stepMinutes))
                    slots.add(startAt);
            }

            result.put(workday.getWorkdayDate(), slots);
        }

        return result;
    }

    void addAppointment(final AppointmentWriteModel toAdd)
    {
        var workday = getWorkdayByDate(toAdd.getDate());

        var customerDTO = toAdd.getCustomer();

        var customer = this.customerRepo.save(
                new Customer(
                    customerDTO.getFirstName()
                    , customerDTO.getLastName()
                    , customerDTO.getEmail()
                    , customerDTO.getPhone()
                ));

        this.appointmentRepo.save(
                new Appointment(
                    toAdd.getType()
                    , toAdd.getStartTime()
                    , toAdd.getStartTime().plusMinutes(toAdd.getDurationMinutes())
                    , toAdd.getPrice()
                    , workday
                    , customer
                ));
    }

    Workday getWorkdayByDate(final LocalDate date)
    {
        return this.workdayRepo.findByDate(date)
                .orElseThrow(() -> new NoSuchElementException("No workday with given date!"));
    }

//    Workday createWorkdayByDate(final LocalDate date)
//    {
//        return this.workdayRepo.save(
//                new Workday()
//        );
//    }

}

