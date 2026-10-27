package com.polskownia.calendar;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Component
class WorkdayInitializer implements ApplicationListener<ContextRefreshedEvent>
{
    private final WorkdayRepository workdayRepo;

    WorkdayInitializer(final WorkdayRepository shopRepo)
    {
        this.workdayRepo = shopRepo;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event)
    {
        var appointment1 = new Appointment(AppointmentType.AUDIT, LocalTime.of(10, 0), LocalTime.of(12, 0));
        var appointment2 = new Appointment(AppointmentType.ORAL_EXAM_TRIAL_TELC, LocalTime.of(14, 0), LocalTime.of(15, 30));

        var workday1 = new Workday(
                        LocalDate.now()
                        , LocalTime.of(8, 0)
                        , LocalTime.of(20, 0)
                        , Set.of(appointment1, appointment2)
                );

        this.workdayRepo.save(workday1);

        var workday2 = this.workdayRepo.save(
                new Workday(
                        LocalDate.now().plusDays(1)
                        , LocalTime.of(10, 0)
                        , LocalTime.of(18, 0)
                ));

        var workday3 = this.workdayRepo.save(
                new Workday(
                        LocalDate.now().plusDays(2)
                        , LocalTime.of(9, 0)
                        , LocalTime.of(12, 0)
                ));

    }
}
