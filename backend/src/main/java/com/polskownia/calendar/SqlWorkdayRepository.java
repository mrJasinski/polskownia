package com.polskownia.calendar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
interface SqlWorkdayRepository extends WorkdayRepository, JpaRepository<Workday, Integer>
{
    @Override
    @Query(value = "FROM Workday w WHERE w.workdayDate BETWEEN :startDate AND :endDate")
    List<Workday> findByDates(LocalDate startDate, LocalDate endDate);

    @Override
    @Query(value = "FROM Workday w WHERE w.workdayDate = :date")
    Optional<Workday> findByDate(LocalDate date);
}
