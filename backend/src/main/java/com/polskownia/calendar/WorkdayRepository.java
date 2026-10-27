package com.polskownia.calendar;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

interface WorkdayRepository
{
    List<Workday> findByDates(LocalDate startDate, LocalDate endDate);

    Workday save(Workday toSave);

    Optional<Workday> findByDate(LocalDate date);
}
